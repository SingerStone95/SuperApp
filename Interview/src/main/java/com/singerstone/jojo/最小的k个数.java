package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

public class 最小的k个数 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, -8, 9, 4, 2, 2, 3};

        printArray(new 最小的k个数().getLeastNumbers(array, 4));
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;

    }

    void quickSort(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }
        int part = getPart(arr, start, end);
        quickSort(arr, start, part - 1);
        quickSort(arr, part + 1, end);
    }

    int getPart(int[] arr, int start, int end) {
        int partValue = arr[start];
        int part = start;
        // 3 4 5 2 6
        for (int i = start + 1; i <= end; i++) {
            if (arr[i] < partValue) {
                part++;
                swap(arr, i, part);
            }
        }
        swap(arr, start, part);
        return part;
    }

    void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
