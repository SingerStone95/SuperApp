package com.singerstone.jojo.projects.singletone;

public class 内部类单例模式 {

    // 内部类外部类私有变量可以互相访问
    private static class 内部类单例模式Holder {
        private static 内部类单例模式 INSTANCE = new 内部类单例模式();
    }

    public static 内部类单例模式 getInstance() {
        return 内部类单例模式Holder.INSTANCE;
    }

}
