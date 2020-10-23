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


}
