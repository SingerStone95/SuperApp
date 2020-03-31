package com.singerstone.java.impl;

import com.singerstone.java.base.AbsSellPriceStrategy;

public class QiyiPriceStrategy extends AbsSellPriceStrategy {
    @Override
    public int getPriceChangeCount(int deadline) {
        if (deadline > 10) {
            return 1;
        } else if (deadline > 5) {
            return 2;
        } else {
            return 3;
        }
    }


    @Override
    protected boolean needClearPriceWhenDeadline() {
        return true;
    }
}
