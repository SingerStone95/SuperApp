package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 子集2 {

    public static void main(String[] args) {
        System.out.println(new 子集2().subsetsWithDup(new int[]{1, 2, 2}));
    }


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); //这里必须要排序
        dfs(nums, 0);
        return result;

    }

    /**
     * 每个位置都有拿或者不拿两种状态
     * n 表示的是当前访问到了第 n 个位置
     *
     */
    void dfs(int[] nums, int n) {
        if (n == nums.length) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        tmp.add(nums[n]);
        dfs(nums, n + 1);
        tmp.remove(tmp.size() - 1);
        // 当前位置已经拿过并且还原了，下一个位置和当前是一样的，拿了就和这一层一样了，直接跳到不一样的位置去
        while (n + 1 < nums.length && nums[n + 1] == nums[n]) {
            n++;
        }
        dfs(nums, n + 1);
    }

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();
}
