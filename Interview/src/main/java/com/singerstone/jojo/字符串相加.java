package com.singerstone.jojo;

public class 字符串相加 {

    public static void main(String[] args) {
        System.out.println(new 字符串相加().addStrings("999", "1"));

    }

    public String addStrings(String num1, String num2) {
        if (num1.isEmpty()) {
            return num2;
        } else if (num2.isEmpty()) {
            return num1;
        }
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int flag = 0; //进位
        String result = "";
        while (i >= 0 || j >= 0) {
            int sum = 0;
            if (i < 0) {
                sum += (num2.charAt(j) - '0');
            } else if (j < 0) {
                sum += (num1.charAt(i) - '0');
            } else {
                sum = (num2.charAt(j) - '0') + (num1.charAt(i) - '0');
            }
            sum += flag;
            flag = sum > 9 ? 1 : 0;
            result = sum % 10 + result;

            i--;
            j--;
        }
        if (flag == 1) {
            result = 1 + result;
        }
        return result;

    }
}
