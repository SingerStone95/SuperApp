package com.singerstone.antlr4.javaFile;

/**
 * author : yogachen
 * date   : 2019-08-09
 * desc   :
 */
public class RefClass {
    private int memVar = 1;

    public RefClass2 refClass2;

    public void methodFor() {
        String a = "";
        for (int i = 0; i < 10; i++) {
            a += i;
        }
        a.toString();
    }
}
