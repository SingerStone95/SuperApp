package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 子集2 {

    public static void main(String[] args) {
        System.out.println(new 子集2().subsetsWithDup(new int[]{1, 2, 2, 3}));
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
        // 当前位置已经拿过并且回溯了，下一个位置和当前是一样的，就不用再取了，取就和上面是一样的了
        while (n + 1 < nums.length && nums[n + 1] == nums[n]) {
            n++;
        }
        dfs(nums, n + 1);
    }

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();
}
