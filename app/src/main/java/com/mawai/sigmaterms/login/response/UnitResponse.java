package com.mawai.sigmaterms.login.response;



import com.mawai.sigmaterms.login.model.UnitModel;

import java.util.List;

public class UnitResponse {

    private Boolean status;
    private String message;
    private List<UnitModel> data;


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UnitModel> getData() {
        return data;
    }

    public void setData(List<UnitModel> data) {
        this.data = data;
    }
}
