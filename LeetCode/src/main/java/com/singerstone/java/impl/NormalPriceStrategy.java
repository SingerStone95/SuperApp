package com.singerstone.java.impl;

import com.singerstone.java.base.AbsSellPriceStrategy;

public class NormalPriceStrategy extends AbsSellPriceStrategy {

    @Override
    public int getPriceChangeCount(int deadline) {
        if (deadline <= 0) {
            return DEFAULT_PRICE_CHANGE_COUNT * 2;
        }
        return DEFAULT_PRICE_CHANGE_COUNT;

    }

}
