package com.mawai.sigmaterms.login.model;

public class LoginModel {

    private String unitcode;
    private String userid;
    private String password;


    public LoginModel() {
    }

    public LoginModel(String userid, String password, String unitcode) {
        this.userid = userid;
        this.password = password;
        this.unitcode = unitcode;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
