package com.mawai.sigmaterms.dispatch.model;

public class DispatchInsertModel {

    private String unitCd;
    private String dispBarcode;
    private int palletNo;
    private String refDocNo;
    private int scanQty;
    private String barcodeType;
    private String scanBy;
    private String entryDate;
    private String session_no;

    public String getUnitCd() {
        return unitCd;
    }

    public void setUnitCd(String unitCd) {
        this.unitCd = unitCd;
    }

    public String getDispBarcode() {
        return dispBarcode;
    }

    public void setDispBarcode(String dispBarcode) {
        this.dispBarcode = dispBarcode;
    }

    public int getPalletNo() {
        return palletNo;
    }

    public void setPalletNo(int palletNo) {
        this.palletNo = palletNo;
    }

    public String getRefDocNo() {
        return refDocNo;
    }

    public void setRefDocNo(String refDocNo) {
        this.refDocNo = refDocNo;
    }

    public int getScanQty() {
        return scanQty;
    }

    public void setScanQty(int scanQty) {
        this.scanQty = scanQty;
    }

    public String getBarcodeType() {
        return barcodeType;
    }

    public void setBarcodeType(String barcodeType) {
        this.barcodeType = barcodeType;
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

    public String getSession_no() {
        return session_no;
    }

    public void setSession_no(String session_no) {
        this.session_no = session_no;
    }
}
