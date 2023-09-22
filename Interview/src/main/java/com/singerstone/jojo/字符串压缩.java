package com.singerstone.jojo;

public class 字符串压缩 {

    public static void main(String[] args) {
        System.out.println(new 字符串压缩().compressString("a"));
    }
    //示例1:
    //
    // 输入："aabcccccaaa"
    // 输出："a2b1c5a3"
    public String compressString(String S) {
        if (S.isEmpty()) {
            return "";
        }
        String result = "";
        char last_char = S.charAt(0);
        int count = 1;
        for (int i = 1; i < S.length(); i++) {
            char c = S.charAt(i);
            if (last_char != c) {
                result += last_char;
                result += count;
                count = 0;
            }
            last_char = c;
            count++;
        }
        result += last_char;
        result += count;
        return S.length()>result.length()?result:S;

    }
}
