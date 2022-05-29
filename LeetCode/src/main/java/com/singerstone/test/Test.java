package com.singerstone.test;


/**
 * Created by chenbinhao on 2018/7/12. YY:909075276
 */

public class Test {


    public static class A {

        static {
            System.out.println("class A init.");

        }

        static B b = new B();

        public static void test() {
            System.out.println("method test called in class A");
        }
    }

    public static class B {

        static {
            System.out.println("class B init.");

        }

        static A a = new A();

        private void test_mem_method() {
            A a = new A();
        }

        public static void test() {
            System.out.println("method test called in class B");
        }
    }

    public static void main(String[] args) {
       A.test();
    }

}
