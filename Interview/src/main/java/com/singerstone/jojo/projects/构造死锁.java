package com.singerstone.jojo.projects;

public class 构造死锁 {

    public static void main(String[] args) {

        new 构造死锁().test();

    }

    void test() {
        new Thread1().start();
        new Thread2().start();
    }

    Object lock1 = new Object();
    Object lock2 = new Object();

    class Thread1 extends Thread {

        @Override
        public void run() {
            super.run();
            synchronized (lock1) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    System.out.println("Thread1");
                }

            }
        }
    }

    class Thread2 extends Thread {

        @Override
        public void run() {
            super.run();
            synchronized (lock2) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock1) {
                    System.out.println("Thread1");
                }

            }
        }
    }

}
