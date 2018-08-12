package com.singerstone.leetcode;

/**
 * 排序算法
 */

public class Sort {

    /**
     * 测试入口
     */
    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] array = {7, 3, 5, 1, 2, 8, 9, 2, 6, -1, 4};
    }


    /**
     * 交换数组两个元素
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 冒泡
     */
    int[] maopaoSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 1; j < length - i; j++) {
                if (array[j - 1] > array[j]) {
                    swap(array, j, j - 1);
                }
            }

        }
        return array;

    }

    /**
     * 选择
     */
    int[] chooseSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i + 1;
            for (int j = i + 1; j < length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            swap(array, minIndex, i);
        }
        return array;
    }

    /**
     * 插入
     */

    int[] insertSort(int[] array) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > array[j + 1]) {
                    // swip(array[j], array[j + 1]);
                    swap(array, j, j + 1);
                } else {
                    break;
                }

            }
        }

        return array;
    }


}
