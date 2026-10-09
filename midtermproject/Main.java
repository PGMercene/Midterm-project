package com.mycompany.midtermproject;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Product[] products = ProductCatalog.createProducts();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("    FAMILY FOOD BUSINESS SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Customer");
            System.out.println("2. Owner");
            System.out.println("3. Exit");

            choice = readNumber("Enter choice: ", 1, 3);

            switch (choice) {
                case 1:
                    customerMode();
                    break;
                case 2:
                    ownerMode();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
            }
        } while (choice != 3);

        scanner.close();
    }

    public static void customerMode() {
        System.out.println("\n===== CUSTOMER MODE =====");
        System.out.println("1. Regular order");
        System.out.println("2. Package / Event order");

        int type = readNumber("Enter choice: ", 1, 2);
        CustomerOrder order = new CustomerOrder(
                type == 1 ? "Regular" : "Package / Event"
        );

        char again;

        do {
            System.out.println("\n===== PRODUCT MENU =====");

            for (int i = 0; i < products.length; i++) {
                System.out.printf("%d. %s - PHP %.2f%n",
                        i + 1,
                        products[i].getDescription(),
                        products[i].getPrice());
            }

            int itemChoice = readNumber(
                    "Select product: ", 1, products.length
            );

            Product selected = products[itemChoice - 1];

            int maximum;
            if (selected instanceof CookiePack) {
                maximum = order.getAvailableSlots();
            } else {
                maximum = 1000000;
            }

            int quantity = readNumber(
                    "Enter quantity: ", 1, maximum
            );

            if (selected instanceof CookiePack) {
                CookiePack pack = (CookiePack) selected;

                for (int i = 1; i <= quantity; i++) {
                    String flavors = chooseFlavors(
                            pack.getPiecesPerPack(), i
                    );

                    order.addItem(
                            new OrderItem(pack, 1, flavors)
                    );
                }
            } else {
                order.addItem(
                        new OrderItem(selected, quantity, "")
                );
            }

            System.out.printf(
                    "Added: %d x %s - PHP %.2f%n",
                    quantity,
                    selected.getName(),
                    selected.calculateAmount(quantity)
            );

            if (order.isFull()) {
                System.out.println("The order is full.");
                again = 'N';
            } else {
                again = readYesNo(
                        "Add another product? (Y/N): "
                );
            }
        } while (again == 'Y');

        System.out.println("\nDiscount:");
        System.out.println("1. None");
        System.out.println("2. Student (20%)");
        System.out.println("3. PWD (20%)");

        int discount = readNumber(
                "Enter choice: ", 1, 3
        );

        if (discount == 2) {
            order.setDiscountType("Student");
        } else if (discount == 3) {
            order.setDiscountType("PWD");
        }

        System.out.println("\n1. Pickup");
        System.out.println("2. Delivery (PHP 50)");

        int fulfillment = readNumber(
                "Enter choice: ", 1, 2
        );

        order.setDelivery(fulfillment == 2);
        order.printSummary();
    }

    public static String chooseFlavors(
            int pieces, int packNumber
    ) {
        String[] flavors = {
            "Matcha",
            "Chocolate Chip",
            "Crinkle",
            "Oatmeal"
        };

        while (true) {
            int[] amounts = new int[4];
            int total = 0;

            System.out.println(
                    "\nCookie flavors for pack "
                    + packNumber
                    + " (choose "
                    + pieces
                    + " pieces):"
            );

            for (int i = 0; i < flavors.length; i++) {
                amounts[i] = readNumber(
                        flavors[i] + ": ", 0, pieces
                );
                total += amounts[i];
            }

            if (total == pieces) {
                String details = "";

                for (int i = 0; i < flavors.length; i++) {
                    if (amounts[i] > 0) {
                        if (!details.equals("")) {
                            details += ", ";
                        }

                        details += amounts[i]
                                + " " + flavors[i];
                    }
                }

                return details;
            }

            System.out.println(
                    "The flavors must add up to "
                    + pieces + ". Try again."
            );
        }
    }

    public static void ownerMode() {
        System.out.println("\n===== OWNER MODE =====");

        String date = readDate();
        DailySalesReport report = new DailySalesReport(
                date, products.length
        );

        System.out.println("\n===== PRODUCTS =====");

        for (int i = 0; i < products.length; i++) {
            System.out.printf(
                    "%d. %s - PHP %.2f%n",
                    i + 1,
                    products[i].getName(),
                    products[i].getPrice()
            );
        }

        System.out.println(
                "\nEnter the quantity sold for each product:"
        );

        for (int i = 0; i < products.length; i++) {
            int sold = readNumber(
                    (i + 1) + ". "
                    + products[i].getName()
                    + " quantity sold: ",
                    0, 1000000
            );

            report.addEntry(
                    new SalesEntry(products[i], sold)
            );
        }

        report.printSummary();
    }

    public static String readDate() {
        while (true) {
            int year = readNumber(
                    "Year (e.g. 2026): ", 1, 9999
            );

            int month = readNumber(
                    "Month (1-12): ", 1, 12
            );

            int day = readNumber(
                    "Day: ", 1, 31
            );

            int daysInMonth = 31;

            if (month == 4 || month == 6
                    || month == 9 || month == 11) {
                daysInMonth = 30;
            } else if (month == 2) {
                boolean leapYear =
                        year % 400 == 0
                        || (year % 4 == 0
                        && year % 100 != 0);

                if (leapYear) {
                    daysInMonth = 29;
                } else {
                    daysInMonth = 28;
                }
            }

            if (day <= daysInMonth) {
                return String.format(
                        "%04d-%02d-%02d",
                        year, month, day
                );
            }

            System.out.println(
                    "That date does not exist. Please try again."
            );
        }
    }

    public static int readNumber(
            String prompt, int minimum, int maximum
    ) {
        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                scanner.nextLine();

                if (number >= minimum
                        && number <= maximum) {
                    return number;
                }
            } else {
                scanner.nextLine();
            }

            System.out.println(
                    "Enter a number from "
                    + minimum + " to " + maximum + "."
            );
        }
    }

    public static char readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);

            String answer = scanner.nextLine()
                    .trim().toUpperCase();

            if (answer.equals("Y")) {
                return 'Y';
            } else if (answer.equals("N")) {
                return 'N';
            }

            System.out.println("Enter Y or N.");
        }
    }
}