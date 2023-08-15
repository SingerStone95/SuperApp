package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 电话号码的字母组合 {

    public static void main(String[] args) {
        System.out.println(new 电话号码的字母组合().letterCombinations("1233"));

    }

    Map<Character, String> map = new HashMap();

    {
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        letterCombinations(digits, 0, "", result);
        return result;
    }

    public void letterCombinations(String digits, int level, String temp, List<String> result) {
        if (level >= digits.length()) {
            return;

        }
        Character key = digits.charAt(level);
        String value = map.get(key);
        if (value == null) {
            letterCombinations(digits, level + 1, temp, result);
            return;
        }
        for (int i = 0; i < value.length(); i++) {
            if (level == digits.length() - 1) {
                result.add(temp + value.charAt(i));
            } else {
                letterCombinations(digits, level + 1, temp + value.charAt(i), result);
            }
        }

    }
}
