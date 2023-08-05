package com.singerstone.jojo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class 只出现一次的字符 {

    public static void main(String[] args) {
        System.out.println(firstUniqChar("abaccdeff"));
        System.out.println(firstUniqChar(""));
    }


    public static char firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            Integer count = map.get(cur);
            if (count != null) {
                map.put(cur, count + 1);
            } else {
                map.put(cur, 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (map.get(c) == 1) {
                return s.charAt(i);
            }
        }
        return ' ';

    }
}
