package com.singerstone.antlr4.javaFile;

public class NormalClass {
    private String a = "";

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public NormalClass(String a) {
        this.a = a;
    }

    public void main() {
        method(11);
    }

    public String method(int a) {
        a = a + 1;
        return "" + a;
    }
}
