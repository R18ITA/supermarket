package net.togogo.entity;

import java.util.Date;

public class Sale {
    private Integer id;

    private Date saleTime;

    private Integer pId;

    private Double saleNum;

    private Double saleCost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Double getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Double saleNum) {
        this.saleNum = saleNum;
    }

    public Double getSaleCost() {
        return saleCost;
    }

    public void setSaleCost(Double saleCost) {
        this.saleCost = saleCost;
    }
}