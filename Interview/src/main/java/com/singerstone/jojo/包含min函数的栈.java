package com.singerstone.jojo;

public class 包含min函数的栈 {

    public static void main(String[] args) {
        MinStack stack = new MinStack(); stack.push(-2); stack.push(0); stack.push(-3);
        System.out.println(stack.min()); stack.pop(); System.out.println(stack.top());
        System.out.println(stack.min());

    }

    public static class MinStack {

        java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
        java.util.Stack<Integer> min = new java.util.Stack<Integer>();

        public MinStack() {

        }

        public void push(int x) {
            stack.push(x); if (min.isEmpty() || min.peek() >= x) {
                min.push(x);
            }

        }

        public void pop() {
            Integer value = stack.pop(); if (min.peek().equals(value)) {
                min.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return min.peek();
        }
    }

}

