package com.singerstone.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenbinhao on 2018/7/11.
 * YY:909075276
 */

public class GenerateParentheses {
    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        String path = "";
        new GenerateParentheses().getAnswers(3, 3, path, result);
        System.out.print(result);
    }

    private void getAnswers(int left, int right, String path, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(path);
            return;
        }
        if (left > 0) {
            getAnswers(left - 1, right, path + "(", result);
        }
        if (left < right && right > 0) {
            getAnswers(left, right - 1, path + ")", result);
        }
    }
}
