package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.List;

public class 括号生成 {

    public static void main(String[] args) {
        System.out.println(new 括号生成().generateParenthesis(3));

    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis(n, n, result, "");
        return result;

    }

    public void generateParenthesis(int left, int right, List<String> result, String tmp) {
        if (left == 0 && right == 0) {
            result.add(tmp);
            return;
        }
        if (left > right) {
            return;
        }
        if (left > 0) {
            generateParenthesis(left - 1, right, result, tmp + "(");
        }
        if (right > 0) {
            generateParenthesis(left, right - 1, result, tmp + ")");
        }
    }
}
