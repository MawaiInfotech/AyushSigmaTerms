package com.mawai.sigmaterms.mastercartonpacking.model;

public class MasterCartonPackingInsertModel {

    private String unitCd;
    private String masterCartonBarcode;
    private String prodCode;
    private int cartonSize;
    private int packCarton;
    private String prodBarcode;
    private String scanProd;
    private int scanQty;
    private String scanBy;
    private String entryDate;

    public MasterCartonPackingInsertModel() {
    }

    public String getUnitCd() {
        return unitCd;
    }

    public void setUnitCd(String unitCd) {
        this.unitCd = unitCd;
    }

    public String getMasterCartonBarcode() {
        return masterCartonBarcode;
    }

    public void setMasterCartonBarcode(String masterCartonBarcode) {
        this.masterCartonBarcode = masterCartonBarcode;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public int getCartonSize() {
        return cartonSize;
    }

    public void setCartonSize(int cartonSize) {
        this.cartonSize = cartonSize;
    }

    public int getPackCarton() {
        return packCarton;
    }

    public void setPackCarton(int packCarton) {
        this.packCarton = packCarton;
    }

    public String getProdBarcode() {
        return prodBarcode;
    }

    public void setProdBarcode(String prodBarcode) {
        this.prodBarcode = prodBarcode;
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
