package com.example.hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "transactions")
public class Transaction {
    @Id
    int id;
    BigDecimal amount;
    String transactionType;
    String name;
    Date date;

    public Transaction() {
    }

    public Transaction(Integer id, BigDecimal amount, String transactionType, String name, Date date) {
        this.id = id;
        this.amount = amount;
        this.transactionType = transactionType;
        this.name = name;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
