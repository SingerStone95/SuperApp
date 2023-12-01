package com.singerstone.jojo.projects;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class 生产者消费者模型ReentrantLock {

    private static volatile int count = 0;
    private static final int FULL = 10;
    //创建一个锁对象
    private final Lock lock = new ReentrantLock();
    //创建两个条件变量，一个为缓冲非满，一个缓冲区非空
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        生产者消费者模型ReentrantLock testMain = new 生产者消费者模型ReentrantLock();
        new Thread(testMain.new Producer()).start();
        new Thread(testMain.new Producer()).start();
        new Thread(testMain.new Consumer()).start();

    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 6; i++) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 获取锁
                lock.lock();
                try {
                    while (count == FULL) {
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName()
                            + "生产者生产，目前总共有" + count);
                    notEmpty.signalAll();

                } finally {
                    lock.unlock();
                }
            }

        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count == 0) {
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() +
                            "消费者消费，目前总共有 " + count);
                    notFull.signalAll();
                } finally {
                    lock.unlock();//解锁
                }
            }

        }
    }
}
