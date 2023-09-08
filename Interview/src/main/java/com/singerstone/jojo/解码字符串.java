package com.singerstone.jojo;


import java.util.Stack;

public class 解码字符串 {

    public static void main(String[] args) {
        System.out.println(new 解码字符串().decodeString("2[a2[c2[e]]]"));
    }

    //示例 1：
    //
    //输入：s = "3[a]2[bc]"
    //输出："aaabcbc"

    //3[a2[c2[e]]]

    public String decodeString(String s) {
        String res = "";
        Stack<Integer> nums = new Stack<>();
        Stack<String> strings = new Stack<>();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = (num * 10) + Integer.parseInt(c + "");
            } else if (c == '[') {
                nums.push(num);
                num = 0;
                strings.push(res);
                res = "";

            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int muti = nums.pop();
                String pre = strings.pop();
                for (int j = 0; j < muti; j++) {
                    tmp.append(res);
                }
                res = pre + tmp;
            } else {
                res += c;
            }

        }
        return res;

    }
}
