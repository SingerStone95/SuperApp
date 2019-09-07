package com.singerstone.antlr4.javaFile;

public class NormalClass {
    private String a = "";
    public RefClass refClass;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public NormalClass(String a) {
        this.a = a;
        main();
    }

    public NormalClass(String a, String b) {
        this.a = a + b;
        main();
    }

    public void main() {
        method(11);
    }

    public String method(int b) {
        b = b + 1;
        this.a = "123";
        if (this.a.equals("2")) {
            b = b + 1;
            b = b + a.length();
        }
        refClass.refClass2.methodFor2();
        refClass.refClass2.memVar2 = 10;
        return "" + a + b;
    }
}
