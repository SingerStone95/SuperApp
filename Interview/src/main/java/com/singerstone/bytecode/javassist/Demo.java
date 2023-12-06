package com.singerstone.bytecode.javassist;

public class Demo {

    public static void main(String[] args) {
        // 构造lanmbda表达式
        MathOperation addition = (int a, int b) -> a + b;
        System.out.println("10 + 5 = " + exe(10, 5, addition));
    }

    private static int exe(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

    interface MathOperation {
        int operation(int a, int b);
    }
}