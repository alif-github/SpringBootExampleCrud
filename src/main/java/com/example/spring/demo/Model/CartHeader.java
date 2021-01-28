package com.example.spring.demo.Model;

import java.util.concurrent.atomic.AtomicInteger;

public class CartHeader {
    private static final AtomicInteger counter = new AtomicInteger();
    private int idCard;
    private String tglTransaksi;
    private int idCustomer;
    private String status;

    public CartHeader(int idCard, String tglTransaksi, int idCustomer, String status) {
        this.status = status;
        this.idCard = idCard;
        this.tglTransaksi = tglTransaksi;
        this.idCustomer = idCustomer;
    }

    public static AtomicInteger getCounter() {
        return counter;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public String getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(String tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
