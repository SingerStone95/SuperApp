package com.singerstone.java;

import com.singerstone.java.base.ItemSellWrapper;

public class BateVarietyStore {
    ItemSellWrapper[] items;

    BateVarietyStore(ItemSellWrapper[] itemSellWrapper) {
        items = itemSellWrapper;
    }

    public void updateItems() {
        for (ItemSellWrapper itemSellWrapper : items) {
            itemSellWrapper.updatePrice();
        }
    }
}
