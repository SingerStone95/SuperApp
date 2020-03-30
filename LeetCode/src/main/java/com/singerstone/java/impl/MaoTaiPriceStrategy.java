package com.singerstone.java.impl;

import com.singerstone.java.base.AbsSellPriceStrategy;

public class MaoTaiPriceStrategy extends AbsSellPriceStrategy {
    @Override
    public int getPriceChangeCount(int deadline) {
        if (deadline <= 0) {
            return 2;
        }
        return 1;
    }
}
