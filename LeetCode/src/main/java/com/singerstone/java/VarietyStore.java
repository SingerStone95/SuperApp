package com.singerstone.java;

/**
 * 欢迎加入 8-12 便利店
 * @author connorlu
 */
class VarietyStore {
    Item[] items;

    public VarietyStore(Item[] items) {
        this.items = items;
    }

    public void updateItems() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("茅台")
                    && !items[i].name.equals("奇异果")) {
                if (items[i].price > 0) {
                    if (!items[i].name.equals("钻戒")) {
                        items[i].price = items[i].price - 1;
                    }
                }
            } else {
                if (items[i].price < 50) {
                    items[i].price = items[i].price + 1;

                    if (items[i].name.equals("奇异果")) {
                        if (items[i].sellDeadline < 11) {
                            if (items[i].price < 50) {
                                items[i].price = items[i].price + 1;
                            }
                        }

                        if (items[i].sellDeadline < 6) {
                            if (items[i].price < 50) {
                                items[i].price = items[i].price + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("钻戒")) {
                items[i].sellDeadline = items[i].sellDeadline - 1;
            }

            if (items[i].sellDeadline < 0) {
                if (!items[i].name.equals("茅台")) {
                    if (!items[i].name.equals("奇异果")) {
                        if (items[i].price > 0) {
                            if (!items[i].name.equals("钻戒")) {
                                items[i].price = items[i].price - 1;
                            }
                        }
                    } else {
                        items[i].price = items[i].price - items[i].price;
                    }
                } else {
                    if (items[i].price < 50) {
                        items[i].price = items[i].price + 1;
                    }
                }
            }
        }
    }
}
