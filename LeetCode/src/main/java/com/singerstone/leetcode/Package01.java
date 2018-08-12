package com.singerstone.leetcode;

/**
 * 01背包问题
 */
public class Package01 {
    /**
     * 二维数组的大小
     */
    public static int M = 0;
    public static int N = 0;

    public static void main(String[] args) {
        int[] v = { 6, 3, 5, 4, 6 };
        int[] w = { 2, 2, 6, 5, 4 };
        System.out.println(new Package01().getMaxValue(v, w, 10));

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
                if (j >= w[i]) {
                    resultArray[i][j] = Util.max(resultArray[i - 1][j], resultArray[i - 1][j - w[i]] + v[i]);
                } else {
                    resultArray[i][j] = resultArray[i - 1][j];
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

        /**
         * 逆向推导出一种符合最大价值的组合
         */
        int[] x = new int[7];
        for (int i = n; i > 1; i--) {
            if (resultArray[i][c] == resultArray[i - 1][c])
                x[i] = 0;
            else {
                x[i] = 1;
                c -= w[i];
            }
        }
        x[1] = (resultArray[1][c] > 0) ? 1 : 0;
        Util.printArray(x);
        System.out.println("\n");
        return result;

    }

}