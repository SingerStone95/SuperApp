package com.singerstone.leetcode;

public class PalindromicNum {

    /**
     * 是否回文数字
     */
    public static void main(String[] args) {

        System.out.println(new PalindromicNum().isPalindromic(1221));
    }

    boolean isPalindromic(int num) {
        boolean result = false;
        if (num < 0) {
            return false;
        } else {
            if (num % 10 == 0)
                return false;
            int temp = num;
            int reverseNum = 0;
            while (temp != 0) {
                reverseNum = reverseNum * 10 + temp % 10;
                temp /= 10;
            }
            if (reverseNum == num) {
                return true;
            }

        }

        return result;

    }
}