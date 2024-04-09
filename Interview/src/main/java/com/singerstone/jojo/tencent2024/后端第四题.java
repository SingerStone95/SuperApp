package com.singerstone.jojo.tencent2024;

import java.util.Scanner;

public class 后端第四题 {

    /**
     * 4. 小红拿到了一个数组，她准备将数组分割成 k 段，使得每段内部做按位异或后，再全部求和。小红希望最终这个和尽可能大，你能帮帮她吗？
     * <p>
     * 输入描述 输入包含两行。
     * 第一行两个正整数n，k，（1＜k≤n≤400），分别表示数组的长度和要分的段数。
     * 第二行n个整数a1（0≤a≤10°）
     * 表示数组a 的元素。
     * 输出描述
     * 输出一个正整数
     * <p>
     * 异或有如下性质，自反性， 0^1^1=0 1^1^1=1 异或同一个数两次就还原了,可以推导出
     * pre 表示 ^(0-i)
     * pre[j]^pre[i]=^[i+1~j] (j>i)
     */
    public static void main(String[] args) {
/*        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }*/
        // 5 3 , 1 0 1 1 0

        int k = 2;
        int[] array = new int[]{1, 1, 1, 2, 3, 4}; // 10
        int[] pre = new int[array.length];
        int tmp = 0;
        for (int i = 0; i < array.length; i++) {
            tmp ^= array[i];
            pre[i] = tmp;
        }
        int[][] dp = new int[array.length][k + 1]; // 以 i 结尾的子数组，分为 k 段的最大和
        for (int i = 0; i < array.length; i++) {
            dp[i][1] = pre[i];
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) { // 用 j 分割 0-i 为 [0-j] (j-i]
                for (int p = 2; p <= k; p++) { // 至少都是分 1 段
                    dp[i][p] = Math.max(dp[i][p], dp[j][p - 1] + pre[j] ^ pre[i]);
                }

            }
        }
        System.out.println(dp[array.length - 1][k]);
    }

    // 1 1 1  , 2
    //
}
