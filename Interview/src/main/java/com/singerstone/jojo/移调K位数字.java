package com.singerstone.jojo;


import java.util.LinkedList;
import java.util.Stack;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/17 11:29
 * @see {@link }
 */
public class 移调K位数字 {

    public static void main(String[] args) {
        System.out.println(new 移调K位数字().removeKdigits("10", 1));
    }

    //输入：num = "1432219", k = 3
    //输出："1219"
    //解释：移除掉 3 个数字 4, 3, 和 2 形成一个新的最小的数字 1219

    // 思路：最小的数一定是升序排列的数，用一个栈来维护这个数
    public String removeKdigits(String num, int k) {
        if (k >= num.length()) {
            return "0";
        }
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!list.isEmpty() && c < list.getLast() && k > 0) {
                list.removeLast();
                k--;
            }
            list.addLast(c);
        }
        for (int i = 0; i < k; i++) {
            list.removeLast();
        }
        while (list.size() > 0 && list.getFirst() == '0') {
            list.removeFirst();
        }
        StringBuilder builder = new StringBuilder();
        for (char c : list) {
            builder.append(c);
        }
        return builder.length() == 0 ? "0" : builder.toString();
    }
}
