package com.singerstone.java.impl;

import com.singerstone.java.base.AbsSellPriceStrategy;

public class DiamondPriceStrategy extends AbsSellPriceStrategy {
    @Override
    public int getPriceChangeCount() {
        return 0;
    }

    @Override
    protected int getChangeSellDeadline() {
        return 0;
    }
}
