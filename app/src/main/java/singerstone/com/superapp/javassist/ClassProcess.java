package singerstone.com.superapp.javassist;

import java.io.File;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class ClassProcess {
    public static void main(String[] args) {
        ClassPool cp = new ClassPool(true);
        String dir = System.getProperty("user.dir") +
                File.separator + "app" +
                File.separator + "build" +
                File.separator + "intermediates" +
                File.separator + "classes" +
                File.separator + "debug" +
                File.separator + "singerstone/com/superapp/TouchEvent/";
        try {
            //pool.insertClassPath(dir);
            CtClass point = ClassPool.getDefault().get("singerstone.com.superapp.TouchEvent.Test");
            //CtMethod ctMethod=point.getDeclaredMethod("lambda$test$0");
            //System.out.println(ctMethod.getName());
            CtClass point2 = ClassPool.getDefault().get("singerstone.com.superapp.TouchEvent.Test");
            System.out.println(point.subclassOf(point2));
            for (CtMethod method : point.getDeclaredMethods()) {
                System.out.println(method.getName());
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
