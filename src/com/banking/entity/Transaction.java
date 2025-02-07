package com.banking.entity;

import java.time.LocalDate;

public class Transaction {
    private LocalDate TransationDate;

    @Override
    public String toString() {
        return "Transaction{" +
                "TransationDate=" + TransationDate +
                ", transsactionUserId='" + transsactionUserId + '\'' +
                ", transsactionAmount=" + transsactionAmount +
                ", transactionType='" + transactionType + '\'' +
                ", initialBalance=" + initialBalance +
                ", finalBalance=" + finalBalance +
                ", transactionPerformedBy='" + transactionPerformedBy + '\'' +
                '}';
    }

    public String getTranssactionUserId() {
        return transsactionUserId;
    }

    public void setTranssactionUserId(String transsactionUserId) {
        this.transsactionUserId = transsactionUserId;
    }

    public LocalDate getTransationDate() {
        return TransationDate;
    }

    public void setTransationDate(LocalDate transationDate) {
        TransationDate = transationDate;
    }

    public Double getTranssactionAmount() {
        return transsactionAmount;
    }

    public void setTranssactionAmount(Double transsactionAmount) {
        this.transsactionAmount = transsactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(Double finalBalance) {
        this.finalBalance = finalBalance;
    }

    public String getTransactionPerformedBy() {
        return transactionPerformedBy;
    }

    public void setTransactionPerformedBy(String transactionPerformedBy) {
        this.transactionPerformedBy = transactionPerformedBy;
    }

    private String transsactionUserId;
    private Double transsactionAmount;
    private String transactionType;
    private Double initialBalance;
    private Double finalBalance;

    public Transaction(LocalDate transationDate, String transsactionUserId, Double transsactionAmount, String transactionType, Double initialBalance, Double finalBalance, String transactionPerformedBy) {
        TransationDate = transationDate;
        this.transsactionUserId = transsactionUserId;
        this.transsactionAmount = transsactionAmount;
        this.transactionType = transactionType;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
        this.transactionPerformedBy = transactionPerformedBy;
    }

    private String transactionPerformedBy;

}