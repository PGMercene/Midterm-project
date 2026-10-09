package com.mycompany.midtermproject;

public class ProductCatalog {
    public static Product[] createProducts() {
        Product[] products = new Product[17];

        products[0] = new FoodProduct("Turon", 35, "Mother", "Snack");
        products[1] = new FoodProduct("Lumpia", 35, "Mother", "Snack");
        products[2] = new FoodProduct("Puto Cake - Cheese", 185, "Mother", "Puto Cake");
        products[3] = new FoodProduct("Puto Cake - Salted Egg", 185, "Mother", "Puto Cake");
        products[4] = new FoodProduct("Puto Cake - Cheese & Salted Egg", 185, "Mother", "Puto Cake");
        products[5] = new FoodProduct("Puto Cake - Chocolate", 200, "Mother", "Puto Cake");
        products[6] = new FoodProduct("Puto Cake - Matcha", 200, "Mother", "Puto Cake");
        products[7] = new FoodProduct("Kakanin / Kalamay", 60, "Mother", "Kakanin");
        products[8] = new FoodProduct("Chicharon", 120, "Mother", "Snack");

        products[9] = new Drink("Original Orchata", 125, "Original");
        products[10] = new Drink("Matchata", 130, "Matcha");
        products[11] = new Drink("Chocata", 135, "Chocolate");
        products[12] = new CookiePack("Cookies - 4 pieces", 188, 4);
        products[13] = new CookiePack("Cookies - 8 mini", 198, 8);
        products[14] = new CookiePack("Cookies - 6 pieces", 268, 6);
        products[15] = new CookiePack("Cookies - 12 pieces", 498, 12);
        products[16] = new FoodProduct("Nachos", 45, "Sister", "Snack");

        return products;
    }
}