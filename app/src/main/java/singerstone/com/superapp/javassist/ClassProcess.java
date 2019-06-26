package singerstone.com.superapp.javassist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.BootstrapMethodsAttribute;
import javassist.bytecode.ClassFile;

public class ClassProcess {
    public static void main(String[] args) {
        try {
            CtClass ctClass = ClassPool.getDefault().get("singerstone.com.superapp.TouchEvent.Test");
          /*  for (CtMethod method : ctClass.getDeclaredMethods()) {
                System.out.println(method.getName());
            }*/

            ClassFile classFile = ctClass.getClassFile();
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

    private static Map<Integer, MethodRecord> findBootstrapMethods(CtClass ctClass) {
        ClassFile classFile = ctClass.getClassFile();
        List<AttributeInfo> attributes = classFile.getAttributes();
        for (AttributeInfo attributeInfo : attributes) {
            if (attributeInfo instanceof BootstrapMethodsAttribute) {
                BootstrapMethodsAttribute.BootstrapMethod[] methods = ((BootstrapMethodsAttribute) attributeInfo).getMethods();
                if (methods.length == 0) {
                    return null;
                }
                Map<Integer, MethodRecord> methodRecordMap = new HashMap<>();
                for (BootstrapMethodsAttribute.BootstrapMethod method : methods) {
                    for (int arg : method.arguments) {


                    }
                }
            }
        }
        return null;
    }

    private static Class getMethodHandleInfoClass() {
        try {
            return Class.forName("javassist.bytecode.ConstPool.MethodHandleInfo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}