package com.micro.transefer.model;

import java.math.BigDecimal;

public class TransferModel {
    private String senderId;
    private String recepeintId;
    private BigDecimal amount;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecepeintId() {
        return recepeintId;
    }

    public void setRecepeintId(String recepeintId) {
        this.recepeintId = recepeintId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
