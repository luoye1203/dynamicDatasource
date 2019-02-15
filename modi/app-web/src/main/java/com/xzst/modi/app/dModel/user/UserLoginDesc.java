package com.xzst.modi.app.dModel.user;

/**
 * Created by li on 2017/7/18.
 * 用户登录成功后的返回信息
 */

public class UserLoginDesc {
    private String sid;
    /**用户编号*/
    private String userNum;
    /**用户登录名*/
    private String loginName;
    /**用户实际姓名*/
    private String userName;
    /**用户角色*/
    private String role;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String usernum) {
        this.userNum = usernum;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
