package com.mycompany.midtermproject;

public class OrderItem {
    private Product product;
    private int quantity;
    private String flavorDetails;

    public OrderItem(
            Product product,
            int quantity,
            String flavorDetails
    ) {
        this.product = product;
        setQuantity(quantity);
        this.flavorDetails = flavorDetails;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero."
            );
        }
    }

    public String getFlavorDetails() {
        return flavorDetails;
    }

    public double calculateSubtotal() {
        return product.calculateAmount(quantity);
    }
}