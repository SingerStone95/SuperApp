package com.singerstone.test;

import com.singerstone.cas.SafeLinkedListStack;
import com.singerstone.cas.Stack;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by chenbinhao on 2018/7/12.
 * YY:909075276
 */

public class Test {
    public static void main(String[] args) {
       /* System.out.println(getFormatCount(1));
        System.out.println(getFormatCount(10000));
        System.out.println(getFormatCount(127000000));*/

        Stack<Integer> stack = new SafeLinkedListStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.size());

    }

    private static String getFormatCount(int count) {
        if (count < 0) {
            return "";
        } else if (count < 10000) {
            return String.valueOf(count);
        } else if (count < 100000000) {
            float showCount = new BigDecimal((float) count / 10000).setScale(1, BigDecimal.ROUND_DOWN).floatValue();
            return showCount + "万";
        } else {
            float showCount = new BigDecimal((float) count / 100000000).setScale(1, BigDecimal.ROUND_DOWN).floatValue();
            return showCount + "亿";
        }
    }

    private void printArray(int n, int m, ArrayList list) {
        if (n <= 0 || m <= 0) {
            return;
        }
        if (n == m) {
            ArrayList temp = new ArrayList(list);
            temp.add(n);
            System.out.println(temp);
        }
        ArrayList temp = new ArrayList(list);
        temp.add(n);
        printArray(n - 1, m - n, temp);
        printArray(n - 1, m, list);
    }


    private volatile int num = 1;

    private volatile int flag = 1;

    public static class MyThread extends Thread {
        protected String name = "";

        MyThread(String name) {
            this.name = name;
        }
    }

    MyThread thread1 = new MyThread("thread1") {
        @Override
        public void run() {
            synchronized (Test.this) {
                while (num <= 100) {
                    if (flag == 1) {
                        System.out.println(name + " " + num);
                        num++;
                        flag = 2;
                        Test.this.notifyAll();
                    } else {
                        try {
                            Test.this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    };

    MyThread thread2 = new MyThread("thread2") {
        @Override
        public void run() {
            synchronized (Test.this) {
                while (num <= 100) {
                    if (flag == 2) {
                        System.out.println(name + " " + num);
                        num++;
                        flag = 3;
                        Test.this.notifyAll();
                    } else {
                        try {
                            Test.this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    };
    MyThread thread3 = new MyThread("thread3") {
        @Override
        public void run() {
            synchronized (Test.this) {
                while (num <= 100) {
                    if (flag == 3) {
                        System.out.println(name + " " + num);
                        num++;
                        flag = 1;
                        Test.this.notifyAll();
                    } else {
                        try {
                            Test.this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
    };
}
