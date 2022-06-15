package com.singerstone.cas;

/**
 * Created by chenbinhao on 2018/7/12.
 * YY:909075276
 */

public class 无锁链表 {

    Stack<Integer> stack = new UnSafeLinkedListStack<>();
    //Stack<Integer> stack = new Stack<>();


    public static void main(String[] args) {
        new 无锁链表().test();
    }

    public void test() {
        TestThread a = new TestThread("a", 1);
        TestThread b = new TestThread("b", 2);
        a.start();
        b.start();
        SleepUtil.sleep(1000);
        System.out.println(stack);

    }

    class TestThread extends Thread {
        int mNum;

        public TestThread(String s, int num) {
            super(s);
            mNum = num;
        }

        @Override
        public void run() {
            stack.push(mNum);
        }
    }


}
