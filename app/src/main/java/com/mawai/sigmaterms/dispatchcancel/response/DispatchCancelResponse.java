package com.mawai.sigmaterms.dispatchcancel.response;

import com.mawai.sigmaterms.dispatchcancel.model.DispatchCancelModel;
import com.mawai.sigmaterms.unpack.model.UnPackModel;

public class DispatchCancelResponse {

    private Boolean status;
    private String message;
    private DispatchCancelModel data;

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

    public DispatchCancelModel getData() {
        return data;
    }

    public void setData(DispatchCancelModel data) {
        this.data = data;
    }
}
