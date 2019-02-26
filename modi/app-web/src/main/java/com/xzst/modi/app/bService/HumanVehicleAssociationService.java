package com.xzst.modi.app.bService;


import com.xzst.modi.app.cDao.HumanVehicleAssociationDao;
import com.xzst.modi.app.dModel.p2cgl.HVAConfigColModel;
import com.xzst.modi.app.dModel.p2cgl.HVAConfigModel;
import com.xzst.modi.app.hConfig.HVAcolConfigProperties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class HumanVehicleAssociationService {

    private static final Logger LOG=Logger.getLogger(HumanVehicleAssociationService.class);

    @Autowired
    private HVAcolConfigProperties hvAcolConfigProperties;

    @Autowired
    private HumanVehicleAssociationDao humanVehicleAssociationDao;

    public HVAConfigModel getConfig( ){
        HVAConfigModel configModel=humanVehicleAssociationDao.getConfig();
        if(configModel!=null){
                String configId=configModel.getId();
                List<HVAConfigColModel> configColModels=humanVehicleAssociationDao.getConfigColById(configId);
                configModel.setColModel(configColModels);

        }
        return configModel;
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

    public void delConfig(String configId){
        humanVehicleAssociationDao.delConfigById( configId);
    }
    public void updateConfig(HVAConfigModel param){
        humanVehicleAssociationDao.updateConfig(param);
    }



    public  List<Map<String,String>> getFocusCols(){

        return hvAcolConfigProperties.getFocusCol();
    }

}
