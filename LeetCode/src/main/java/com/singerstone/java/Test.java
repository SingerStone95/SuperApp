package com.singerstone.java;

import java.util.ArrayList;

/**
 * Created by chenbinhao on 2018/7/12.
 * YY:909075276
 */

public class Test {
    public static void main(String[] args) {

        Singleton s = Singleton.getSingletone();
        System.out.println(s.a);
        System.out.println(s.b);

        Test test = new Test();
       /* test.thread1.start();
        test.thread2.start();
        test.thread3.start();*/
        test.printArray(1, 10, new ArrayList());
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
