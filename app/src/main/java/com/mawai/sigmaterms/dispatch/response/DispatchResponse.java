package com.mawai.sigmaterms.dispatch.response;

import com.mawai.sigmaterms.dispatch.model.DispatchModel;
import com.mawai.sigmaterms.login.model.UnitModel;

import java.util.List;

public class DispatchResponse {

    private Boolean status;
    private String message;
    private DispatchModel data;

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

    public DispatchModel getData() {
        return data;
    }

    public void setData(DispatchModel data) {
        this.data = data;
    }
}
