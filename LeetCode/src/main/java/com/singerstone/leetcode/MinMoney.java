package com.singerstone.leetcode;

public class MinMoney {

    /**
     * 
     * 硬币找零：动态规划算法 
     * 
     * @param values:保存每一种硬币的币值的数组 
     * 
     * @param valueKinds:币值不同的硬币种类数量，即coinValue[]数组的大小 
     * 
     * @param money:需要找零的面值 
     * 
     * @param coinsUsed:保存面值为i的纸币找零所需的最小硬币数 
     * 
     * 
     */

    public static void makeChange(int[] values, int valueKinds, int money, int[] coinsUsed, int[] coinTrack) {
        coinsUsed[0] = 0;
        for (int cents = 1; cents <= money; cents++) {
            int minCoins = Integer.MAX_VALUE;
            int maxKind = 0; // 保存每一个最优组合中最大的硬币面值，用于反向查找组合
            for (int kind = 0; kind < valueKinds; kind++) {
                if (values[kind] <= cents) {
                    int temp = coinsUsed[cents - values[kind]] + 1;
                    if (temp < minCoins) {
                        minCoins = temp;
                        maxKind = kind;
                    }

                }
            }
            // 保存最小硬币数  
            coinsUsed[cents] = minCoins;
            coinTrack[cents] = values[maxKind];
            System.out.print("money:" + (cents) + "\r\tmincount:" + coinsUsed[cents]);
            System.out.print("\r\tdetail:");
            trackPrint(cents, coinTrack);
            System.out.println();
        }
    }

    private static void trackPrint(int m, int[] coinTrack) {
        if (m == 0) {
            return;
        } else {
            System.out.print(coinTrack[m] + " ");
            trackPrint(m - coinTrack[m], coinTrack);
        }
    }

    public static void main(String[] args) {
        int[] coinValue = new int[] { 1, 5, 10, 20, 25 };
        // 需要找零的面值  
        int money = 65;
        // 保存每一个面值找零所需的最小硬币数，0号单元舍弃不用，所以要多加1  
        int[] coinsUsed = new int[money + 1];
        int[] coinTrack = new int[money + 1];
        for (int i = 1; i <= money; i++) {
            coinsUsed[i] = 0;
            coinTrack[i] = 0;
        }
        makeChange(coinValue, coinValue.length, money, coinsUsed, coinTrack);
    }
}