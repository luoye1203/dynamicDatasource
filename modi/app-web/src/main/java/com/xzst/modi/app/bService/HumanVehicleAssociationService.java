package com.xzst.modi.app.bService;


import com.xzst.modi.app.cDao.HumanVehicleAssociationDao;
import com.xzst.modi.app.dModel.p2cgl.HVAConfigModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanVehicleAssociationService {

    private static final Logger LOG=Logger.getLogger(HumanVehicleAssociationService.class);


    @Autowired
    private HumanVehicleAssociationDao humanVehicleAssociationDao;

    public HVAConfigModel getConfig( ){

        return humanVehicleAssociationDao.getConfig();
    }

    public void addConfig(HVAConfigModel param){
        humanVehicleAssociationDao.addConfig(param);
    }

    public void delConfig(){
        humanVehicleAssociationDao.delConfig();
    }
    public void updateConfig(HVAConfigModel param){
        humanVehicleAssociationDao.updateConfig(param);
    }

}
