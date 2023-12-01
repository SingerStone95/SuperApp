package com.singerstone;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 安装 ASM ByteCode Viewer
 * 在 .class 文件右键查看即可
 */
public class TestByteCode {

    public static String staticString = "staticString";

    public static String staticMethod() {
        return "12345678";
    }


    // 非静态方法调用，a.b() 字节码层面就是 b(a) 会把调用的对象作为第一个参数放进操作数栈
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.forEach(new Consumer<Object>() {
            @Override
            public void accept(Object s) {

            }
        });
        Runnable runnable = () -> {
            System.out.println("runnable");
        };
        new TestByteCode().handleRunnable(runnable);
        runnable.run();

      /*  // 拆箱装箱字节码
        Integer num1 = 127;
        Integer num2 = 128;
        System.out.println(num1 == num2);
        System.out.println(staticString);
        staticMethod();

        //
        System.out.println(new TestByteCode().level1(1, 2));
        System.out.println(getStaticNum(10));*/
    }

    void handleRunnable(Runnable runnable) {
        runnable.run();

    }

    void format() {

    }

    void testlambdaparam() {
        Runnable runnable = () -> System.out.println("runnable"+mA);
        runnable.run();
    }

    int mA = 10;

    int level1(int a1, int a2) {
        synchronized (TestByteCode.class) {
            int r = a1 + a2;
            return level2(r, mA);
        }
    }

    int level2(int a1, int a2) {
        synchronized (this) {
            int a = mA;
            mA = a + a1 + a2;
            return mA;
        }
    }

    public static int getStaticNum(int a) {
        return a + 1;

    }

    String throwAnError(String input) {
        if (input.isEmpty()) {
            throw new RuntimeException("error");
        }
        return input.substring(0, 1);
    }

}
