package com.mawai.sigmaterms.dispatchcancel.model;

public class DispatchCancelModel {

    private int packtype;
    private String scanbarcode;
    private String unitCode;
    private String scanby;

    public DispatchCancelModel() {
    }

    public int getPacktype() {
        return packtype;
    }

    public void setPacktype(int packtype) {
        this.packtype = packtype;
    }

    public String getScanbarcode() {
        return scanbarcode;
    }

    public void setScanbarcode(String scanbarcode) {
        this.scanbarcode = scanbarcode;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getScanby() {
        return scanby;
    }

    public void setScanby(String scanby) {
        this.scanby = scanby;
    }
}
