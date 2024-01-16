package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/10 11:52
 * @see {@link 全排列}
 */
public class 字符串的排列 {

    public static void main(String[] args) {
        String[] array = new 字符串的排列().permutation("aab");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }


    }


    public String[] permutation(String s) {
        char[] array = s.toCharArray();
        List<String> result = new ArrayList<>();
        dfs(result, array, 0);
        return result.toArray(new String[]{});
    }

    void dfs(List<String> result, char[] array, int start) {
        if (start == array.length - 1) {
            result.add(String.valueOf(array));
            return;
        }
        Set<Character> set = new HashSet<>(); // 每一层的用到的元素不能重复
        for (int i = start; i < array.length; i++) {
            if (set.contains(array[i])) {
                continue;
            }

            set.add(array[i]);
            swap(array, i, start);
            dfs(result, array, start + 1);
            swap(array, i, start);
        }

    }

    private void swap(char[] array, int i, int start) {
        char tmp = array[i];
        array[i] = array[start];
        array[start] = tmp;
    }

}
