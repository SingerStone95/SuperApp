package com.singerstone.jojo;

public class 股票买卖最佳时机 {

    public static void main(String[] args) {

        int[] array=new int[]{6,5,4};
        System.out.println(new 股票买卖最佳时机().maxProfit2(array));
    }

    // 只能进行一次交易
    //输入：prices = [7,1,5,3,6,4]
    //输出：5
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


    // 能进行多次交易
    //输入：prices = [7,1,5,3,6,4]
    //输出：7
    public int maxProfit2(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                result += diff;
            }
        }
        return result;
    }

    //包含冷冻期
    //输入: prices = [1,2,3,0,2]
    //输出: 3
    //解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

    public int maxProfit3(int[] prices) {

        return 0;
    }
}
