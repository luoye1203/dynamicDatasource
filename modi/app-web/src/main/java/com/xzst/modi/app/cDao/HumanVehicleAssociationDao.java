package com.xzst.modi.app.cDao;

import com.xzst.modi.app.dModel.p2cgl.HVAConfigColModel;
import com.xzst.modi.app.dModel.p2cgl.HVAConfigModel;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanVehicleAssociationDao {

    public HVAConfigModel getConfig( );
    public void addConfig(HVAConfigModel param);
    public void delConfig();
    public void updateConfig(HVAConfigModel param);

    public String getConfigId();

    public void addConfigCol(HVAConfigColModel param);

}
