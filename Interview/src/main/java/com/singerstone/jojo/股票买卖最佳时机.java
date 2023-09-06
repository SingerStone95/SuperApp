package com.singerstone.jojo;

public class 股票买卖最佳时机 {

    public static void main(String[] args) {

        int[] array = new int[]{1, 2, 5};
        System.out.println(new 股票买卖最佳时机().maxProfit3(array));
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

    // 表示第i天持有股票的最大值     dp[i][0]=max(dp[i-1][0],dp[i-2][1]-prices[i]);
    // 表示第i天不持有股票的最大值    dp[i][1]=max(dp[i-1][1],dp[i-1][0]+prices[i]);
    public int maxProfit3(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int dp[][] = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[1][0] = Math.max(-prices[0], -prices[1]);
        dp[1][1] = Math.max(0, prices[1] - prices[0]);
        for (int i = 2; i < prices.length; i++) {

            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }
}
