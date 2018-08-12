package com.singerstone.leetcode;

/**
 * 归并排序
 */

public class Merge {

    public static void main(String[] args) {
        int[] array = {3, 4, 2, 3, 9, 4, 1, 9, 7};
        new Merge().mergeSort(array, 0, array.length - 1);
        Util.printArray(array);

    }

    /**
     * arr[l,mid] 和 arr[mid+1,r] 两部分进行归并
     */
    private void merge(int[] arr, int l, int mid, int r) {
        int[] aux = new int[r - l + 1];
        for (int i = l; i <= r; i++) {
            aux[i - l] = arr[i];
        }
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) { //左半边走完
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) { //右半边走完
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l] < aux[j - l]) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    /**
     * @param arr 待排序数组
     * @param l   其实元素角标 0
     * @param r   最后一个元素角标 n -1
     */
    private void mergeSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        if (arr[mid] <= arr[mid + 1]) {
            return;
        }
        merge(arr, l, mid, r);
    }

}