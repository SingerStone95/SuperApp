package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/10 11:33
 * @see {@link } 题目保证了不含重复数字，所以该题算是比较简单的题 主要是一个dfs回溯思想
 */
public class 全排列 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3};
        System.out.println(new 全排列().permute(array));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        List<Integer> tmp = new ArrayList<>();
        dfs(result, used, tmp, nums);
        return result;

    }

    void dfs(List<List<Integer>> result, boolean[] used, List<Integer> tmp, int[] nums) {
        if (tmp.size() == nums.length) {
            result.add(new ArrayList<>(tmp));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                tmp.add(nums[i]);
                dfs(result, used, tmp, nums);
                used[i] = false;
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
