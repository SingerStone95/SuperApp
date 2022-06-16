package com.singerstone.test;


import java.util.concurrent.TimeUnit;

/**
 * 必须在多线程环境下执行
 * 根本原因是类加载时候 虚拟机会对静态块的执行加锁
 * 单线程环境下不会有问题
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
