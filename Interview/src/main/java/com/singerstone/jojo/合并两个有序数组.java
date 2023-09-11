package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

public class 合并两个有序数组 {


    public static void main(String[] args) {
        int[] array1 = new int[]{1, 3, 5, 0, 0, 0};
        int[] array2 = new int[]{2, 4, 6};
        new 合并两个有序数组().merge(array1, 3, array2, 3);
        printArray(array1);

    }

    // 1.从前往后遍历，会有覆盖的情况发生，必须要额外的空间，最后还要赋值给 nums1
    // 2.从后往前遍历，可以证明不会被覆盖，不需要额外的空间，可以节省空间
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m - 1;
        int right = n - 1;
        int p = m + n - 1;
        while (left >= 0 || right >= 0) {
            if (left < 0) {
                nums1[p--] = nums2[right--];
            } else if (right < 0) {
                nums1[p--] = nums1[left--];
            } else {
                if (nums1[left] > nums2[right]) {
                    nums1[p--] = nums1[left--];
                } else {
                    nums1[p--] = nums2[right--];
                }
            }
        }
    }
}
