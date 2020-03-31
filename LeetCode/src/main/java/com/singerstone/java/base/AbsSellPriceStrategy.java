package com.singerstone.java.base;

public abstract class AbsSellPriceStrategy {
    public static int DEFAULT_PRICE_CHANGE_COUNT = -1;
    public static int DEFAULT_DEADLINE_CHANGE_COUNT = -1;
    public static int DEFAULT_MAX_PRICE = 50;
    public static int DEFAULT_MIN_PRICE = 0;


    void update(Item item) {
        //price
        int oldPrice = item.price;
        int newPrice = oldPrice + getPriceChangeCount(item.sellDeadline);
        if (needLimitPrice()) { //保值的物品不受最大最小限制
            if (newPrice > getMaxPrice()) {
                newPrice = getMaxPrice();
            }
            if (newPrice < getMinPrice()) {
                newPrice = getMinPrice();
            }
        }
        //deadline
        int oldDeadline = item.sellDeadline;
        int newDeadline = oldDeadline + getChangeSellDeadline();
        if (needClearPriceWhenDeadline() && newDeadline < 0) { //过期清零
            newPrice = 0;
        }
        item.price = newPrice;
        item.sellDeadline = newDeadline;

    }


    public abstract int getPriceChangeCount(int deadline);

    protected int getChangeSellDeadline() {
        return DEFAULT_DEADLINE_CHANGE_COUNT;
    }

    protected int getMinPrice() {
        return DEFAULT_MIN_PRICE;
    }

    protected int getMaxPrice() {
        return DEFAULT_MAX_PRICE;
    }

    protected boolean needClearPriceWhenDeadline() {
        return false;
    }

    protected boolean needLimitPrice() {
        return true;
    }

}
