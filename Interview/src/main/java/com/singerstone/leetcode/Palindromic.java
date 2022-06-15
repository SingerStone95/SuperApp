package com.singerstone.leetcode;

/**
 * 最长回文子串
 */
public class Palindromic {
    public static void main(String[] args) {
        String s = "abbaabcba";
        System.out.println("answer: " + new Palindromic().palindromic(s));
    }

    /**
     * 最长回文子串 暴力穷举
     */
    String palindromic(String sourse) {
        String result = "";
        int palinLength = 0;
        int length = sourse.length();
        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j > i; j--) {
                if (sourse.charAt(i) == sourse.charAt(j)) {
                    // System.out.println(sourse.substring(i, j + 1));
                    if (isPalin(sourse, i, j) && ((j - i + 1) > palinLength)) {
                        result = sourse.substring(i, j + 1);
                        palinLength = j - i + 1;

                    } else {
                        continue;
                    }
                }
            }
        }
        return result;
    }

    boolean isPalin(String sourse, int start, int end) {
        boolean result = true;
        while (start <= end) {
            if (sourse.charAt(start) == sourse.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }

        return result;
    }
}