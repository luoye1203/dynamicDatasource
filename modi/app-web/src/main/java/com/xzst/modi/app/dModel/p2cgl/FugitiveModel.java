package com.xzst.modi.app.dModel.p2cgl;

import java.util.List;

public class FugitiveModel {

    private String id;

    private String fugitiveNo;
    private String fugitiveId;
    private String fugitiveName;

    private String isArchived;
    private String archivedReason;

    private String archivedOpertatorNo;

    private List<FugitiveRelationShiperModel> relationShiperModels;


    public String getFugitiveNo() {
        return fugitiveNo;
    }

    public void setFugitiveNo(String fugitiveNo) {
        this.fugitiveNo = fugitiveNo;
    }

    public String getFugitiveId() {
        return fugitiveId;
    }

    public void setFugitiveId(String fugitiveId) {
        this.fugitiveId = fugitiveId;
    }

    public String getFugitiveName() {
        return fugitiveName;
    }

    public void setFugitiveName(String fugitiveName) {
        this.fugitiveName = fugitiveName;
    }

    public String getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(String isArchived) {
        this.isArchived = isArchived;
    }

    public String getArchivedReason() {
        return archivedReason;
    }

    public void setArchivedReason(String archivedReason) {
        this.archivedReason = archivedReason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArchivedOpertatorNo() {
        return archivedOpertatorNo;
    }

    public void setArchivedOpertatorNo(String archivedOpertatorNo) {
        this.archivedOpertatorNo = archivedOpertatorNo;
    }

    public List<FugitiveRelationShiperModel> getRelationShiperModels() {
        return relationShiperModels;
    }

    public void setRelationShiperModels(List<FugitiveRelationShiperModel> relationShiperModels) {
        this.relationShiperModels = relationShiperModels;
    }
}
