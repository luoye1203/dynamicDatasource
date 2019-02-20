package com.xzst.modi.app.dModel.p2cgl;

import java.util.List;

public class HVAConfigModel {

    private String id;
    private String resultSetId;
    private String resultSetName;
    //------
    private String opratorNo;
    private String operteTime;
    private String workStatus;
    private String isDel;

    private List<HVAConfigColModel> colModel;





    public String getOpratorNo() {
        return opratorNo;
    }

    public void setOpratorNo(String opratorNo) {
        this.opratorNo = opratorNo;
    }

    public String getOperteTime() {
        return operteTime;
    }

    public void setOperteTime(String operteTime) {
        this.operteTime = operteTime;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultSetId() {
        return resultSetId;
    }

    public void setResultSetId(String resultSetId) {
        this.resultSetId = resultSetId;
    }

    public String getResultSetName() {
        return resultSetName;
    }

    public void setResultSetName(String resultSetName) {
        this.resultSetName = resultSetName;
    }

    public List<HVAConfigColModel> getColModel() {
        return colModel;
    }

    public void setColModel(List<HVAConfigColModel> colModel) {
        this.colModel = colModel;
    }



}
