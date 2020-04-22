package com.singerstone.test;

import com.singerstone.cas.SafeLinkedListStack;

import java.util.Stack;

/**
 * Created by chenbinhao on 2018/7/12.
 * YY:909075276
 */

public class Test {

    SafeLinkedListStack<Object> stack = new SafeLinkedListStack<>();
    //Stack<Integer> stack = new Stack<>();


    public static void main(String[] args) {

        new Test().test();
    }

    public void test() {

        stack.push(1);
        stack.push(2);
        stack.push(3);
        TestThread a = new TestThread("a");
        TestThread b = new TestThread("b");
        a.start();
        b.start();

    }

    class TestThread extends Thread {

        public TestThread(String s) {
            super(s);
        }

        @Override
        public void run() {
            while (stack.size() != 0) {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + ":" + stack.pop());

            }
        }
    }


}
