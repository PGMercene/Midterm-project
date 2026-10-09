package com.mycompany.midtermproject;

public class CookiePack extends Product {
    private int piecesPerPack;

    public CookiePack(
            String name, double price, int piecesPerPack
    ) {
        super(name, price, "Sister");
        this.piecesPerPack = piecesPerPack;
    }

    public int getPiecesPerPack() {
        return piecesPerPack;
    }

    @Override
    public String getDescription() {
        return getName() + " ("
                + piecesPerPack + " cookies)";
    }
}