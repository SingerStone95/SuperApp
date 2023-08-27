package com.singerstone.jojo;

public class 股票买卖最佳时机 {

    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int price : prices) {
            if (price < min) {
                min = price;
            }
            max = Math.max(max, price - min);
        }

        return max;
    }

}
