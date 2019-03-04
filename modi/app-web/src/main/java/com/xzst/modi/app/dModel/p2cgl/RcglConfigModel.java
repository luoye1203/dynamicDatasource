package com.xzst.modi.app.dModel.p2cgl;

import java.util.List;

public class RcglConfigModel {

    private String id;
    private String resultSetId;
    private String resultSetName;
    //------
    private String opratorNo;
    private String operteTime;
    private String isDel;

    private List<RcglConfigColModel> colModel;





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

    public List<RcglConfigColModel> getColModel() {
        return colModel;
    }

    public void setColModel(List<RcglConfigColModel> colModel) {
        this.colModel = colModel;
    }



}
