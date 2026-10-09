package com.mycompany.midtermproject;

public class SalesEntry {
    private Product product;
    private int quantitySold;

    public SalesEntry(
            Product product, int quantitySold
    ) {
        this.product = product;
        setQuantitySold(quantitySold);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        if (quantitySold >= 0) {
            this.quantitySold = quantitySold;
        } else {
            throw new IllegalArgumentException(
                    "Quantity sold cannot be negative."
            );
        }
    }

    public double calculateSalesAmount() {
        return product.calculateAmount(quantitySold);
    }
}