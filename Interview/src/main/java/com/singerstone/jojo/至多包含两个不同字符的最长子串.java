package com.singerstone.jojo;

import java.util.HashMap;
import java.util.Map;

public class 至多包含两个不同字符的最长子串 {
    public static void main(String[] args) {

        System.out.println(new 至多包含两个不同字符的最长子串().lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(new 至多包含两个不同字符的最长子串().lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }

    /**
     * Input: "eceba"
     * Output: 3
     * Explanation: _t_ is "ece" which its length is 3.
     */
    int lengthOfLongestSubstringTwoDistinct(String s) {
        int left = 0;
        int result = 0;
        int max_count = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            int count = map.getOrDefault(c, 0);
            map.put(c, ++count);
            max_count = Math.max(max_count, count);
            while (map.size() > 2) { // 收缩左边界
                int new_count = map.get(s.charAt(left)) - 1;
                if (new_count == 0) {
                    map.remove(s.charAt(left));
                } else {
                    map.put(s.charAt(left), new_count);
                }

                left++;
            }
            result = Math.max(result, i - left + 1);

        }
        return result;

    }
}
