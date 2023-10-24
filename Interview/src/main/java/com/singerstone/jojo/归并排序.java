package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/10/24 22:34
 * @see {@link }
 */
public class 归并排序 {

    public static void main(String[] args) {

        int[] array = new int[]{2, 4, 2, 7, 4, 2, 3, 4, 5};
        new 归并排序().mergeSort(array, 0, array.length - 1);
        printArray(array);
    }

    void mergeSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        doMerge(array, left, mid, right);

    }

    private void doMerge(int[] array, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int index = 0;
        int i = left;
        int j = mid + 1;
        while (i <= mid || j <= right) {
            if (i > mid) {
                tmp[index++] = array[j++];
            } else if (j > right) {
                tmp[index++] = array[i++];
            } else {
                if (array[i] > array[j]) {
                    tmp[index++] = array[j++];
                } else {
                    tmp[index++] = array[i++];
                }
            }
        }
        for (int k = left; k <= right; k++) {
            array[k] = tmp[k - left];
        }
    }

}
