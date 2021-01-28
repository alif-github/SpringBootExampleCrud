package com.example.spring.demo.Model;

import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private static final AtomicInteger counter = new AtomicInteger();
    private int id;
    private String name;
    private int categoryId;
    private double price;
    private int qty;

    public Product(int id, String name, int categoryId, double price) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public static AtomicInteger getCounter() {
        return counter;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime*result+(int)(id^(id>>>32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj)
            return true;
        if (obj==null)
            return false;
        if (getClass()!=obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id!=other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [id="+id+", name ="+name+",categoryId= "+categoryId+", price="+price+"]";
    }
}
