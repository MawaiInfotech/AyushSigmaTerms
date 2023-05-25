package com.mawai.sigmaterms.palletscanning.model;

public class PalletScanningInsertModel {

    private String unitCd;
    private String palletBarcode;
    private int palletSize;
    private int packPallet;
    private String mcBarcode;
    private String scanProd;
    private int scanQty;
    private String scanBy;
    private String entryDate;

    public String getUnitCd() {
        return unitCd;
    }

    public void setUnitCd(String unitCd) {
        this.unitCd = unitCd;
    }

    public String getPalletBarcode() {
        return palletBarcode;
    }

    public void setPalletBarcode(String palletBarcode) {
        this.palletBarcode = palletBarcode;
    }

    public int getPalletSize() {
        return palletSize;
    }

    public void setPalletSize(int palletSize) {
        this.palletSize = palletSize;
    }

    public int getPackPallet() {
        return packPallet;
    }

    public void setPackPallet(int packPallet) {
        this.packPallet = packPallet;
    }

    public String getMcBarcode() {
        return mcBarcode;
    }

    public void setMcBarcode(String mcBarcode) {
        this.mcBarcode = mcBarcode;
    }

    public String getScanProd() {
        return scanProd;
    }

    public void setScanProd(String scanProd) {
        this.scanProd = scanProd;
    }

    public int getScanQty() {
        return scanQty;
    }

    public void setScanQty(int scanQty) {
        this.scanQty = scanQty;
    }

    public String getScanBy() {
        return scanBy;
    }

    public void setScanBy(String scanBy) {
        this.scanBy = scanBy;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
}
