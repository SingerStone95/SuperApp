package com.singerstone.jojo;

public class 多线程打印 {
    private volatile int num = 1;
    private Object object = new Object();

    public static void main(String[] args) {
        new 多线程打印().threeThreadPrint100();
    }

    /**
     * 3个线程交替打印到100
     */
    public void threeThreadPrint100() {
        new StepThread(0).start();
        new StepThread(1).start();
        new StepThread(2).start();
    }

    class StepThread extends Thread {
        private int step;

        public StepThread(int step) {
            this.step = step;
        }

        @Override
        public void run() {
            while (num <= 100) {
                synchronized (object) {
                    if (num % 3 == step) {
                        System.out.println(num + " print-thread" + (step == 0 ? 3 : step));
                        num++;
                        object.notifyAll();
                    } else {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    private int charIndex = 0;
    private int curPrintThreadIndex = 0;

    /**
     * n 个线程交替打印ABC
     */
    public static void multiThreadPrintABC(int n) {

    }

    class PrintABCThread extends Thread {
        int index = 0;

        public PrintABCThread(int index) {

        }
    }
}
