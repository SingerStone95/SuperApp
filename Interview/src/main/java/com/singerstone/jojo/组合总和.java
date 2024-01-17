package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 组合总和 {

    public static void main(String[] args) {
        int[] array = new int[]{2, 3,  5};
        System.out.println(new 组合总和().combinationSum(array, 10));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        //先排序
        Arrays.sort(candidates);
        //combinationSum(candidates, target, 0, new ArrayList<Integer>(), result);
        //combinationSum2(candidates, target, 0, new ArrayList<Integer>(), result);
        combinationSum(candidates, target, 0, new ArrayList<Integer>(), result);
        return result;

    }


    /**
     * 原数组不重复，组合里可以重复
     */
    public void combinationSum(int[] candidates, int target, int left, List<Integer> temp,
                               List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(temp));
            return;
        } else if (target < 0) {
            return;
        } else {
            for (int i = left; i < candidates.length; i++) {
                List<Integer> next = new ArrayList<>(temp);
                next.add(candidates[i]);
                // 这里下一层要从 i 开始 而不是从 left 开始 ，不然会出现 [2,3] [3,2] 组合
                combinationSum(candidates, target - candidates[i], i, next, result); // 区别在这下一层的起点：从当前数字开始
            }
        }
    }

    /**
     * 原数组不重复，组合里不可以重复
     */
    public void combinationSum2(int[] candidates, int target, int left, List<Integer> temp,
                                List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(temp));
            return;
        } else if (target < 0) {
            return;
        } else {
            for (int i = left; i < candidates.length; i++) {
                List<Integer> next = new ArrayList<>(temp);
                next.add(candidates[i]);
                combinationSum2(candidates, target - candidates[i], i + 1, next, result); // 区别在这下一层的起点：从下一个数字开始
            }
        }
    }

    /**
     * 原数组有重复，组合里不可以重复
     */
    public void combinationSum3(int[] candidates, int target, int left, List<Integer> temp,
                                List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(temp));
            return;
        } else if (target < 0) {
            return;
        } else {
            for (int i = left; i < candidates.length; i++) {
                List<Integer> next = new ArrayList<>(temp);
                next.add(candidates[i]);
                // 下一个和当前是一样的话，就跳过
                while (i + 1 < candidates.length && candidates[i + 1] == candidates[i]) {
                    i++;
                }
                combinationSum3(candidates, target - candidates[i], i + 1, next, result);
            }
        }
    }

}
