package com.singerstone.jojo;

import java.util.HashMap;
import java.util.Map;

public class 最小覆盖子串 {
    public static void main(String[] args) {
        System.out.println(">>>" + new 最小覆盖子串().minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd"));

    }

    /**
     * 示例 1：
     * <p>
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
     * 示例 2：
     * <p>
     * 输入：s = "a", t = "a"
     * 输出："a"
     * 解释：整个字符串 s 是最小覆盖子串。
     * 示例 3:
     * <p>
     * 输入: s = "a", t = "aa"
     * 输出: ""
     * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
     * 因此没有符合条件的子字符串，返回空字符串。
     * <p>
     * "aaaaaaaaaaaabbbbbcdd"
     * "abcdd"
     */
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        String result = "";
        // 还能被消耗的字符个数
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // t 中个数不为 0 的字符数
        int total0 = 0;
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                int oldCount = map.getOrDefault(c, 0);
                int newCount = oldCount - 1;
                map.put(c, newCount);
                if (newCount == 0) {
                    total0++;
                }
            }

            // 当前字符串满足条件就尝试压缩左边界
            while (total0 == map.size()) {
                if (result.equals("") || result.length() >= (j - i + 1)) {
                    result = s.substring(i, j + 1);
                }
                char lc = s.charAt(i++);
                if (map.containsKey(lc)) {
                    int lCount = map.getOrDefault(lc, 0);
                    int lNewCount = lCount + 1;
                    map.put(lc, lNewCount);
                    if (lNewCount > 0) { // 还原被消耗的字符
                        total0--;
                    }
                }
            }
        }
        return result;
    }
}
