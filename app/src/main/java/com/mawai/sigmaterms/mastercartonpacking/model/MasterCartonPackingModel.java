package com.mawai.sigmaterms.mastercartonpacking.model;

public class MasterCartonPackingModel {

    private  String pkOrdHdno;
    private  String soNo;
    private  String prodCd;
    private  String prod_desc;
    private  String prodBcodeNo;
    private  String mastCartBcodeNo;
    private  String scanSt;
    private  int slno;
    private  String pkOrdDt;
    private  int qty;
    private  String skuCode;
    private  String palletBcodeNo;


    public MasterCartonPackingModel() {

    }

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

    public String getMastCartBcodeNo() {
        return mastCartBcodeNo;
    }

    public void setMastCartBcodeNo(String mastCartBcodeNo) {
        this.mastCartBcodeNo = mastCartBcodeNo;
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

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getPalletBcodeNo() {
        return palletBcodeNo;
    }

    public void setPalletBcodeNo(String palletBcodeNo) {
        this.palletBcodeNo = palletBcodeNo;
    }

    public String getProdBcodeNo() {
        return prodBcodeNo;
    }

    public void setProdBcodeNo(String prodBcodeNo) {
        this.prodBcodeNo = prodBcodeNo;
    }

    public String getProd_desc() {
        return prod_desc;
    }

    public void setProd_desc(String prod_desc) {
        this.prod_desc = prod_desc;
    }
}
