package com.singerstone.bytecode;

import java.io.File;

public class ByteCodeTest {

    public static void main(String[] args) {
        String rootPath = System.getProperty("user.dir");
        String classFilePath =
                rootPath + File.separator + "LeetCode" + File.separator + "Test.java";
        System.out.println("yogachen->" + classFilePath);
        File classFile = new File(classFilePath);
        if (classFile.exists()){
            System.out.println("yogachen->" + "has file");
        }

    }



}
