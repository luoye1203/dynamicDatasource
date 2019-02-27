package com.xzst.modi.app.dModel.p2cgl;

import java.util.List;

public class FugitiveModelPageParams {

    //当前页
    private Integer pageNow;
    //每页记录数量
    private Integer pageSize;

    private List<String> fugitiveIdList;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getFugitiveIdList() {
        return fugitiveIdList;
    }

    public void setFugitiveIdList(List<String> fugitiveIdList) {
        this.fugitiveIdList = fugitiveIdList;
    }
}
