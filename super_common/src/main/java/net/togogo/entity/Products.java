package net.togogo.entity;

public class Products {
    private Integer id;

    private String pId;

    private String pName;

    private String kId;

    private Double purchasingprice;

    private Double saleprice;

    private String unit;

    private String sId;

    private Double store;

    private Double discount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
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

    public Double getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(Double saleprice) {
        this.saleprice = saleprice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId == null ? null : sId.trim();
    }

    public Double getStore() {
        return store;
    }

    public void setStore(Double store) {
        this.store = store;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}