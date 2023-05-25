package com.mawai.sigmaterms.palletscanning.model;

public class PalletScanningModel {

    private  String pkOrdHdno;
    private  String soNo;
    private  String prodCd;
    private  String mastCartBcodeNo;
    private  String palletBcodeNo;
    private  String scanSt;
    private  int slno;
    private  String pkOrdDt;
    private  int qty;
    private  String skuCode;
    private  String custCode;


    public String getPkOrdHdno() {
        return pkOrdHdno;
    }

    public void setPkOrdHdno(String pkOrdHdno) {
        this.pkOrdHdno = pkOrdHdno;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public String getProdCd() {
        return prodCd;
    }

    public void setProdCd(String prodCd) {
        this.prodCd = prodCd;
    }

    public String getPalletBcodeNo() {
        return palletBcodeNo;
    }

    public void setPalletBcodeNo(String palletBcodeNo) {
        this.palletBcodeNo = palletBcodeNo;
    }

    public String getScanSt() {
        return scanSt;
    }

    public void setScanSt(String scanSt) {
        this.scanSt = scanSt;
    }

    public int getSlno() {
        return slno;
    }

    public void setSlno(int slno) {
        this.slno = slno;
    }

    public String getPkOrdDt() {
        return pkOrdDt;
    }

    public void setPkOrdDt(String pkOrdDt) {
        this.pkOrdDt = pkOrdDt;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getMastCartBcodeNo() {
        return mastCartBcodeNo;
    }

    public void setMastCartBcodeNo(String mastCartBcodeNo) {
        this.mastCartBcodeNo = mastCartBcodeNo;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
}
