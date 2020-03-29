package com.singerstone.java.base;

public abstract class AbsSellPriceStrategy {
    public static int DEFAULT_PRICE_CHANGE_COUNT = -1;
    public static int DEFAULT_DEADLINE_CHANGE_COUNT = -1;
    public static int DEFAULT_MAX_PRICE = 50;
    public static int DEFAULT_MIN_PRICE = 0;

    private Item mItem;

    void update(Item item) {
        mItem = item;
        int oldPrice = item.price;
        int newPrice = oldPrice + getPriceChangeCount();
        if (newPrice > getMaxPrice()) {
            newPrice = getMaxPrice();
        }
        if (newPrice < getMinPrice()) {
            newPrice = getMinPrice();
        }
        int oldDeadline = item.sellDeadline;
        int newDeadline = oldDeadline + getChangeSellDeadline();
        if (needPriceWhenDeadline() && newDeadline < 0) { //过期清零
            newPrice = 0;
        }
        item.price = newPrice;
        item.sellDeadline = newDeadline;

    }

    public int getItemDeadline() {
        return mItem.sellDeadline;
    }

    public abstract int getPriceChangeCount();

    protected int getChangeSellDeadline() {
        return DEFAULT_DEADLINE_CHANGE_COUNT;
    }

    protected int getMinPrice() {
        return DEFAULT_MIN_PRICE;
    }

    protected int getMaxPrice() {
        return DEFAULT_MAX_PRICE;
    }

    protected boolean needPriceWhenDeadline() {
        return false;
    }

}
