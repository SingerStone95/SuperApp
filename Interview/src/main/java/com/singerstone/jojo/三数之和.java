package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 三数之和 {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,-2,-1};
        System.out.println(new 三数之和().threeSum(array));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int start = 0; start < nums.length; start++) {
            if (nums[start] > 0) {
                break;
            }
            if (start - 1 >= 0 && nums[start] == nums[start - 1]) {
                continue;
            }
            int target = -nums[start];
            int left = start + 1;
            int right = nums.length - 1;
            while (left < right && (left < nums.length) && (right >= 0)) {
                int sum = nums[left] + nums[right];
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    List<Integer> array = new ArrayList<>();
                    array.add(nums[start]);
                    array.add(nums[left]);
                    array.add(nums[right]);
                    result.add(array);
                    left++;
                    right--;
                    while (left < nums.length && nums[left - 1] == nums[left]) {
                        left++;
                    }
                    while (right >= 0 && nums[right + 1] == nums[right]) {
                        right--;
                    }


                }
            }

        }
        return result;
    }

}
