package com.xzst.modi.app.dModel.p2cgl;

public class FugitiveRelationShiperModel {

    private String id;

    private String fugitiveId;

    private String relationShip;
    private String relationShiperId;

    private String trailDesc;
    private String trailType;

    private String trailTime;

    private String lat;
    private String lon;
    private String operate;


    public String getFugitiveId() {
        return fugitiveId;
    }

    public void setFugitiveId(String fugitiveId) {
        this.fugitiveId = fugitiveId;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public String getRelationShiperId() {
        return relationShiperId;
    }

    public void setRelationShiperId(String relationShiperId) {
        this.relationShiperId = relationShiperId;
    }

    public String getTrailDesc() {
        return trailDesc;
    }

    public void setTrailDesc(String trailDesc) {
        this.trailDesc = trailDesc;
    }

    public String getTrailType() {
        return trailType;
    }

    public void setTrailType(String trailType) {
        this.trailType = trailType;
    }

    public String getTrailTime() {
        return trailTime;
    }

    public void setTrailTime(String trailTime) {
        this.trailTime = trailTime;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
