package com.singerstone.jojo;

import java.util.HashMap;
import java.util.Map;

public class 替换后的最大重复字符 {

    public static void main(String[] args) {
        System.out.println(new 替换后的最大重复字符().characterReplacement("ABAB", 2));

    }

    // 双指针+滑动窗口
    public int characterReplacement(String s, int k) {
        int start = 0;
        int result = 0;
        int maxCount = 0; // 当前窗口里面的最大重复个数
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0; end < s.length(); end++) {
            Character c = s.charAt(end);
            int count = map.getOrDefault(c, 0);
            count++;
            map.put(c, count);
            maxCount = Math.max(count, maxCount);

            // 当前区间的长度 - 区间里面最多的元素个数 = 要保证连续的前提下还需要替换的元素 > k
            // 说明当前区间已经不得行了，要继续往后找
            if (end - start + 1 - maxCount > k) {
                Character removeChar = s.charAt(start);
                map.put(removeChar, map.get(removeChar) - 1); // 左边界右移，需要把它的个数减掉
                start++;
            }
            result = Math.max(result, end - start + 1);
        }
        return result;
    }
}
