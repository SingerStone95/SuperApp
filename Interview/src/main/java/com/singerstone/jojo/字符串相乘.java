package com.singerstone.jojo;

public class 字符串相乘 {

    public static void main(String[] args) {
        System.out.println(new 字符串相乘().multiply("12", "12"));

    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] result = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                result[i + j + 1] += n1 * n2;
            }
        }
        for (int i = result.length - 1; i >= 1; i--) {
            result[i - 1] += result[i] / 10;
            result[i] = result[i] % 10;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (i == 0 && result[i] == 0) {
                continue;
            }
            builder.append(result[i]);
        }
        return builder.toString();

    }
}
