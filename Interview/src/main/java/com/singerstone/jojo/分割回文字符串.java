package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 分割回文字符串 {


    public static void main(String[] args) {
        System.out.println(new 分割回文字符串().partition("abb"));

    }


    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        partition2(s, 0, s.length() - 1, new ArrayList<String>(), result);
        return result;

    }

    /**
     * 示例 1：
     * 输入：s = "aab"
     * 输出：[["a","a","b"],["aa","b"]]
     */
    public void partition2(String s, int left, int right, List<String> temp, List<List<String>> result) {
        if (left > right) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = left; i <= right; i++) {
            String sub = s.substring(left, i + 1);
            if (isMirror(sub)) {
                temp.add(sub);
                partition2(s, i + 1, right, temp, result);
                temp.remove(temp.size() - 1);
            }
        }

    }


    Map<String, Boolean> record = new HashMap<>();

    private boolean isMirror(String str) {
        if (str.isEmpty() || str.length() == 1) {
            return true;
        }
        Boolean isMir = record.get(str);
        if (isMir != null) {
            return isMir;
        }
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                record.put(str, false);
                return false;
            }
            left++;
            right--;
        }
        record.put(str, true);
        return true;
    }


}
