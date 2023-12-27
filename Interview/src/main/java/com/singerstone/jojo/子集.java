package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.List;

public class 子集 {
    public static void main(String[] args) {
        System.out.println(new 子集().subsets2(new int[]{1, 2, 3}));

    }

    /**
     * 二进制法 ,每个数都有进数组，不进数组两种表示 n 个数 就有 2^n 个子集 分别对应二进制的表示
     *  以 3 个数为例，状态为： 000-111
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = 1 << nums.length;
        for (int i = 0; i < n; i++) { // 外层循环是
            int x = 1;
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & x) != 0) {
                    temp.add(nums[j]);
                }
                x = x << 1;
            }
            result.add(temp);

        }
        return result;
    }


    // 回溯法
    public List<List<Integer>> subsets2(int[] nums) {

        dfs(nums, 0);
        return result;
    }

    List<Integer> tmp = new ArrayList<>();

    List<List<Integer>> result = new ArrayList<>();


    void dfs(int[] nums, int n) {
        if (n == nums.length) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        tmp.add(nums[n]);
        dfs(nums, n + 1);
        tmp.remove(tmp.size() - 1); // 不选当前的数 选下一个数
        dfs(nums, n + 1);
    }
}
