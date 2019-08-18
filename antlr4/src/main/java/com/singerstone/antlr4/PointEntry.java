package com.singerstone.antlr4;

import com.singerstone.antlr4.lib.J2OcClassAndMethodListener;
import com.singerstone.antlr4.lib.Java8Lexer;
import com.singerstone.antlr4.lib.Java8Parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileInputStream;

public class PointEntry {

    public static String sPath = "/Users/yogachen/Documents/project/SuperApp/antlr4/src/main/java/com/singerstone/antlr4/javaFile/NormalClass.java";

    public static void main(String[] args) {
        File file = new File(sPath);
        traversingFiles(file);
    }

    private static void traversingFiles(File file) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                traversingFiles(f);
            }
        } else {
            analysisFile(file);
        }
    }

    private static void analysisFile(File file) {
        System.out.println("analyseFile >>>>>>>>>>>>>>> " + file.getName());
        if (file.getName().endsWith(".java")) {
            try {
                FileInputStream fis = new FileInputStream(file);
                ANTLRInputStream antlrInputStream = new ANTLRInputStream(fis);
                Java8Lexer lexer = new Java8Lexer(antlrInputStream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                Java8Parser parser = new Java8Parser(tokens);
                ParseTree tree = parser.compilationUnit();
                System.out.print(tree);
                ParseTreeWalker walker = new ParseTreeWalker();
                J2OcClassAndMethodListener j2OcListener = new J2OcClassAndMethodListener();
                walker.walk(j2OcListener, tree);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
