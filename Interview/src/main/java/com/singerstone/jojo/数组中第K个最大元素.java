package com.singerstone.jojo;

public class 数组中第K个最大元素 {

    public static void main(String[] args) {
        int[] array = new int[]{3, 3, 6, 1, 2, 3, 4, 5, 6};
        System.out.println(new 数组中第K个最大元素().findKthLargest(array, 3));

    }

    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, nums.length - k, 0, nums.length - 1);

    }

    public int findKthLargest(int[] nums, int k, int left, int right) {
        if (left > right) {
            return -1;
        }

        int part = getPart(nums, left, right);
        int pk = part - left; // part 当前是区间 [start-end] pk 小的数
        if (k == pk) {
            return nums[part];
        } else if (k < pk) {
            return findKthLargest(nums, k, left, part - 1);
        } else {
            // k-pk-1 从 0 开始计数
            return findKthLargest(nums, k - pk - 1, part + 1, right);
        }

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
