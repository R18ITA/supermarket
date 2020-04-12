package net.togogo.entity;

import java.util.Date;

public class Orderline {
    private Integer id;

    private String olId;

    private Double amount;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOlId() {
        return olId;
    }

    public void setOlId(String olId) {
        this.olId = olId == null ? null : olId.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}