package com.singerstone.leetcode;

public class MaxSumArray {

    /**
     * 最大连续区间和
     */
    public static void main(String[] args) {

        int[] array = {-1, 2, 4, -5, 1, 2, -1};

        System.out.println(new MaxSumArray().getMaxSumArray(array));
    }

    int getMaxSumArray(int[] array) {
        int l = array.length;
        int max = 0;
        int[] list = new int[l];
        list[0] = array[0];
        for (int i = 1; i < l; i++) {
            list[i] = Util.max(list[i - 1], 0) + array[i];
        }
        Util.printArray(list);
        for (int i = 0; i < l; i++) {
            if (list[i] > max) {
                max = list[i];
            }
        }
        return max;

    }
}