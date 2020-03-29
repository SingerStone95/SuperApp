package com.singerstone.java.impl;

import com.singerstone.java.base.AbsSellPriceStrategy;

public class NormalPriceStrategy extends AbsSellPriceStrategy {

    @Override
    public int getPriceChangeCount() {
        if (getItemDeadline() <= 0) {
            return DEFAULT_PRICE_CHANGE_COUNT * 2;
        }
        return DEFAULT_PRICE_CHANGE_COUNT;

    }

}
