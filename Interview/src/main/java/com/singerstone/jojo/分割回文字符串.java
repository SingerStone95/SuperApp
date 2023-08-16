package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 分割回文字符串 {


    public static void main(String[] args) {
        System.out.println(new 分割回文字符串().partition("abcd"));

    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        partition(s, new ArrayList<String>(), result);
        return result;

    }

    public void partition(String s, List<String> temp, List<List<String>> result) {
        if (s.isEmpty()) {
            result.add(temp);

        } else if (s.length() == 1) {
            temp.add(s);
            result.add(temp);
        } else {
            for (int i = 1; i < s.length(); i++) {
                String str = s.substring(0, i);
                if (isMirror(str)) {
                    List<String> next = new ArrayList<>(temp);
                    next.add(str);
                    partition(s.substring(i), next, result);

                }
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
