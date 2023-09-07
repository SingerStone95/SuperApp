package com.singerstone.jojo;

//https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/description/
public class 字符串转整数 {

    public static void main(String[] args) {
        System.out.println(new 字符串转整数().strToInt("   "));
    }


    public int strToInt(String str) {

        int start = 0;
        while (start < str.length() && str.charAt(start) == ' ') {
            start++;
        }
        if (start == str.length()) {
            return 0;
        }
        int sign = 1;
        char cs = str.charAt(start);
        if (cs == '-') {
            sign = -1;
            start++;
        }
        if (cs == '+') {
            start++;
        }
        int board = Integer.MAX_VALUE / 10;
        int result = 0;
        for (int i = start; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                if (result > board || result == board && c > '7') {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                result = result * 10 + (c - '0');
            } else {
                break;
            }

        }
        return result * sign;

    }

}
