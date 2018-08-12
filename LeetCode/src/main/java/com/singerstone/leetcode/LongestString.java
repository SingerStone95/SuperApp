package com.singerstone.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestString {
    public static void main(String[] args) {

        String sourse = "acdacdacd"; //fabcabcd
        System.out.println("answer:" + new LongestString().getMaxLength(sourse));
    }

    /**
     * 最长不重复子串
     */

    int getMaxLength(String sourse) {
        Map<Character, Integer> charRecorder = new HashMap<>();
        int result = 0;
        int newStart;
        int length = 0;
        for (int i = 0; i < sourse.length(); i++) {
            char c = sourse.charAt(i);
            Integer ic = charRecorder.get(c);
            if (ic == null) {
                length++;
                if (length > result) { //更新length最大值
                    result = length;
                }
            } else {
                newStart = ic + 1;
                length = i - newStart + 1;
                if (length > result) { //更新length最大值
                    result = length;
                }
            }
            charRecorder.put(c, i); // 记录位置
        }
        return result;
    }

}