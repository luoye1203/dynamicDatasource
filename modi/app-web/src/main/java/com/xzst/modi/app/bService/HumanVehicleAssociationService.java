package com.xzst.modi.app.bService;


import com.xzst.modi.app.cDao.HumanVehicleAssociationDao;
import com.xzst.modi.app.dModel.p2cgl.HVAConfigColModel;
import com.xzst.modi.app.dModel.p2cgl.HVAConfigModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HumanVehicleAssociationService {

    private static final Logger LOG=Logger.getLogger(HumanVehicleAssociationService.class);


    @Autowired
    private HumanVehicleAssociationDao humanVehicleAssociationDao;

    public HVAConfigModel getConfig( ){

        return humanVehicleAssociationDao.getConfig();
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class,RuntimeException.class})
    public void addConfig(HVAConfigModel param){
        String configId=humanVehicleAssociationDao.getConfigId();
        param.setId(configId);
        humanVehicleAssociationDao.addConfig(param);
        List<HVAConfigColModel> colList=param.getColModel();

        for (HVAConfigColModel model: colList) {
            model.setConfigId(configId);
            humanVehicleAssociationDao.addConfigCol(model);
//            throw new RuntimeException("xxx");
        }
    }

    public void delConfig(){
        humanVehicleAssociationDao.delConfig();
    }
    public void updateConfig(HVAConfigModel param){
        humanVehicleAssociationDao.updateConfig(param);
    }



}
