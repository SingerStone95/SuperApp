package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/10/24 22:33
 * @see {@link }
 */
public class 快速排序 {

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 1, 6, 3, 2, 1, 4, 3};
        new 快速排序().QSort(array, 0, array.length - 1);
        printArray(array);

    }

    void QSort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        int part = getPart(array, left, right);
        QSort(array, left, part - 1);
        QSort(array, part + 1, right);
    }

    int getPart(int[] array, int left, int right) {
        int result = left;
        int pv = array[left];
        //4,2,5,3 result 指向最后一个 <= part的数
        for (int i = left + 1; i <= right; i++) {
            if (array[i] <= pv) {
                swap(array, i, ++result);
            }
        }
        swap(array, result, left); // 将part交换过来
        return result;

    }

    private void swap(int[] array, int i, int i1) {
        if (i == i1) {
            return;
        }
        int tmp = array[i];
        array[i] = array[i1];
        array[i1] = tmp;
    }

}
