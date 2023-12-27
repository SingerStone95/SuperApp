package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

/**
 * 兄弟问题：{@link 数组中第K个最大元素}
 */
public class 最小的k个数 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 2, 3, 4, 1, 13, -8, 9, 4};

        printArray(new 最小的k个数().getLeastNumbers(array, 6));

        printArray(new 最小的k个数().getLeastNumbers2(array, 6));
    }


    /**
     * 快选 推荐 面试写快选
     *   1.快选出第k小的数
     *   2.然后返回0-k
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        QSelect(arr, 0, arr.length - 1, k);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;

    }


    /**
     * 这里不需要设计返回值，因为直接操作的原始数组
     */
    void QSelect(int[] arr, int start, int end, int k) {
        if (start > end) {
            return;
        }
        int part = getPart(arr, start, end);
        // 这里用的是区间长度
        int len = part - start + 1;
        if (len == k) {
            return;
        } else if (len > k) {
            QSelect(arr, start, part - 1, k);
        } else {
            QSelect(arr, part + 1, end, k - len);
        }
    }


    // 快排 不推荐，面试用快选

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
