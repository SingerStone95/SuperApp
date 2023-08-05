package com.singerstone.jojo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 最长连续序列 {

    public static void main(String[] args) {

        System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2}));
        System.out.println(longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }


    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int result = 1;
        for (int num : nums) {
            if (set.contains(num-1)){
                continue;
            }
            int count = 1;
            while (set.contains(++num)) {
                count++;
            }
            result = Math.max(count, result);
        }

        return result;

    }
}
