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

        X x = getX();
        x.getClass().isInstance(x);
        if (x instanceof Y) {
            System.out.println("yes");
        }
    }

    public static X getX() {
        return new Demo();
    }


    static abstract class X {

        abstract void x();
    }

   interface Y {

        void y() ;
    }

    static class Demo extends X {

        @Override
        public void x() {

        }


    }
}
