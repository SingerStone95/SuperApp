package com.singerstone.jojo;

import java.util.Stack;

public class 两个栈实现队列 {

    class CQueue {

        Stack<Integer> stack = new Stack<>();


        public CQueue() {

        }

        public void appendTail(int value) {
            stack.push(value);

        }

        public int deleteHead() {
            if (stack.isEmpty()){
                return -1;
            }
            Stack<Integer> tmp = new Stack<>();
            while (!stack.isEmpty()) {
                tmp.push(stack.pop());
            }
            int result = tmp.pop();
            while (!tmp.isEmpty()) {
                stack.push(tmp.pop());
            }

            return result;
        }
    }

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */


}
