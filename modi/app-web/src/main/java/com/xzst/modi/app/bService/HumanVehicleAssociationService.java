package com.xzst.modi.app.bService;


import com.xzst.modi.app.cDao.HumanVehicleAssociationDao;
import com.xzst.modi.app.dModel.ConsumerMessageBean;
import com.xzst.modi.app.dModel.p2cgl.*;
import com.xzst.modi.app.gCommon.PageModel;
import com.xzst.modi.app.hConfig.HVAcolConfigProperties;
import com.xzst.modi.app.websocket.WebSocket;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class HumanVehicleAssociationService {

    private static final Logger LOG = Logger.getLogger(HumanVehicleAssociationService.class);

    @Autowired
    private HVAcolConfigProperties hvAcolConfigProperties;

    @Autowired
    private HumanVehicleAssociationDao humanVehicleAssociationDao;

    @Autowired
    private WebSocket webSocket;



    public HVAConfigModel getConfig() {
        HVAConfigModel configModel = humanVehicleAssociationDao.getConfig();
        if (configModel != null) {
            String configId = configModel.getId();
            List<HVAConfigColModel> configColModels = humanVehicleAssociationDao.getConfigColById(configId);
            configModel.setColModel(configColModels);

        }
        return configModel;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, RuntimeException.class})
    public void addConfig(HVAConfigModel param) {
        String configId = humanVehicleAssociationDao.getConfigIdFromSeq();
        param.setId(configId);
        humanVehicleAssociationDao.addConfig(param);
        List<HVAConfigColModel> colList = param.getColModel();

        for (HVAConfigColModel model : colList) {
            model.setConfigId(configId);
            humanVehicleAssociationDao.addConfigCol(model);
//            throw new RuntimeException("xxx");
        }
    }

    public void delConfig(String configId) {
        humanVehicleAssociationDao.delConfigById(configId);
    }

    public void updateConfig(HVAConfigModel param) {
        humanVehicleAssociationDao.updateConfig(param);
    }


    public List<Map<String, String>> getPageFocusCols() {

        return hvAcolConfigProperties.getFocusCol();
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, RuntimeException.class})
    public void insertMessageFromKafka(ConsumerMessageBean consumerMessageBean) {

        String resultSetId = consumerMessageBean.getResultSetId();
        Map<String, String> dataMap = consumerMessageBean.getDataMap();

        String configId = this.humanVehicleAssociationDao.getConfigIdByResultSetId(resultSetId);
        List<Map<String, String>> colsMap = this.humanVehicleAssociationDao.getConfigColsByConfigId(configId);

        FugitiveModel fugitiveModel = new FugitiveModel();
        FugitiveRelationShiperModel fugitiveRelationShiperModel = new FugitiveRelationShiperModel();

        for (Map<String, String> colMap : colsMap) {
            String ename = colMap.get("ENAME");
            String pagebyename = colMap.get("PAGEBYENAME");
            if (ename.equals("fugitiveNo")) {
                fugitiveModel.setFugitiveNo(dataMap.get(pagebyename));
            }
            if (ename.equals("fugitiveId")) {
                fugitiveModel.setFugitiveId(dataMap.get(pagebyename));
                fugitiveRelationShiperModel.setFugitiveId(dataMap.get(pagebyename));
            }
            if (ename.equals("fugitiveName")) {
                fugitiveModel.setFugitiveName(dataMap.get(pagebyename));
            }
            if (ename.equals("relationShip")) {
                fugitiveRelationShiperModel.setRelationShip(dataMap.get(pagebyename));
            }
            if (ename.equals("relationShiperId")) {
                fugitiveRelationShiperModel.setRelationShiperId(dataMap.get(pagebyename));
            }
            if (ename.equals("trailDesc")) {
                fugitiveRelationShiperModel.setTrailDesc(dataMap.get(pagebyename));
            }
            if (ename.equals("trailType")) {
                fugitiveRelationShiperModel.setTrailType(dataMap.get(pagebyename));
            }
            if (ename.equals("trailTime")) {
                fugitiveRelationShiperModel.setTrailTime(dataMap.get(pagebyename));
            }
            if (ename.equals("lat")) {
                fugitiveRelationShiperModel.setLat(dataMap.get(pagebyename));
            }
            if (ename.equals("lon")) {
                fugitiveRelationShiperModel.setLon(dataMap.get(pagebyename));
            }

        }

        Map<String, String> fugitiveMap = this.humanVehicleAssociationDao.getFugitiveByPersonId(fugitiveModel.getFugitiveId());

        if (null == fugitiveMap) {
            this.humanVehicleAssociationDao.addFugitive(fugitiveModel);
            this.humanVehicleAssociationDao.addFugitiveRelationShiper(fugitiveRelationShiperModel);
        } else {
            this.humanVehicleAssociationDao.addFugitiveRelationShiper(fugitiveRelationShiperModel);
        }

        //通知前端
        webSocket.sendInfoToAllSession();

    }


    public PageModel<FugitiveModel> getFugitiveModels(FugitiveModelPageParams params) {

        try {
            List<String> fugitiveIdList = this.humanVehicleAssociationDao.getFugitiveIdsByDate(params.getDate());
            params.setFugitiveIdList(fugitiveIdList);
            if (fugitiveIdList != null && fugitiveIdList.size() > 0) {

                PageModel<FugitiveModel> pageModel = this.humanVehicleAssociationDao.getFugitiveModelsByPage(params);

                for (FugitiveModel fugitiveModel : pageModel.getRecords()) {
                    String fugitiveId = fugitiveModel.getFugitiveId();
                    List<FugitiveRelationShiperModel> fugitiveRelationShiperModelList = this.humanVehicleAssociationDao.getFugitiveRelationShiperByFugitiveId(params.getDate(), fugitiveId);
                    fugitiveModel.setRelationShiperModels(fugitiveRelationShiperModelList);
                }
                return pageModel;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }


    public List<Map<String, Object>> getHvaTop20(String date) {
        List<Map<String, Object>> top20 = this.humanVehicleAssociationDao.getHvaTop20(date);
        for (Map<String, Object> map : top20) {
            String fugitiveId = map.get("FUGITIVEID").toString();
            List<Map<String, String>> topDetail = this.humanVehicleAssociationDao.getHvaTop20DetailByFugitiveId(date, fugitiveId);
            map.put("detail", topDetail);
        }
        return top20;

    }


    public void archivedFugitive(String id, String archivedReason, String archivedOpertatorNo) {
        this.humanVehicleAssociationDao.archivedFugitive(id, archivedReason, archivedOpertatorNo);
    }
}
