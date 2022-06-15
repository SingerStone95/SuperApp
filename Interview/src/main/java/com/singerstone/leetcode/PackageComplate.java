package com.singerstone.leetcode;

/**
 * 完全背包
 */

public class PackageComplate {

    public static int M = 0;
    public static int N = 0;

    public static void main(String[] args) {
        int[] v = { 1, 3, 5, 9 }; // 前面的0是一定要填充的
        int[] w = { 2, 3, 4, 7 };
        System.out.println(new PackageComplate().getMaxValue(v, w, 10));

    }

    int getMaxValue(int[] ov, int[] ow, int c) {
        M = ov.length + 1;
        N = c + 1;
        /**
         * 给两个数组填充一个0
         */
        int[] v = new int[ov.length + 1];
        int[] w = new int[ow.length + 1];

        for (int i = 0; i < w.length; i++) {
            if (i == 0) {
                v[i] = 0;
                w[i] = 0;
            } else {
                v[i] = ov[i - 1];
                w[i] = ow[i - 1];
            }
        }

        int[][] resultArray = new int[M][N];
        int n = ow.length;
        // 这个表的第一行第一列都是0
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= c; j++) {
                for (int k = 0; k * w[i] <= j; k++) {
                    if (j >= k * w[i]) {
                        resultArray[i][j] = Util.max(resultArray[i - 1][j],
                                resultArray[i - 1][j - k * w[i]] + k * v[i]);
                    } else {
                        resultArray[i][j] = resultArray[i - 1][j];
                    }
                }
            }
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= c; j++) {
                if (resultArray[i][j] > result) {
                    result = resultArray[i][j];
                }
            }

        }

        Util.printArray(resultArray);


        return result;

    }

}