package com.xzst.modi.app.dModel.user;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @author li
 * User info for DB xtyh_t
 */
public class User {
    private String token;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    @JsonIgnore
    private String password;
    /**
     * 用户编号
     */
    private String yhbh;
    /**
     * 身份证号
     */
    private String sfzh;
    /**
     * 用户中文姓名
     */
    private String yhxm;
    /**
     * 帐号是否启用状态
     */
    private String sfqy;

    /**
     * 角色信息，目前多个角色用,号分隔
     */
    private String role;
    /**
     * 用户token最后一次重置
     */
    @JsonIgnore
    private Date lastPasswordResetDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getYhxm() {
        return yhxm;
    }

    public void setYhxm(String yhxm) {
        this.yhxm = yhxm;
    }

    public String getSfqy() {
        return sfqy;
    }

    public void setSfqy(String sfqy) {
        this.sfqy = sfqy;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", yhbh='" + yhbh + '\'' +
                ", sfzh='" + sfzh + '\'' +
                ", yhxm='" + yhxm + '\'' +
                ", sfqy='" + sfqy + '\'' +
                ", role='" + role + '\'' +
                ", lastPasswordResetDate=" + lastPasswordResetDate +
                '}';
    }
}
