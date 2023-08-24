package com.singerstone.jojo;

public class 字符串相加 {
    public static void main(String[] args) {
        System.out.println(new 字符串相加().addStrings("999","1"));

    }

    public String addStrings(String num1, String num2) {
        num1=new StringBuilder(num1).reverse().toString();
        num2=new StringBuilder(num2).reverse().toString();
        int size = Math.max(num1.length(), num2.length());
        String result = "";
        int flag = 0;
        for (int i = 0; i < size; i++) {
            int sum = 0;
            if (i >= num1.length()) {
                sum = flag + (num2.charAt(i) - '0');
            } else if (i >= num2.length()) {
                sum = flag + (num1.charAt(i) - '0');
            } else {
                sum = flag + (num1.charAt(i) - '0') + (num2.charAt(i) - '0');
            }
            flag = sum > 9 ? 1 : 0;
            result = (sum % 10) + result;
        }
        if (flag == 1) {
            result = '1' + result;
        }
        return result;

    }
}
