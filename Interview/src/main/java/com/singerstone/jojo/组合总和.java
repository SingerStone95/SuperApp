package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 组合总和 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        System.out.println(new 组合总和().combinationSum(array, 4));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(candidates, target, 0, new ArrayList<Integer>(), result);
        return result;

    }


    public void combinationSum(int[] candidates, int target, int left, List<Integer> temp, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }
        if (left >= candidates.length) {
            return;
        } else if (candidates[left] > target) {
            return;
        } else {
            for (int i = left; i < candidates.length; i++) {
                List<Integer> next = new ArrayList<>(temp);
                next.add(candidates[i]);
                combinationSum(candidates, target - candidates[i], i + 1, next, result);
            }
        }

    }

}
