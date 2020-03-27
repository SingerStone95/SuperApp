package com.singerstone.java;

public class TexttestFixture {
    public static void main(String[] args) {
        System.out.println("欢迎来到 8-12 便利店!");

        Item[] items = new Item[] {
                new Item("面包", 10, 20),
                new Item("茅台", 2, 0),
                new Item("方便面", 5, 7),
                new Item("钻戒", 0, 80),
                new Item("钻戒", -1, 80),
                new Item("奇异果", 15, 20),
                new Item("奇异果", 10, 49),
                new Item("奇异果", 5, 49),
                //TODO 香蕉 这个商品的管理逻辑还没有完成
                new Item("香蕉", 3, 6) };

        VarietyStore app = new VarietyStore(items);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- 第 " + i + " 天 --------");
            System.out.println("名称, 保质期, 价钱");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateItems();
        }
    }

}
