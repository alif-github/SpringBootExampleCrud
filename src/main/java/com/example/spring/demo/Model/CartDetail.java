package com.example.spring.demo.Model;

import java.util.concurrent.atomic.AtomicInteger;

public class CartDetail {
    private static final AtomicInteger counter = new AtomicInteger();
    private int idCartDetail;
    private int idCard;
    private int id;
    private String qty;

    public CartDetail(int idCartDetail, int idCard, int id, String qty) {
        this.idCartDetail = idCartDetail;
        this.idCard = idCard;
        this.id = id;
        this.qty = qty;
    }

    public static AtomicInteger getCounter() {
        return counter;
    }

    public int getIdCartDetail() {
        return idCartDetail;
    }

    public void setIdCartDetail(int idCartDetail) {
        this.idCartDetail = idCartDetail;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
