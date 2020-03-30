package com.singerstone.java.base;

import com.singerstone.java.base.AbsSellPriceStrategy;
import com.singerstone.java.base.Item;

public class ItemSellWrapper<T extends AbsSellPriceStrategy> {
    private T mStrategy;
    private Item mItem;

    public ItemSellWrapper(Item item, T strategy) {
        mItem = item;
        mStrategy = strategy;
        //todo check 数据
    }

    public void updatePrice() {
        if (mItem == null || mStrategy == null) {
            return;
        }
        mStrategy.update(mItem);
    }

    public int getPrice() {
        return mItem.price;
    }

    @Override
    public String toString() {
        return mItem.toString();
    }
}
