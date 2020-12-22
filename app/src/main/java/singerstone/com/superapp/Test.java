package singerstone.com.superapp;

import android.app.Activity;

public class Test {

    public static void doLeak(Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (activity != null) {
                    activity.finish();
                }

            }
        }).start();
    }

    public static void main(String[] args) {
        new C().setColor();
    }


    public static class A {

        public void print() {
            System.out.print("A");
        }

        public void setColor() {
        }
    }

    public static class B extends A {

        @Override
        public void setColor() {
            super.print();
        }
    }

    public static class C extends B {

        @Override
        public void print() {
            System.out.print("C");
        }

        @Override
        public void setColor() {
            super.setColor();
        }
    }

    public static class User {

        String name = "";
        int age = 0;

        public User() {
            //如果没有显式的调用父类构造方法，会默认调用父类无参的构造函数super()
        }

        public User(String var1, int var2) {
            this(); //间接调用 无参构造函数 中的 super();
            this.name = var1;
            this.age = var2;
        }
    }

}
