package com.singerstone.java.impl;

import com.singerstone.java.base.AbsSellPriceStrategy;

public class QiyiPriceStrategy extends AbsSellPriceStrategy {
    @Override
    public int getPriceChangeCount() {
        if (getItemDeadline() > 10) {
            return 1;
        } else if (getItemDeadline() > 5) {
            return 2;
        }else {
            return 3;
        }
    }


    @Override
    protected boolean needPriceWhenDeadline() {
        return true;
    }
}
