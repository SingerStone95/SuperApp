package com.singerstone.jojo;

import java.util.LinkedList;
import java.util.Stack;

public class 栈的压入弹出序列 {

    public static void main(String[] args) {
/*        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(129);
        int[] popped = new int[]{10, 11, 129};
        for (int pop : popped) {
            System.out.println(stack.contains(pop));
        }*/
        //pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
        int[] pushed = new int[]{1, 2, 3, 4, 5};
        int[] popped = new int[]{4, 3, 5, 1, 2};
        System.out.println(new 栈的压入弹出序列().validateBookSequences(pushed, popped));

    }

    /**
     * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * 我自己的思路：遍历出栈，完全模拟入栈出栈的过程，
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> stack = new LinkedList<>();
        int start = 0;
        for (int pop : popped) {
            // 如果弹出的元素没有在栈中就模拟入栈
            if (!stack.contains(pop)) {
                for (; start < pushed.length; start++) {
                    stack.addLast(pushed[start]);
                    if (pushed[start] == pop) {  // 直到入栈到pop的这个数停止
                        break;
                    }
                }
            }
            // 模拟出栈
            while (!stack.isEmpty()) {
                int top = stack.getLast();
                if (top == pop) {
                    stack.removeLast();
                    break;
                } else {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * 精简写法，面试的时候使用
     * 思路：遍历入栈，每次入栈之后都尝试出栈，正常栈结束后一定为空
     */
    public boolean validateBookSequences(int[] putIn, int[] takeOut) {

        Stack<Integer> stack = new Stack();
        int i = 0;
        for (int put : putIn) {
            stack.push(put);
            while (!stack.isEmpty() && stack.peek() == takeOut[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();

    }

}
