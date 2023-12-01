package com.singerstone.jojo.projects;

public class 生产者消费者模型NotifyWait {


    public static void main(String[] args) {
        生产者消费者模型NotifyWait testMain = new 生产者消费者模型NotifyWait();
        new Thread(testMain.new Producer()).start();
        new Thread(testMain.new Producer()).start();
        new Thread(testMain.new Consumer()).start();
    }

    /**
     * 1. notify wait 实现
     * 2. lock unlock 实现
     * 3. blockqueue 实现
     */


    private static volatile int count = 0;
    private static final int FULL = 10;
    private static final Object LOCK = new Object();

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    while (count == FULL) {//缓存空间满了
                        try {
                            LOCK.wait();//线程阻塞
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count++;//生产者
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    LOCK.notifyAll();//唤醒所有线程
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    while (count == 0) {
                        try {
                            LOCK.wait();
                        } catch (Exception e) {
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有 " + count);
                    LOCK.notifyAll();//唤醒所有线程
                }
            }
        }
    }
}
