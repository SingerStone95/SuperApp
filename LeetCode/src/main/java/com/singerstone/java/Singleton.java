package com.singerstone.java;

public class Singleton {

    public static int a;
    // public static int b=0;
    private static Singleton singleton = new Singleton();
    public static int b = 0; //位置不同执行结果不同

    private Singleton() {
        a++;
        b++;
    }

    public static Singleton getSingletone() {
        return singleton;
    }
}