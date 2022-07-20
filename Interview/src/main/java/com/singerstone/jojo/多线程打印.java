package com.singerstone.jojo;

public class 多线程打印 {

    private volatile int num = 1;
    private Object object = new Object();

    /**
     * 思路就是每个线程要打印的数其实是明确的，就是限制条件让它它打印 模板就是
     * if(能打印){
     *   打印
     *   lock.notifyAll()
     * }else{
     *   lock.wait()
     * }
     * @param args
     */
    public static void main(String[] args) {
//        new 多线程打印().threeThreadPrint100();

        new 多线程打印().multiThreadPrintABC(10);
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


    private volatile int curPrintThreadIndex = 1;
    private Object lock = new Object();

    /**
     * n 个线程交替打印ABC
     */
    public void multiThreadPrintABC(int n) {
        for (int i = 1; i <= n; i++) {
            new PrintABCThread(i, n).start();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                    if (curPrintThreadIndex % maxCount == index-1) {
                        int p = curPrintThreadIndex % 3;
                        String print;
                        if (p == 0) {
                            print = "A";
                        } else if (p == 1) {
                            print = "B";
                        } else {
                            print = "C";
                        }
                        System.out.println("thread " + index + " print " + print);
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
