package com.mawai.sigmaterms.unpack.response;

import com.mawai.sigmaterms.unpack.model.UnPackModel;

public class UnPackResponse {

    private Boolean status;
    private String message;
    private UnPackModel data;

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

    public UnPackModel getData() {
        return data;
    }

    public void setData(UnPackModel data) {
        this.data = data;
    }
}
