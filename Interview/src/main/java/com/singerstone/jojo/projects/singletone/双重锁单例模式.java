package com.singerstone.jojo.projects.singletone;

public class 双重锁单例模式 {

    private static volatile 双重锁单例模式 instance;

    public 双重锁单例模式 getInstance() {
        if (instance == null) {
            synchronized (双重锁单例模式.class) {
                if (instance == null) {
                    instance = new 双重锁单例模式();
                }
            }

        }
        return instance;

    }


}
