package com.mycompany.midtermproject;

public class DailySalesReport implements Summarizable {
    private String reportDate;
    private SalesEntry[] entries;
    private int entryCount;

    public DailySalesReport(
            String reportDate, int numberOfProducts
    ) {
        this.reportDate = reportDate;
        entries = new SalesEntry[numberOfProducts];
        entryCount = 0;
    }

    public void addEntry(SalesEntry entry) {
        entries[entryCount] = entry;
        entryCount++;
    }

    public String getReportDate() {
        return reportDate;
    }

    public double calculateSellerTotal(
            String seller
    ) {
        double total = 0;

        for (int i = 0; i < entryCount; i++) {
            if (entries[i].getProduct()
                    .getSeller().equals(seller)) {
                total += entries[i]
                        .calculateSalesAmount();
            }
        }

        return total;
    }

    public Product findTopSellingProduct() {
        int highest = 0;
        Product top = null;

        for (int i = 0; i < entryCount; i++) {
            if (entries[i].getQuantitySold() > highest) {
                highest = entries[i].getQuantitySold();
                top = entries[i].getProduct();
            }
        }

        return top;
    }

    @Override
    public void printSummary() {
        System.out.println(
                "\n===== DAILY SALES REPORT ====="
        );
        System.out.println("Date: " + reportDate);

        String[] sellers = {"Mother", "Sister"};

        for (int s = 0; s < sellers.length; s++) {
            System.out.println(
                    "\n" + sellers[s] + "'s products:"
            );

            for (int i = 0; i < entryCount; i++) {
                SalesEntry entry = entries[i];

                if (entry.getProduct().getSeller()
                        .equals(sellers[s])) {
                    System.out.printf(
                            "%s: %d x PHP %.2f = PHP %.2f%n",
                            entry.getProduct().getName(),
                            entry.getQuantitySold(),
                            entry.getProduct().getPrice(),
                            entry.calculateSalesAmount()
                    );
                }
            }
        }

        double motherTotal =
                calculateSellerTotal("Mother");

        double sisterTotal =
                calculateSellerTotal("Sister");

        System.out.printf(
                "%nMother's sales: PHP %.2f%n",
                motherTotal
        );

        System.out.printf(
                "Sister's sales: PHP %.2f%n",
                sisterTotal
        );

        System.out.printf(
                "Overall sales: PHP %.2f%n",
                motherTotal + sisterTotal
        );

        Product top = findTopSellingProduct();

        if (top == null) {
            System.out.println(
                    "Top-selling product: None (no sales)"
            );
        } else {
            int highest = 0;

            for (int i = 0; i < entryCount; i++) {
                if (entries[i].getQuantitySold()
                        > highest) {
                    highest =
                            entries[i].getQuantitySold();
                }
            }

            System.out.println(
                    "Top-selling product(s):"
            );

            for (int i = 0; i < entryCount; i++) {
                if (entries[i].getQuantitySold()
                        == highest) {
                    System.out.println(
                            entries[i].getProduct()
                                    .getName()
                            + " - " + highest + " units"
                    );
                }
            }
        }

        System.out.println(
                "=============================="
        );
    }
}