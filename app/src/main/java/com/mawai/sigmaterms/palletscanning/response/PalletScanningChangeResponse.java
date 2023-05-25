package com.mawai.sigmaterms.palletscanning.response;

import com.mawai.sigmaterms.palletscanning.model.PalletScanningChangeModel;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningModel;

public class PalletScanningChangeResponse {

    private Boolean status;
    private String message;
    private PalletScanningChangeModel data;

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

    public PalletScanningChangeModel getData() {
        return data;
    }

    public void setData(PalletScanningChangeModel data) {
        this.data = data;
    }
}
