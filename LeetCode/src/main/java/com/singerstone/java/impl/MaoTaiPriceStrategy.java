package com.singerstone.java.impl;

import com.singerstone.java.base.AbsSellPriceStrategy;

public class MaoTaiPriceStrategy extends AbsSellPriceStrategy {
    @Override
    public int getPriceChangeCount() {
        if (getItemDeadline() <= 0) {
            return 2;
        }
        return 1;
    }
}
