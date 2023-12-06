package com.singerstone.bytecode.javassist;

/**
 * author : yogachen
 * date   : 2019-06-26
 * desc   : 记录lambda表达式函数信息
 */
public class MethodRecord {
    public String methodRefName;
    public String methodRefType;
    public String methodClassName;
    public String interfaceMethodName;
    public String interfaceClassName;

    @Override
    public String toString() {
        return "MethodRecord{" +
                "methodRefName='" + methodRefName + '\'' +
                ", methodRefType='" + methodRefType + '\'' +
                ", methodClassName='" + methodClassName + '\'' +
                ", interfaceMethodName='" + interfaceMethodName + '\'' +
                ", interfaceName='" + interfaceClassName + '\'' +
                '}';
    }
}
