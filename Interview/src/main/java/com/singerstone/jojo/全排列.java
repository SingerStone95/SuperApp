package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/10 11:33
 * @see {@link } 题目保证了不含重复数字，所以该题算是比较简单的题 主要是一个dfs回溯思想
 */
public class 全排列 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 1, 2, 3};
        System.out.println(new 全排列().permute2(array));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        List<Integer> tmp = new ArrayList<>();
        dfs(result, used, tmp, nums);
        return result;

    }

    // 使用 boolean[] used + dfs
    void dfs(List<List<Integer>> result, boolean[] used, List<Integer> tmp, int[] nums) {
        if (tmp.size() == nums.length) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        Set<Integer> set = new HashSet<>(); // 处理题目中有重复元素的 case ，保证当前层级不要取到重复的元素
        // 每一个位置都可以取所有的num ，要保证取下一个num的时候，上一个 num 的状态要清除掉
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            if (!used[i]) { // 每个数都可以选择在这一层拿或者不拿，在这一层不拿，就可能会在后面的轮次拿到
                used[i] = true;
                tmp.add(nums[i]);
                set.add(nums[i]);
                dfs(result, used, tmp, nums);
                used[i] = false;
                tmp.remove(tmp.size() - 1);
            }
        }
    }


    // 交换法 节省了一个visited空间
    public List<List<Integer>> permute2(int[] nums) {
        // 每次固定一个位置，分别和后面的每一个元素交换
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> src = new ArrayList<>();
        for (int num : nums) {
            src.add(num);
        }
        dfs(src, 0, result);
        return result;
    }


    void dfs(List<Integer> src, int start, List<List<Integer>> result) {
        if (start == src.size()) {
            result.add(new ArrayList<>(src));
        }
        // 这里本质上和第一种写法一样，也尝试把后面的每一个值，交换到当前start为止,所以去重复和方法一是一样的
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < src.size(); i++) {
            // 起始是从 start 开始,保证start也参与与自己的交换（不交换），或者和其后面的元素交换
            if (set.contains(src.get(i))) {
                continue;
            }
            set.add(src.get(i));
            Collections.swap(src, start, i);
            dfs(src, start + 1, result);
            Collections.swap(src, start, i);
        }
    }


}
