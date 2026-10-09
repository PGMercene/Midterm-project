package com.mycompany.midtermproject;

public class CustomerOrder implements Summarizable {
    private String orderType;
    private OrderItem[] items;
    private int itemCount;
    private String discountType;
    private boolean delivery;

    public CustomerOrder(String orderType) {
        this.orderType = orderType;
        items = new OrderItem[100];
        itemCount = 0;
        discountType = "None";
        delivery = false;
    }

    public boolean addItem(OrderItem item) {
        if (itemCount < items.length) {
            items[itemCount] = item;
            itemCount++;
            return true;
        }

        return false;
    }

    public boolean isFull() {
        return itemCount == items.length;
    }

    public int getAvailableSlots() {
        return items.length - itemCount;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public double calculateSubtotal() {
        double subtotal = 0;

        for (int i = 0; i < itemCount; i++) {
            subtotal += items[i].calculateSubtotal();
        }

        return subtotal;
    }

    public double calculateDiscount() {
        if (discountType.equals("Student")
                || discountType.equals("PWD")) {
            return calculateSubtotal() * 0.20;
        }

        return 0;
    }

    public double getDeliveryFee() {
        if (delivery) {
            return 50;
        }

        return 0;
    }

    public double calculateTotal() {
        return calculateSubtotal()
                - calculateDiscount()
                + getDeliveryFee();
    }

    @Override
    public void printSummary() {
        System.out.println("\n========== RECEIPT ==========");
        System.out.println("Order type: " + orderType);
        System.out.println(
                "Fulfillment: "
                + (delivery ? "Delivery" : "Pickup")
        );

        for (int i = 0; i < itemCount; i++) {
            OrderItem item = items[i];

            System.out.printf(
                    "%d x %s - PHP %.2f%n",
                    item.getQuantity(),
                    item.getProduct().getDescription(),
                    item.calculateSubtotal()
            );

            if (!item.getFlavorDetails().equals("")) {
                System.out.println(
                        "    Flavors: "
                        + item.getFlavorDetails()
                );
            }
        }

        System.out.printf(
                "Subtotal: PHP %.2f%n",
                calculateSubtotal()
        );

        System.out.printf(
                "%s discount: -PHP %.2f%n",
                discountType,
                calculateDiscount()
        );

        System.out.printf(
                "Delivery fee: PHP %.2f%n",
                getDeliveryFee()
        );

        System.out.printf(
                "TOTAL: PHP %.2f%n",
                calculateTotal()
        );

        System.out.println("=============================");
    }
}