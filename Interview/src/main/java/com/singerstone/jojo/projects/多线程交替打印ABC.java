package com.singerstone.jojo.projects;

public class 多线程交替打印ABC {

    public static void main(String[] args) {
        new 多线程交替打印ABC().multiThreadPrintABC(10);
    }

    /**
     * n 个线程交替打印ABC
     */

    private volatile int curPrintThreadIndex = 0;
    private Object lock = new Object();

    public void multiThreadPrintABC(int n) {
        for (int i = 0; i < n; i++) {
            new PrintABCThread(i, n).start();
        }
    }

    class PrintABCThread extends Thread {
        int index = 0;
        int maxCount = 0;

        public PrintABCThread(int index, int maxCount) {
            this.index = index;
            this.maxCount = maxCount;
        }

        @Override
        public void run() {
            super.run();
            synchronized (lock) {
                while (curPrintThreadIndex <= 100) {
                    if (curPrintThreadIndex % maxCount == index) { // 是否由当前线程打印
                        int p = curPrintThreadIndex % 3;
                        String print;
                        if (p == 0) {
                            print = "A";
                        } else if (p == 1) {
                            print = "B";
                        } else {
                            print = "C";
                        }
                        System.out.println("thread " + index + " print " + print + " 线程优先级:" + getPriority());
                        curPrintThreadIndex++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
    }
}
