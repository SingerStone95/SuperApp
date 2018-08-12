package com.singerstone.leetcode;

public class Reverse {

    /**
     * 逆序输出输入的数
     */
    public static void main(String[] args) {
        System.out.println(new Reverse().reverse(-321887));
    }

    int reverse(int sourse) {
        int result = 0;
        while (sourse != 0) {
            int num = sourse % 10;
            int temp = result * 10 + num;
            if ((temp - num) / 10 != result) { //逆运算回去不等于原来的数，就溢出了
                return 0;
            }
            result = temp;
            sourse /= 10;
        }

        return result;
    }
}