package com.mawai.sigmaterms.palletscanning.response;

import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningModel;

public class PalletScanningResponse {

    private Boolean status;
    private String message;
    private PalletScanningModel data;

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

    public PalletScanningModel getData() {
        return data;
    }

    public void setData(PalletScanningModel data) {
        this.data = data;
    }
}
