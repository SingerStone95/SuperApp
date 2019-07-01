package singerstone.com.superapp.javassist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.BootstrapMethodsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;

public class ClassProcess {
    public static void main(String[] args) {
        try {
            CtClass ctClass = ClassPool.getDefault().get("singerstone.com.superapp.TouchEvent.Test");
            Map<Integer, MethodRecord> methodMap = findBootstrapMethods(ctClass);
            if (methodMap == null) return;
            ClassFile classFile = ctClass.getClassFile();
            ConstPool constPool = classFile.getConstPool();
            //classPool.getInvokeDynamicType()
            //constPool.getSize();

            int methodSize = methodMap.size();
            int constSize = constPool.getSize();
            int breakFlagCount = 0; //提前终止循环计数
            for (int i = 1; breakFlagCount < methodSize && i < constSize - 1; i++) {
                if (constPool.getTag(i) == ConstPool.CONST_InvokeDynamic) {
                    int key = constPool.getInvokeDynamicBootstrap(i);
                    MethodRecord record = methodMap.get(key);
                    if (record == null) {
                        continue;
                    }
                    breakFlagCount++;
                    int nameAndTypeIndex = constPool.getInvokeDynamicNameAndType(i);
                    //NameAndTypeInfo
                    int methodNameIndex = constPool.getNameAndTypeName(nameAndTypeIndex);
                    String methodName = constPool.getUtf8Info(methodNameIndex);
                    int methodDescriptorIndex = constPool.getNameAndTypeDescriptor(nameAndTypeIndex);
                    String methodDescriptor = constPool.getUtf8Info(methodDescriptorIndex);
                    record.interfaceMethodName = methodName;
                    record.interfaceClassName = methodDescriptor;
                    //System.out.println(key + " " + methodName + " " + methodDescriptor);
                    System.out.println(record);
                }
            }
            for (CtMethod ctMethod:ctClass.getDeclaredMethods()){
                System.out.println(ctMethod.getLongName());
                System.out.println(ctMethod.getName());
            }
            for (MethodRecord record:methodMap.values()){

            }

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, MethodRecord> findBootstrapMethods(CtClass ctClass) {

        ClassFile classFile = ctClass.getClassFile();
        ConstPool constPool = classFile.getConstPool();
        List<AttributeInfo> attributes = classFile.getAttributes();
        for (AttributeInfo attributeInfo : attributes) {
            if (attributeInfo instanceof BootstrapMethodsAttribute) {
                BootstrapMethodsAttribute.BootstrapMethod[] methods = ((BootstrapMethodsAttribute) attributeInfo).getMethods();
                if (methods.length == 0) {
                    return null;
                }
                Map<Integer, MethodRecord> methodRecordMap = new HashMap<>();
                int key = 0;
                for (BootstrapMethodsAttribute.BootstrapMethod method : methods) {
                    for (int arg : method.arguments) {
                        if (constPool.getTag(arg) == ConstPool.CONST_MethodHandle) {
                            int methodHandleIndex = constPool.getMethodHandleIndex(arg);
                            String methodRefName = constPool.getMethodrefName(methodHandleIndex);
                            String methodRefType = constPool.getMethodrefType(methodHandleIndex);
                            String methodClassName = constPool.getMethodrefClassName(methodHandleIndex);
                            //System.out.println(methodRefName + " " + methodRefType + " " + methodClassName);
                            MethodRecord methodRecord = new MethodRecord();
                            methodRecord.methodClassName = methodClassName;
                            methodRecord.methodRefName = methodRefName;
                            methodRecord.methodRefType = methodRefType;
                            methodRecordMap.put(key, methodRecord);
                        }
                    }
                    key++;
                }
                return methodRecordMap;
            }
        }
        return null;
    }


}