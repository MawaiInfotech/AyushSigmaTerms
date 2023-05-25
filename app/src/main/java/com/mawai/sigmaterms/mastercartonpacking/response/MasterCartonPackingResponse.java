package com.mawai.sigmaterms.mastercartonpacking.response;

import com.mawai.sigmaterms.login.model.LoginModel;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;

public class MasterCartonPackingResponse {

    private Boolean status;
    private String message;
    private MasterCartonPackingModel data;

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

    public MasterCartonPackingModel getData() {
        return data;
    }

    public void setData(MasterCartonPackingModel data) {
        this.data = data;
    }
}
