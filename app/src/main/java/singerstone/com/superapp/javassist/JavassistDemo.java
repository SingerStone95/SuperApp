package singerstone.com.superapp.javassist;

import java.util.Arrays;
import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.BootstrapMethodsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.MethodInfo;

public class JavassistDemo {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("singerstone.com.superapp.javassist.Demo2");
        ClassFile classFile = ctClass.getClassFile();
        List<AttributeInfo> attributes = classFile.getAttributes();
        for (AttributeInfo attributeInfo :
                attributes) {
            if (attributeInfo instanceof BootstrapMethodsAttribute) {
                BootstrapMethodsAttribute.BootstrapMethod[] methods = ((BootstrapMethodsAttribute) attributeInfo).getMethods();
                for (BootstrapMethodsAttribute.BootstrapMethod method: methods
                     ) {
                    System.out.println("bsmMethod = " + method.methodRef + ", " + Arrays.toString(method.arguments));
                }
            }
        }
        CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
        for (CtMethod method : declaredMethods) {
            System.out.println("methodName = " + method.getName() + ", methodLongName = " + method.getLongName() + ", methodSign = " + method.getSignature() + ", returnType = " + method.getReturnType().getName());
            MethodInfo methodInfo = method.getMethodInfo();
            System.out.println("methodInfo = " + methodInfo);
        }
    }

}