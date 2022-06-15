package com.singerstone.test;


import java.util.concurrent.TimeUnit;

/**
 * Created by chenbinhao on 2018/7/12. YY:909075276
 */

public class 类加载死锁 {


    public static class A {

        static {
            System.out.println("class A init.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            B.test();
        }

        public static void test() {
            System.out.println("method test called in class A");
        }
    }

    public static class B {

        static {
            System.out.println("class B init.");
            A.test();
        }

        public static void test() {
            System.out.println("method test called in class B");
        }
    }

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                A.test();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                B.test();
            }
        }.start();
    }

}
