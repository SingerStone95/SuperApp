package com.singerstone.jojo;

public class 整数转罗马数字 {

    public static void main(String[] args) {
        System.out.println(new 整数转罗马数字().intToRoman(3344));
    }

    /**
     * 直接暴力枚举
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 3333
     */
    public String intToRoman(int num) {
        String[] array1 = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] array2 = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String[] array3 = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM", "M"};
        String[] array4 = new String[]{"", "M", "MM", "MMM"};
        return array4[num / 1000]
                + array3[num % 1000 / 100]
                + array2[num % 100 / 10]
                + array1[num % 10];
    }
}
