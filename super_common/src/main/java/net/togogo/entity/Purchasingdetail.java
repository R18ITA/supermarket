package net.togogo.entity;

public class Purchasingdetail {
    private Integer id;

    private String pdId;

    private String pId;

    private Double pdNum;

    private String sId;

    private String kId;

    private Double purchasingprice;

    private String pUnit;

    private Double amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPdId() {
        return pdId;
    }

    public void setPdId(String pdId) {
        this.pdId = pdId == null ? null : pdId.trim();
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public Double getPdNum() {
        return pdNum;
    }

    public void setPdNum(Double pdNum) {
        this.pdNum = pdNum;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId == null ? null : sId.trim();
    }

    public String getkId() {
        return kId;
    }

    public void setkId(String kId) {
        this.kId = kId == null ? null : kId.trim();
    }

    public Double getPurchasingprice() {
        return purchasingprice;
    }

    public void setPurchasingprice(Double purchasingprice) {
        this.purchasingprice = purchasingprice;
    }

    public String getpUnit() {
        return pUnit;
    }

    public void setpUnit(String pUnit) {
        this.pUnit = pUnit == null ? null : pUnit.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}