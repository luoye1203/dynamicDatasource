package com.xzst.modi.app.cDao;

import com.xzst.modi.app.dModel.p2cgl.HVAConfigColModel;
import com.xzst.modi.app.dModel.p2cgl.HVAConfigModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HumanVehicleAssociationDao {

    public HVAConfigModel getConfig( );
    public List<HVAConfigColModel> getConfigColById(@Param("configId")String configId);
    public void addConfig(HVAConfigModel param);
    public void delConfigById(@Param("configId")String configId);
    public void updateConfig(HVAConfigModel param);

    public String getConfigId();

    public void addConfigCol(HVAConfigColModel param);

}
