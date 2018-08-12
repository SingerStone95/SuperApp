package com.singerstone.leetcode;

/**
 * 快排专用文件
 */
public class Qsort {
    public static void main(String[] args) {
        int[] array = { 3, 4, 2, 3, 9, 4, 1, 9, 7, -1, 8, 9, 12 };
        new Qsort().qsort(array, 0, array.length - 1);
        Util.printArray(array);
    }

    void qsort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        int pPosition = getPPosition(array, left, right);
        qsort(array, left, pPosition - 1);
        qsort(array, pPosition + 1, right);

    }

    int getPPosition(int array[], int left, int right) {
        int valueP = array[left];
        int indexP = left;
        for (int i = left + 1; i <= right; i++) { //***indexP始终指向左边部分的最后一个***
            if (array[i] < valueP) {
                Util.swip(array, i, indexP + 1);
                indexP++;
            }
        }
        Util.swip(array, indexP, left);
        return indexP;
    }
}