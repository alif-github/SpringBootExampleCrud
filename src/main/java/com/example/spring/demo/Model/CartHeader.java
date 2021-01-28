package com.example.spring.demo.Model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CartHeader {
    private static final AtomicInteger counter = new AtomicInteger();
    private int idCard;
    private String tglTransaksi;
    private int idCustomer;
    private String status;

    List<Product> produtcs;

    public CartHeader(int idCard, String tglTransaksi, int idCustomer, String status, List<Product> produtcs) {
        this.idCard = idCard;
        this.tglTransaksi = tglTransaksi;
        this.idCustomer = idCustomer;
        this.status = status;
        this.produtcs = produtcs;
    }

    public List<Product> getProdutcs() {
        return produtcs;
    }

    public void setProdutcs(List<Product> produtcs) {
        this.produtcs = produtcs;
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

    @Override
    public String toString() {
        return "CartHeader{" +
                "idCard=" + idCard +
                ", tglTransaksi='" + tglTransaksi + '\'' +
                ", idCustomer=" + idCustomer +
                ", status='" + status + '\'' +
                ", produtcs=" + produtcs +
                '}';
    }
}
