package com.example.groceryapp;

public class Category {
    private String name;
    private int itemCount;

    public Category(String name, int itemCount, int imageResId) {
        this.name = name;
        this.itemCount = itemCount;
    }

    public String getName() {
        return name;
    }

    public int getItemCount() {
        return itemCount;
    }
}
