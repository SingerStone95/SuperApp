package com.singerstone.java.impl;

public class BananaPriceStrategy extends NormalPriceStrategy {
    @Override
    public int getPriceChangeCount() {
        return super.getPriceChangeCount() * 2;
    }
}
