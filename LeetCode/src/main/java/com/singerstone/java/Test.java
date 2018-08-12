package com.singerstone.java;

/**
 * Created by chenbinhao on 2018/7/12.
 * YY:909075276
 */

public class Test {
    public static void main(String[] args) {

        Singleton s = Singleton.getSingletone();
        System.out.println(s.a);
        System.out.println(s.b);
    }
}
