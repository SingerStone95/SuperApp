package com.singerstone;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        A a=new A();
        a.str="1";
        A a1=new A();
        a1.str="1";
        System.out.println("yogachen " + (a .equals(a1)));
        System.out.println("yogachen " + (a.hashCode()==a1.hashCode()));

        ReentrantLock lock=new ReentrantLock();
        try {
            lock.newCondition().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class A {

        public String str = "";

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof A){
                return str.equals(((A) obj).str);
            }
            return super.equals(obj);

        }
    }
}
