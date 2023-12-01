package com.singerstone.jojo.projects.cas;

public class SleepUtil {
    public static void sleep(long mill){
        try {
            Thread.sleep(mill);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
