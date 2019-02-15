package com.xzst.modi.app.dModel.user;

/**
 * Created by Administrator on 2017/7/26.
 */
public class UserConfig {

    private String tableId; //话单导入对应的titan库
    private String userNum;//yhbh;//用户编号

    private String homeConfig;//zysz;//主页设置

    private String visualizationAnalysisSetting;//kshfxsz;//可视化分析设置

    private String baiduConig;//警用百度配置

    private String BaiduEncyclopediaConifg;//百度百科配置

    public String getUserNum() {
        return userNum;
    }

    public String getHomeConfig() {
        return homeConfig;
    }

    public String getVisualizationAnalysisSetting() {
        return visualizationAnalysisSetting;
    }

    public String getBaiduConig() {
        return baiduConig;
    }

    public String getBaiduEncyclopediaConifg() {
        return BaiduEncyclopediaConifg;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public void setHomeConfig(String homeConfig) {
        this.homeConfig = homeConfig;
    }

    public void setVisualizationAnalysisSetting(String visualizationAnalysisSetting) {
        this.visualizationAnalysisSetting = visualizationAnalysisSetting;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public void setBaiduConig(String baiduConig) {
        this.baiduConig = baiduConig;
    }

    public void setBaiduEncyclopediaConifg(String baiduEncyclopediaConifg) {
        BaiduEncyclopediaConifg = baiduEncyclopediaConifg;
    }


}
