package com.singerstone.leetcode;

public class MaxSection {

    /**
     * 和最大的连续区间
     */
    public static void main(String[] args) {
        int[] array = {-1, 1, 2, 3, 4, 5, 6, -1, -2};
        new MaxSection().maxQujian(array);
    }


    /**
     * 动态规划算法
     *
     * @param array
     */
    void maxQujian(int[] array) {

        int max = 0;
        for (int i = 0; i < array.length; i++) {
            int result = maxI(array, i);
            if (result > max) {
                max = result;
            }
        }
        System.out.println(max);
    }

    /**
     * 以I结尾的最大和
     */
    int maxI(int[] array, int i) {
        if (i < 0) {
            System.out.println("error");
            return 0;
        } else if (i == 0) {
            return array[0];
        } else if (i == 1) {
            if (array[0] + array[1] > array[1]) {
                return array[0] + array[1];
            } else {
                return array[1];
            }
        } else {
            return maxI(array, i - 1) + array[i];
        }

    }
}