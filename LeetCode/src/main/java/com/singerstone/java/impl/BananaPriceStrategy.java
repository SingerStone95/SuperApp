package com.singerstone.java.impl;

public class BananaPriceStrategy extends NormalPriceStrategy {
    @Override
    public int getPriceChangeCount(int deadline) {
        return super.getPriceChangeCount(deadline) * 2;
    }
}
