package com.mycompany.midtermproject;

public abstract class Product {
    private String name;
    private double price;
    private String seller;

    public Product(String name, double price, String seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }

    public double calculateAmount(int quantity) {
        return price * quantity;
    }

    public abstract String getDescription();
}