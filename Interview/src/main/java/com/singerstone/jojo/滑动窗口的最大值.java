package com.singerstone.jojo;


import static com.singerstone.jojo.二叉树的遍历.printArray;

import java.util.LinkedList;

public class 滑动窗口的最大值 {
    public static void main(String[] args) {
        int[] array = new int[]{4,3,11};
        printArray(new 滑动窗口的最大值().maxSlidingWindow(array, 3));

    }

    // nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int m = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            // 一直添加到队列里面比当前这个数大的后面
            while (!queue.isEmpty() && queue.getLast() < nums[i]) {
                queue.removeLast();
            }
            queue.addLast(nums[i]);
        }
        result[m++] = queue.getFirst();
        for (int i = k; i < nums.length; i++) {
            if (nums[i - k] == queue.getFirst()) {
                queue.removeFirst();
            }
            while (!queue.isEmpty() && queue.getLast() < nums[i]) {
                queue.removeLast();
            }
            queue.addLast(nums[i]);
            result[m++] = queue.getFirst();
        }

        return result;

    }
}
