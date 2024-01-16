package com.singerstone.jojo;

import java.util.HashMap;

public class 和为K的子数组个数 {
    public static void main(String[] args) {
        int[] array = new int[]{1, 1, 1};
        System.out.println(new 和为K的子数组个数().subarraySum(array, 2));

    }


    // 前提：一定是连续子数组，负责规则不适用
    // 用 pre[i] 表示从 0-i 的和
    // 在数组中 存在 pre[j]-pre[i](j>i) =k   区间长度:j-i+1
    // 所以该方法转化为在 pre 组书中寻找有 x 组差为 k 的组合
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        int preSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 如果 preSum == k 直接结果加1
        for (int num : nums) {
            preSum += num;
            Integer other = map.get(preSum - k); // 可以和当前前缀数组相减构成和为K的子数组
            if (other != null) {
                result += other;
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return result;
    }
}
