package com.example.spring.demo.Model;

import java.util.concurrent.atomic.AtomicInteger;

public class Category {
    private static final AtomicInteger counter = new AtomicInteger();
    private int categoryId;
    private String categoryName;

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public static AtomicInteger getCounter() {
        return counter;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (categoryId ^ (categoryId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        if (categoryId != other.categoryId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Category [" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ']';
    }
}
