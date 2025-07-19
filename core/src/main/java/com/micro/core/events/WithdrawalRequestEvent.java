package com.micro.core.events;

import java.math.BigDecimal;

public class WithdrawalRequestEvent {
    private String senderId;
    private String recepeintId;
    private BigDecimal amount;

    public WithdrawalRequestEvent(){

    }

    public WithdrawalRequestEvent(String senderId, String recepeintId, BigDecimal amount) {
        this.senderId = senderId;
        this.recepeintId = recepeintId;
        this.amount = amount;
    }

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
