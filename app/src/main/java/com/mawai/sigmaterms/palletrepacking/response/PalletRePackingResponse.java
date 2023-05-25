package com.mawai.sigmaterms.palletrepacking.response;

import com.mawai.sigmaterms.palletrepacking.model.PalletRePackingModel;

public class PalletRePackingResponse {

    private  boolean status;
    private  String message;
    private PalletRePackingModel data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PalletRePackingModel getData() {
        return data;
    }

    public void setData(PalletRePackingModel data) {
        this.data = data;
    }
}
