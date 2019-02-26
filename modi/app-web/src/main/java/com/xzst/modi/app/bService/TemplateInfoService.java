package com.xzst.modi.app.bService;

import com.alibaba.fastjson.JSON;
import com.xzst.modi.app.dModel.ModelPageBean;
import com.xzst.modi.app.dModel.ResultSetBean;
import com.xzst.modi.app.http.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TemplateInfoService {

    private static final Logger LOG=Logger.getLogger(TemplateInfoService.class);

    public static final String preTag = "<font weight=\'bold\' color=\'#FF0000\'>";
    public static final String endTag = "</font>";


    @Autowired
    private HttpAPIService httpAPIService;

    @Value("${shumo.modelListUrl}")
    private String modelListUrl;

    @Value("${shumo.resultSetList}")
    private String resultSetList;


    @Value("${shumo.resultSetColInfoUrl}")
    private String resultSetColInfoUrl;

    @Value("${shumo.token}")
    private String token;



    public List<Map> getModelAndResultByKeyword(String keyword){

        Map<String,Object> modelMap=this.getModelByKeyword(keyword);
        List<String> modelIdList=(List<String>)modelMap.get("modelIdList");

        Map<String,Map<String, List<Map<String, String>>>> mulResultSetMap=this.getResultInfoByModelIdAndKeyword(modelIdList,keyword);
        //获取所有的模型的结果集
        Map<String, List<Map<String, String>>> allResultSetMap=mulResultSetMap.get("allResultSetMap");
        //获取keyword过滤过得结果集
        Map<String, List<Map<String, String>>> fliterResultSetMap=mulResultSetMap.get("fliterResultSetMap");
        //获取处理过得模型
        List<Map<String,Object>> processedDataMapListModel=(List<Map<String,Object>>)modelMap.get("processedDataMapList");
        //获取差集模型
        List<Map<String,Object>> diffDataMapListModel=(List<Map<String,Object>>)modelMap.get("diffDataMapList");
        if(diffDataMapListModel==null ){
            return null;
        }
        //查看处理过得Model列表是否有结果集
        List<Map>	reMapList=  processedDataMapListModel.stream().
                map(rowDataMap->{
                    String modeleId=rowDataMap.get("id").toString();
                    List<Map<String, String>> resultSetList=allResultSetMap.get(modeleId);
                    if(resultSetList!=null&&resultSetList.size()>0){
                        rowDataMap.put("hasChild","0");
                        rowDataMap.put("tableList",resultSetList.stream().sorted((map1,map2)->{return map1.get("id").compareTo(map2.get("id"));}).collect(Collectors.toList()));
                    }else {
                        rowDataMap.put("hasChild","1");
                    }
                    return rowDataMap;}).
                collect(Collectors.toList());
        //多余的模型列表中里面是否含有结果集的 如果有则加入到返回数据里
        diffDataMapListModel.
                stream().
                peek(rowDataMap->{
                    String modeleId=rowDataMap.get("id").toString();
                    List<Map<String, String>> resultSetList=fliterResultSetMap.get(modeleId);
                    if(resultSetList!=null&&resultSetList.size()>0){
                        rowDataMap.put("hasChild","0");
                        rowDataMap.put("tableList",resultSetList.stream().sorted((map1,map2)->{return Integer.parseInt(map1.get("id"))-Integer.parseInt(map2.get("id"));}).collect(Collectors.toList()));
                        reMapList.add(rowDataMap);
                    }}).
                count();

        List<Map> reList=null;
        if(reMapList!=null&&reMapList.size()>0){
            reList=reMapList.stream().
//			filter(map->map.get("hasChild").toString().equals("0")).
        sorted((map1,map2)->{return Integer.parseInt(map1.get("id").toString())-Integer.parseInt(map2.get("id").toString());}).
                            collect(Collectors.toList());
        }

        return reList;
    }


    public Map<String,Object> getModelByKeyword(String keyword){
        Map<String,Object> reMap=new LinkedHashMap<>();
        try {
            String url=modelListUrl;
            Map<String,Object> bodyParamsMap=new HashMap<>();
            //查询userId下的全部任务模型列表
            bodyParamsMap.put("pageNow",0);
            bodyParamsMap.put("pageSize",Integer.MAX_VALUE);
            bodyParamsMap.put("taskType",2);

            String bodyJsonParams= JSON.toJSONString(bodyParamsMap);
            Map<String,String> headersMap=new HashMap<>();

            headersMap.put("Authorization",token);
            headersMap.put("Content-Type","application/json; charset=utf-8");

            HttpResult result = httpAPIService.doPost(url,null,headersMap,bodyJsonParams);
            if(null==result||result.getBody().equals("")){
                throw new Exception("模型组的url"+ url+"访问访问结果为空,请确认...");
            }

            List<Map<String,Object>> processedDataMapList=null;
            List<String> modelIdList=new ArrayList<>();

//			logger.info(dataJson);
            if(result.getCode()==200){
                String dataJson=result.getBody();
                ModelPageBean modelPageBean=JSON.parseObject(dataJson, ModelPageBean.class);
                reMap.put("originalDataMapList",modelPageBean.getData());
                //获取name包含keyword的 model
                if(StringUtils.isNotBlank(keyword)){
                    processedDataMapList=modelPageBean.getData().stream().
                            filter(rowDataMap->rowDataMap.get("name")!=null&&rowDataMap.get("name").toString().contains(keyword)).//过滤不含keyword的数据
                            map(rowDataMap-> {
                        String orignName=rowDataMap.get("name").toString();
                        String highLightName=highLightTranslate(orignName,keyword);
                        rowDataMap.put("highLightName",highLightName);
                        return rowDataMap;}).
                            sorted((map1,map2)-> {return -(Integer.parseInt(map1.get("id").toString())-Integer.parseInt(map2.get("id").toString()));} ).
                            collect(Collectors.toList());
                }else{
                    processedDataMapList=modelPageBean.getData().stream().
                            filter(rowDataMap->rowDataMap.get("name")!=null).
                            sorted((map1,map2)-> {return -(Integer.parseInt(map1.get("id").toString())-Integer.parseInt(map2.get("id").toString()));} ).
                            collect(Collectors.toList());
                }
                //获取所有的modelid
                modelPageBean.getData().stream().peek(rowDataMap->{modelIdList.add(rowDataMap.get("id").toString());}).count();
                reMap.put("modelIdList",modelIdList);
                reMap.put("processedDataMapList",processedDataMapList);
                List<Map<String,Object>> temp_processedDataMapList=processedDataMapList;
                reMap.put("diffDataMapList",modelPageBean.getData().stream().filter(rowMap->!temp_processedDataMapList.contains(rowMap)).collect(Collectors.toList()));


            }else{
                throw new Exception("模型组的url"+ url+"访问失败,失败代码:"+result.getCode()+" 请确认...\n" +
                        "错误内容:"+result.getBody());
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }

        return  reMap;

    }



    public String highLightTranslate(String orignStr,String keyword){
        if(null==keyword || "".equals(keyword.trim())){
            return orignStr;
        }
        return orignStr.replace(keyword,preTag+keyword+endTag);

    }


    private Map<String,Map<String, List<Map<String, String>>>> getResultInfoByModelIdAndKeyword(List<String> modelIdList,String keyword) {
        String url= resultSetList;
        //请求头参数
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Authorization", token);
        headersMap.put("Content-Type", "application/json; charset=utf-8");

        String bodyJsonParams=JSON.toJSONString(modelIdList);

        Map<String,Map<String, List<Map<String, String>>>> reMap = new LinkedHashMap<>();
        HttpResult result = null;
        try {
            result = httpAPIService.doPost(url,null,headersMap,bodyJsonParams);
            if(null==result||result.getBody().equals("")){
                throw new Exception("模型组的url"+ url+"访问访问结果为空,请确认...");
            }
            if(result.getCode()==200){
                String dataJson=result.getBody();
                ResultSetBean resultSetBean=JSON.parseObject(dataJson, ResultSetBean.class);

                //获取name包含keyword的 resulst 同时按照modelid分组

                Map<String, List<Map<String, String>>> filterMap=resultSetBean.getNodes().stream().
                        filter(rowDataMap->rowDataMap.get("name")!=null&&rowDataMap.get("name").contains(keyword==null?"":keyword)).//过滤不含keyword的数据
                        map(rowDataMap-> {//高亮
                    String orignName=rowDataMap.get("name");
                    String highLightName=highLightTranslate(orignName,keyword==null?"":keyword);
                    rowDataMap.put("highLightName",highLightName);
                    return rowDataMap;}).
                        collect(Collectors.groupingBy(rowDataMap->rowDataMap.get("taskId")));

                Map<String, List<Map<String, String>>> allMap=resultSetBean.getNodes().stream().
                        filter(rowDataMap->rowDataMap.get("name")!=null).
                        collect(Collectors.groupingBy(rowDataMap->rowDataMap.get("taskId")));

                reMap.put("fliterResultSetMap",filterMap);
                reMap.put("allResultSetMap",allMap);

            }else{
                throw new Exception("模型组的url"+ url+"访问失败,失败代码:"+result.getCode()+" 请确认...\n" +
                        "错误内容:"+result.getBody());
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
        return reMap;
    }



    public List<Map> getColInfoByResultTableId(String resultTableId){
        String url = resultSetColInfoUrl+resultTableId;
        Map<String, String> headersMap = new HashMap<>();

        headersMap.put("Authorization", token);
        headersMap.put("Content-Type", "application/json; charset=utf-8");

        List<Map> resultSetColInfo=null;

        HttpResult result = null;
        try {
            result = httpAPIService.doGet(url,headersMap);
            if(result==null){
                throw new Exception("url"+ url+"获取内容为空 请确认...");
            }
            if(result.getCode()==200){
                String dataString=result.getBody();
                if(StringUtils.isNotBlank(dataString)){
                    resultSetColInfo=JSON.parseArray(dataString,Map.class);
                }else{
                    throw new Exception("url"+ url+"获取内容为空 请确认...");
                }
            }else{
                throw new Exception("url"+ url+"访问失败,失败代码:"+result.getCode()+" 请确认...");
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }


        return  resultSetColInfo;
    }



}
