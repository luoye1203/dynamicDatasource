package com.xzst.modi.app.aController;

import com.xzst.modi.app.bService.AuthService;
import com.xzst.modi.app.cDao.UserDao;
import com.xzst.modi.app.dModel.BaseResponse;
import com.xzst.modi.app.dModel.LoginParams;
import com.xzst.modi.app.dModel.user.User;
import com.xzst.modi.app.dModel.user.UserConfig;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by li on 2017/7/6.
 */
@RestController
@RequestMapping("usermanage")
public class UserManageController {
    private static Logger log = LoggerFactory.getLogger(UserManageController.class);


    @Resource(name = "authServiceImpl")
    AuthService authService;
    @Autowired
    UserDao userDao;

//    @Value("${outlogin.name}")
//    private String u;
//    @Value("${outlogin.passwd}")
//    private String p;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "用户登录", notes = "0:登录成功; 201:用户名为空 ;202:用户不存在; 203:密码错误")
    @ApiImplicitParams(
            {@ApiImplicitParam(paramType = "body", name = "userInfo",
                    dataType = "LoginParams",
                    value = "用户登录", required = true)}
    )
//    @LogRecordAnnotation(funname = "登录")
    public BaseResponse login(@RequestBody LoginParams userInfo) {
        if(null==userInfo.getUsername() || "".equals(userInfo.getUsername())){
            BaseResponse response = BaseResponse.buildResponse().setCode(201).setMessage("用户名为空").build();
            return response;
        }
        User user = authService.loginToken(userInfo.getUsername(), userInfo.getPassword());
        if (null == user) {
            BaseResponse response = BaseResponse.buildResponse().setCode(202).setMessage("用户不存在").build();
            return response;
        }
        if(null!=user &&null==user.getYhbh() ){
	        BaseResponse response = BaseResponse.buildResponse().setCode(203).setMessage("密码不正确").build();
	        return response;
        }
        Map<String, Object> redata = new HashMap<>();//返回的数据
        UserConfig userConfig = userDao.findUserConigByYhbh(user.getYhbh());
        if(null==userConfig){
            userConfig=new UserConfig();
            userConfig.setUserNum(user.getYhbh());
            userConfig.setHomeConfig("084641");
            userConfig.setVisualizationAnalysisSetting(
                    "{\"relationshipColor_life\":12259754,\"relationshipColor_family\":65280,\"relationshipColor\":16763955,\"otherEdgeColor\":16711680,\"attributesColor\":39423,\"backgroundColor\":0,\"relationshipColor_reasoning\":16711680,\"edgeLabelColor\":15660528,\"linkLength\":140,\"nodeLabelColor\":65484,\"edgeLabelVisible\":false,\"relationshipColor_friend\":16711935,\"nodeLabelVisible\":true}"
                    );
            userConfig.setBaiduConig("linkAll,ren,asj,gj,rl,zsk,");
            userConfig.setBaiduEncyclopediaConifg("locusAcc,allAE,jtAE,zpAE,pyAE,shAE,defAcc,itemsAcc,bqxwtzAcc,photoAcc,");
            userDao.addUserConfig(userConfig);
        }
        userConfig.setTableId(null);
        redata.put("userInfo", user); //返回数据加入用户信息
        redata.put("userConifg", userConfig);
        //删除权限标志  1 为有权限 0 为无 ；临时措施，以后注意更改 只有xinzhishutong账户有权限
//        if (user.getUsername().equals("xinzhishutong")|| user.getUsername().equals("admin")) {
//            redata.put("isAdmin", 1);
//        } else {
            redata.put("isAdmin", 0);
//        }
        BaseResponse response = BaseResponse.buildResponse().setObj(redata).setCode(0).setMessage("登陆成功").build();
        return response;
    }

    @RequestMapping(value = "/userconfig", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "用户登录")
    public BaseResponse getUserConfig(HttpServletRequest request) {
        String yhbh=authService.getYhbhFromToken(request);
        if(yhbh!=null){
            UserConfig userConfig = userDao.findUserConigByYhbh(yhbh);
            //话单 的titan库的tableId
            userConfig.setTableId(null);
            return BaseResponse.buildResponse().setObj(userConfig).build();
        }else{
            return BaseResponse.buildResponse().setCode(401).setMessage("获取用户配置信息异常").build();
        }
    }


//    @RequestMapping(value = "/loginP", method = RequestMethod.POST, produces = "application/json")
//    @ApiOperation(value = "用户免登陆")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name="userName",paramType = "query",value = "用户名",dataType = "string",defaultValue = "lht"),
//                    @ApiImplicitParam(name="userPassword",paramType = "query",value = "密码",dataType = "string",defaultValue = "1")
//            }
//    )
//    @LogRecordAnnotation(funname = "用户免登录")
//    public void loginP(@RequestParam("userName")String username, @RequestParam("userPassword") String password, @RequestParam("param") String param, HttpServletRequest request, HttpServletResponse response) {
//        log.info("-------------loginP 执行免登陆---------------------------come in");
//        Map<String, String[]> params = request.getParameterMap();
//        for (String key : params.keySet()) {
//            String[] values = params.get(key);
//            for (int i = 0; i < values.length; i++) {
//                String value = values[i];
//                log.info("parama"+i+":  "+ key + "=" + value + "&");
//            }
//        }
//        String url = "http://"+loginRedirectToInnerOrOutParams.getAddress().trim()+":"+loginRedirectToInnerOrOutParams.getPort().trim()+"/relation/#/app?userName="+username+"&userPassword="+password+"&"+param;
//        try {
//            log.info("-------------loginP---------------------------执行重定向====>"+url);
//            response.sendRedirect(url);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @RequestMapping(value = "/outsidesi", method = RequestMethod.GET, produces = "application/json")
//    @ApiOperation(value = "用户登录", notes = "0:登录成功; 201:用户名为空 ; 202:用户名不存在;  203:密码不正确")
//    public BaseResponse login() {
//        User user = authService.loginToken(u, p);
//        if (null == user) {
//            BaseResponse response = BaseResponse.buildResponse().setCode(203).setMessage("用户名或者密码错误").build();
//            return response;
//        }
//        Map<String, Object> redata = new HashMap<>();//返回的数据
//        UserConfig userConfig = userDao.findUserConigByYhbh(user.getYhbh());
//        if(null==userConfig){
//            userConfig=new UserConfig();
//            userConfig.setUserNum(user.getYhbh());
//            userConfig.setHomeConfig("084641");
//            userConfig.setVisualizationAnalysisSetting(
//                    "{\"relationshipColor_life\":12259754,\"relationshipColor_family\":65280,\"relationshipColor\":16763955,\"otherEdgeColor\":16711680,\"attributesColor\":39423,\"backgroundColor\":0,\"relationshipColor_reasoning\":16711680,\"edgeLabelColor\":15660528,\"linkLength\":140,\"nodeLabelColor\":65484,\"edgeLabelVisible\":false,\"relationshipColor_friend\":16711935,\"nodeLabelVisible\":true}"
//            );
//            userConfig.setBaiduConig("linkAll,ren,asj,gj,rl,zsk,");
//            userConfig.setBaiduEncyclopediaConifg("locusAcc,allAE,jtAE,zpAE,pyAE,shAE,defAcc,itemsAcc,bqxwtzAcc,photoAcc,");
//            userDao.addUserConfig(userConfig);
//        }
//        userConfig.setTableId(null);
//        redata.put("userInfo", user); //返回数据加入用户信息
//        redata.put("userConifg", userConfig);
//        BaseResponse response = BaseResponse.buildResponse().setObj(redata).build();
//        return response;
//    }

    /**
     * 查询关联的案件信息
     *
     * @return
     */
    @ApiOperation(value = "查询配置参数", notes = "查询配置参数")
    @ApiParam(required = false)
    @ApiResponses({@ApiResponse(code = 400, message = "参数没填好")})
    @RequestMapping(value = "queryTmSysParam", method = RequestMethod.GET)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "pid", paramType = "query", value = "参数ID，可为空，为空时，返回所有配置项", dataType = "string"),
            }
    )
    public BaseResponse queryTmSysParam(String pid) {

        List list = null;
        try {
            list = this.authService.queryTmSysParam(pid);
        } catch (Exception e) {
            log.error("queryTmSysParam 异常：" + e);
            return  BaseResponse.buildResponse().setCode(-1).setMessage("查询错误").build();
        }

        if (list != null && list.size() > 0){
            return  BaseResponse.buildResponse().setCode(200).setMessage("查询成功").setObj(list).build();
        }else {
            return  BaseResponse.buildResponse().setCode(-1).setMessage("无数据").setObj(null).build();
        }
    }
}
