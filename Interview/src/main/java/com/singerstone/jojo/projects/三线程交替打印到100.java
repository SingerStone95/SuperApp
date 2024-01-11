package com.singerstone.jojo.projects;

public class 三线程交替打印到100 {

    private volatile int num = 1;
    private Object object = new Object();

    /**
     * 思路就是每个线程要打印的数其实是明确的，就是限制条件让它它打印 模板就是
     * if(能打印){
     * 打印
     * lock.notifyAll()
     * }else{
     * lock.wait()
     * }
     *
     * @param args
     */
    public static void main(String[] args) {
        new 三线程交替打印到100().threeThreadPrint100();
    }

    /**
     * 3个线程交替打印到100 ,每个线程打印的数是固定的 ，用线程数取模
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
                        System.out.println(num + " print-thread" + (step == 0 ? 3 : step) + " 线程优先级:" + getPriority());
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










}
