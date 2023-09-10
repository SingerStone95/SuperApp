package com.singerstone.jojo;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/10 11:54 1. 一个数组中 分为两部分 众数 非众数 2. 非众数 一定能被众数抵消
 */
public class 数组中超过一半的数字 {

    public static void main(String[] args) {
        System.out.println(new 数组中超过一半的数字().majorityElement(new int[]{2,3,4,1,1,2,2,2}));
    }

    public int majorityElement(int[] nums) {
        int m = 0;
        int sum = 0;
        for (int num : nums) {
            if (sum == 0) {
                m = num;
            }
            sum += num == m ? 1 : -1;

        }

        return m;

    }
}
