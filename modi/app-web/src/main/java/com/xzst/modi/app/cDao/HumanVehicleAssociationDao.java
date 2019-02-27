package com.xzst.modi.app.cDao;

import com.xzst.modi.app.dModel.p2cgl.FugitiveModel;
import com.xzst.modi.app.dModel.p2cgl.FugitiveRelationShiperModel;
import com.xzst.modi.app.dModel.p2cgl.HVAConfigColModel;
import com.xzst.modi.app.dModel.p2cgl.HVAConfigModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HumanVehicleAssociationDao {

    public HVAConfigModel getConfig( );
    public List<HVAConfigColModel> getConfigColById(@Param("configId")String configId);
    public void addConfig(HVAConfigModel param);
    public void delConfigById(@Param("configId")String configId);
    public void updateConfig(HVAConfigModel param);

    public String getConfigIdFromSeq();
    public String getConfigIdByResultSetId(@Param("resultSetId")String resultSetId);

    public void addConfigCol(HVAConfigColModel param);


    public List<Map<String,String>> getConfigColsByConfigId(@Param("configId")String configId);

    public Map<String,String> getFugitiveByPersonId(@Param("personId")String configId);


    public void addFugitive(FugitiveModel model);
    public void addFugitiveRelationShiper(FugitiveRelationShiperModel fugitiveRelationShiperModel);

    public List<FugitiveModel> getFugitiveModels(List<String> fugitiveIdList);

    public List<FugitiveRelationShiperModel> getFugitiveRelationShiperByFugitiveId(@Param("date") String date,@Param("fugitiveId")String fugitiveId);

    public List<Map<String,Object>> getHvaTop20(@Param("date") String date);
    public List<Map<String,String>> getHvaTop20DetailByFugitiveId(@Param("date") String date,@Param("fugitiveId")String fugitiveId);

    public List<String> getFugitiveIdsByDate(@Param("date") String date);

    public void archivedFugitive(@Param("id")String id,@Param("archivedReason")String archivedReason,@Param("archivedOpertatorNo")String archivedOpertatorNo);

}
