package com.singerstone.jojo;

import java.util.HashMap;
import java.util.Map;

public class 无重复字符的最长子串 {

    public static void main(String[] args) {
        System.out.println(new 无重复字符的最长子串().lengthOfLongestSubstring("abcabcbb"));

    }

    //"abcabcbb"
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            Integer lastIndex = map.get(c);
            if (lastIndex != null) {
                if (lastIndex >= left) {
                    left = lastIndex + 1;
                }
            }
            result = Math.max(result, i - left + 1);
            map.put(c, i);

        }
        return result;

    }

}
