package com.singerstone.leetcode;

import java.util.Arrays;

public class TwoSum {
    /**
     * 求两数之和==给定值的下标
     */

    public static void main(String[] args) {

        int[] array = {1, 2, 4, 7, 9, 4, 3, 2};
        Util.printArray(new TwoSum().towSum(array, 3));
    }

    int[] towSum(int[] array, int sum) {

        Arrays.sort(array);
        int[] values = new int[2];
        int i = 0, j = array.length - 1;
        while (i < j) {
            if (array[i] + array[j] > sum) {
                j--;
            } else if (array[i] + array[j] < sum) {
                i++;
            } else {
                values[0] = array[i];
                values[1] = array[j];
                break;
            }
        }
        // Sort.printArray(values);

        int size = 0;
        int[] result = new int[2];
        for (int k = 0; k < array.length; k++) {
            if (array[k] == values[0] || array[k] == values[1]) {
                result[size++] = k;
                if (size == 2) {
                    break;
                }

            }
        }
        return result;

    }
}