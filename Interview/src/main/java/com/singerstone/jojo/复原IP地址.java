package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.List;

public class 复原IP地址 {

    public static void main(String[] args) {
        System.out.println(new 复原IP地址().restoreIpAddresses("010010"));
    }

    public List<String> restoreIpAddresses(String s) {

        List<String> result = new ArrayList<>();
        restoreIpAddresses(s, 4, "", result);
        return result;

    }

    public void restoreIpAddresses(String s, int seg, String temp, List<String> result) {

        if (s.isEmpty() && seg > 0) {
            return;
        } else if (seg == 0) {
            if (s.isEmpty()) {
                result.add(temp); // 字符串是值传递，不用new对象
            } else {
                return;
            }
        } else {
            for (int i = 1; i <= 3 && i <= s.length(); i++) {
                String segment = s.substring(0, i);
                if (isValidSeg(segment, seg)) {
                    String next = temp + segment;
                    if (seg > 1) {
                        next += '.';
                    }
                    restoreIpAddresses(s.substring(i), seg - 1, next, result);
                }
            }
        }
    }

    private boolean isValidSeg(String s, int seg) {
        if (s.isEmpty()) {
            return false;
        }
        if (s.length() > 1 && s.startsWith("0")) {
            return false;
        }
        int ip = Integer.valueOf(s);
        return ip >= 0 && ip <= 255;

    }
}
