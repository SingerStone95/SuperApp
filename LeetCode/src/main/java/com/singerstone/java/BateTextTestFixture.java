package com.singerstone.java;

import com.singerstone.java.base.Item;
import com.singerstone.java.base.ItemSellWrapper;
import com.singerstone.java.impl.BananaPriceStrategy;
import com.singerstone.java.impl.DiamondPriceStrategy;
import com.singerstone.java.impl.MaoTaiPriceStrategy;
import com.singerstone.java.impl.NormalPriceStrategy;
import com.singerstone.java.impl.QiyiPriceStrategy;

public class BateTextTestFixture {

    public static void main(String[] args) {
        ItemSellWrapper[] items = new ItemSellWrapper[]{
                new ItemSellWrapper(new Item("面包", 10, 20), new NormalPriceStrategy()),
                new ItemSellWrapper(new Item("茅台", 2, 0), new MaoTaiPriceStrategy()),
                new ItemSellWrapper(new Item("方便面", 5, 7), new NormalPriceStrategy()),
                new ItemSellWrapper(new Item("钻戒", 0, 80), new DiamondPriceStrategy()),
                new ItemSellWrapper(new Item("钻戒", -1, 80), new DiamondPriceStrategy()),
                new ItemSellWrapper(new Item("奇异果", 15, 20), new QiyiPriceStrategy()),
                new ItemSellWrapper(new Item("奇异果", 10, 49), new QiyiPriceStrategy()),
                new ItemSellWrapper(new Item("奇异果", 5, 49), new QiyiPriceStrategy()),
                new ItemSellWrapper(new Item("香蕉", 3, 6), new BananaPriceStrategy())


        };
        BateVarietyStore app = new BateVarietyStore(items);

        int days = 20;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- 第 " + i + " 天 --------");
            System.out.println("名称, 保质期, 价钱");
            for (ItemSellWrapper item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateItems();
        }
    }
}
