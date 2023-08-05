package com.singerstone.jojo;

import java.util.HashMap;
import java.util.Map;

public class 罗马数转整数 {

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("MCMXCIV"));
    }

    public static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);

        int result = 0;
        for (int i = 0; i < s.length(); ) {
            if (i + 1 < s.length()) {
                Integer v = map.get(s.substring(i, i + 2));
                if (v != null) {
                    result += v;
                    i = i + 2;
                    continue;
                }
            }
            result += map.get(s.substring(i, i + 1));
            i = i + 1;

        }

        return result;
    }

}
