package singerstone.com.superapp.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.BootstrapMethodsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;

public class ClassProcess {
    public static void main(String[] args) {
        try {
            CtClass ctClass = ClassPool.getDefault().get("singerstone.com.superapp.TouchEvent.Test");
          /*  for (CtMethod method : ctClass.getDeclaredMethods()) {
                System.out.println(method.getName());
            }*/

            ClassFile classFile = ctClass.getClassFile();
            ConstPool constPool = classFile.getConstPool();
            constPool.getClassName();
            for (AttributeInfo attributeInfo : classFile.getAttributes()) {
                if (attributeInfo instanceof BootstrapMethodsAttribute) {
                    BootstrapMethodsAttribute bootstrapMethodsAttribute = (BootstrapMethodsAttribute) attributeInfo;

                    for (BootstrapMethodsAttribute.BootstrapMethod method : bootstrapMethodsAttribute.getMethods()) {
                        String sArg = "";
                        for (int arg : method.arguments) {
                            sArg += arg + " ";
                        }
                        System.out.println(sArg + "  ref=" + method.methodRef);

                    }

                    break;
                }
            }

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}