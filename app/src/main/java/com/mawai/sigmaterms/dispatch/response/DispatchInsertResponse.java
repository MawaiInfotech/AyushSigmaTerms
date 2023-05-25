package com.mawai.sigmaterms.dispatch.response;

import com.mawai.sigmaterms.dispatch.model.DispatchInsertModel;
import com.mawai.sigmaterms.dispatch.model.DispatchModel;

public class DispatchInsertResponse {

    private Boolean status;
    private String message;
    private DispatchInsertModel data;



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

    public DispatchInsertModel getData() {
        return data;
    }

    public void setData(DispatchInsertModel data) {
        this.data = data;
    }
}
