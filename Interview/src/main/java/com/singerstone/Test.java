package com.singerstone;

public class Test {
    public static void main(String[] args) {
        A a=new A();
        a.str="1";
        A a1=new A();
        a1.str="1";
        System.out.println("yogachen " + (a .equals(a1)));
        System.out.println("yogachen " + (a.hashCode()==a1.hashCode()));

    }

    public static class A {

        public String str = "";

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof A){
                return str.equals(((A) obj).str);
            }
            return super.equals(obj);

        }
    }
}
