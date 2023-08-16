package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.List;

public class 字典序排数 {

    public static void main(String[] args) {
        System.out.println(new 字典序排数().lexicalOrder(115));
    }

    public List<Integer> lexicalOrder(int n) {

        List<Integer> result = new ArrayList();
        for (int i = 1; i <= 9; i++) {
            lexicalOrder(result, i, n);
        }
        return result;
    }

    void lexicalOrder(List<Integer> result, int cur, int n) {
        if (cur > n) {
            return;
        }
        result.add(cur);
        for (int i = 0; i <= 9; i++) {
            lexicalOrder(result, cur * 10 + i, n);
        }

    }
}
