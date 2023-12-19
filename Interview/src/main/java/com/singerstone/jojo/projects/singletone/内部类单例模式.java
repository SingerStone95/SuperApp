package com.singerstone.jojo.projects.singletone;

public class 内部类单例模式 {

    static class 内部类单例模式Holder {
        private static 内部类单例模式 INSTANCE = new 内部类单例模式();
    }

    public 内部类单例模式 getInstance() {
        return 内部类单例模式Holder.INSTANCE;


    }

}
