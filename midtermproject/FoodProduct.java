package com.mycompany.midtermproject;

public class FoodProduct extends Product {
    private String category;

    public FoodProduct(
            String name,
            double price,
            String seller,
            String category
    ) {
        super(name, price, seller);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String getDescription() {
        return getName() + " (" + category + ")";
    }
}