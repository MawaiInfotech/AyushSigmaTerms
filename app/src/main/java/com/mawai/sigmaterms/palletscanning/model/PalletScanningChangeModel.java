package com.mawai.sigmaterms.palletscanning.model;

public class PalletScanningChangeModel {

    private String unit_cd;
    private String pallent_no;
    private String pallent_unique_no;
    private String scanBarcode;
    private String pallent_Order_no;
    private String scanBy;

    public PalletScanningChangeModel() {
        
    }

    public String getUnit_cd() {
        return unit_cd;
    }

    public void setUnit_cd(String unit_cd) {
        this.unit_cd = unit_cd;
    }

    public String getPallent_no() {
        return pallent_no;
    }

    public void setPallent_no(String pallent_no) {
        this.pallent_no = pallent_no;
    }

    public String getPallent_unique_no() {
        return pallent_unique_no;
    }

    public void setPallent_unique_no(String pallent_unique_no) {
        this.pallent_unique_no = pallent_unique_no;
    }

    public String getScanBarcode() {
        return scanBarcode;
    }

    public void setScanBarcode(String scanBarcode) {
        this.scanBarcode = scanBarcode;
    }

    public String getPallent_Order_no() {
        return pallent_Order_no;
    }

    public void setPallent_Order_no(String pallent_Order_no) {
        this.pallent_Order_no = pallent_Order_no;
    }

    public String getScanBy() {
        return scanBy;
    }

    public void setScanBy(String scanBy) {
        this.scanBy = scanBy;
    }
}
