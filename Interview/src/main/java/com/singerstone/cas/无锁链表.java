package com.singerstone.cas;

/**
 * Created by chenbinhao on 2018/7/12.
 * YY:909075276
 */

public class 无锁链表 {

    Stack<Integer> stack = new SafeLinkedListStack<>();
    //Stack<Integer> stack = new UnSafeLinkedListStack<>();


    public static void main(String[] args) {
        new 无锁链表().test();
    }

    /**
     * 模拟并行插入两条记录
     */
    public void test() {
        TestThread a = new TestThread("thread_1", 1);
        TestThread b = new TestThread("thread_2", 2);
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
