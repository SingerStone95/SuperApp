package com.singerstone.jojo;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/10/3 11:09
 * @see {@link }
 */
public class 寻找两个有序数组的中位数 {

    public static void main(String[] args) {
        int[] array1 = new int[]{1, 3, 5};
        int[] array2 = new int[]{2, 4};
        System.out.println(new 寻找两个有序数组的中位数().findMedianSortedArrays(array1, array2));

    }

    /**
     * 根据数的个数是奇数 偶数转化为找两个数组的第 K 个数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int totalLen = len1 + len2;
        if (totalLen % 2 == 1) {
            return findK(nums1, nums2, totalLen / 2 + 1);
        } else {
            return ((float) findK(nums1, nums2, totalLen / 2 + 1) + findK(nums1, nums2, totalLen / 2)) / 2;
        }
    }

    // k 是从 1 开始计数,所以使用 k 进行 index 计算的时候都要 -1
    public int findK(int[] nums1, int[] nums2, int k) {
        int left1 = 0;
        int left2 = 0;
        while (true) {
            if (left1 >= nums1.length) {
                return nums2[left2 + k - 1];
            }
            if (left2 >= nums2.length) {
                return nums1[left1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[left1], nums2[left2]);
            }
            // 一般情况
            int half = k / 2;
            // 可能越界
            int index1 = Math.min(left1 + half, nums1.length) - 1;
            int index2 = Math.min(left2 + half, nums2.length) - 1;

            if (nums1[index1] <= nums2[index2]) {
                k = k - (index1 - left1 + 1);
                left1 = index1 + 1;

            } else {
                k = k - (index2 - left2 + 1);
                left2 = index2 + 1;
            }
        }

    }

}
