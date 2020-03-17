package singerstone.com.superapp.javassist;

import java.util.Arrays;
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
            for (CtMethod ctMethod : ctClass.getDeclaredMethods()) {
                System.out.println(ctMethod.getLongName());
                if ("lambda$test$2".equals(ctMethod.getName())) {
                    for (CtClass ctC : ctMethod.getParameterTypes()) {
                        System.out.println(ctC.getName());
                    }
                    System.out.println(Arrays.toString(getParamsIndexs(ctMethod, 2)));
                }
            }

            String format = "onItemClick($%d,$%d,$%d);}";
            int[] arrays = new int[]{3, 2, 3, 4};
            System.out.println(repleceArgsIndex(format, arrays, 0));

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int[] getParamsIndexs(CtMethod ctMethod, int paramsSize) throws NotFoundException {
        if (paramsSize <= 0) {
            return new int[0];
        }
        int indexs[] = new int[paramsSize];
        int allParamSize = ctMethod.getParameterTypes().length;
        int position = allParamSize;
        for (int currentIndex = paramsSize - 1; currentIndex >= 0; currentIndex--) {
            indexs[currentIndex] = position;
            position--;
        }
        return indexs;
    }

    private static String repleceArgsIndex(String format, int arg[], int index) {
        if (arg.length == index) {
            return format;
        }
        String temp = format.replaceFirst("%d", arg[index] + "");
        return repleceArgsIndex(temp, arg, ++index);
    }

  /*  public static ArrayList<Integer> getParamsIndexs(CtMethod ctMethod, int paramsSize) {
        ArrayList<Integer> positions = new ArrayList<>();
        try {
            int allParamSize = ctMethod.getParameterTypes().length;
            for (int position = allParamSize; positions.size() != paramsSize; position--) {
                positions.add(position);
            }
            Collections.reverse(positions);
            return positions;
        } catch (NotFoundException e) {
            e.printStackTrace();
            return positions;
        }
    }*/

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