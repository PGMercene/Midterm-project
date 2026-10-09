package com.mycompany.midtermproject;

public class Drink extends Product {
    private String flavor;

    public Drink(String name, double price, String flavor) {
        super(name, price, "Sister");
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public String getDescription() {
        return getName() + " (" + flavor + ")";
    }
}