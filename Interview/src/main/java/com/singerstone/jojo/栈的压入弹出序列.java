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

    //输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> stack = new LinkedList<>();
        int start = 0;
        for (int pop : popped) {
            if (!stack.contains(pop)) {
                while (start < pushed.length) {
                    stack.addLast(pushed[start]);
                    if (pushed[start] == pop) {
                        start++;
                        break;
                    }
                    start++;
                }
            }
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
     *
     * 精简写法，面试的时候使用
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
