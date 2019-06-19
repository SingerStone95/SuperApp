package com.singerstone.supergradle.asm

import com.singerstone.supergradle.LogHookConfig
import com.singerstone.supergradle.bean.LogMethodCell
import com.singerstone.supergradle.util.LogAnalyticsUtil
import com.singerstone.supergradle.util.Logger
import org.objectweb.asm.*
import org.objectweb.asm.commons.AdviceAdapter

/**
 * 针对日志采集sdk埋点的方法进行修改
 */
public class LogMethodVisitor extends AdviceAdapter {

    public HashSet<String> visitedFragMethods
    String methodName
    int access
    MethodVisitor methodVisitor
    String methodDesc
    String superName
    String className
    String[] interfaces

    public LogMethodVisitor(MethodVisitor methodVisitor, int access, String name, String desc,
                            String superName, String className, String[] interfaces, HashSet<String> visitedFragMethods) {
        super(Opcodes.ASM6, methodVisitor, access, name, desc)
        this.methodName = name
        this.access = access
        this.methodVisitor = methodVisitor
        this.methodDesc = desc
        this.superName = superName
        this.className = className
        this.interfaces = interfaces
        this.visitedFragMethods = visitedFragMethods
        // Logger.info("||开始扫描方法：${Logger.accCode2String(access)} ${methodName}${desc}")
    }

    boolean isAutoTrackViewOnClickAnnotation = false
    boolean isAutoTrackIgnoreTrackOnClick = false
    boolean isHasInstrumented = false
    boolean isHasTracked = false

    @Override
    void visitEnd() {
        super.visitEnd()
        if (isHasTracked) {
            visitAnnotation("Lsingerstone/com/annotations/AutoDataInstrumented;", false)
            Logger.info("Hooked method: ${className} ${methodName} ${methodDesc}")
        }
        //Logger.info("||结束扫描方法：${methodName}")
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter()
        if (isAutoTrackIgnoreTrackOnClick) {
            return
        }

        /**
         * 在 android.gradle 的 3.2.1 版本中，针对 view 的 setOnClickListener 方法 的 lambda 表达式做特殊处理。
         */
        if (methodName.trim().startsWith('lambda$') && LogAnalyticsUtil.isPrivate(access) && LogAnalyticsUtil.isSynthetic(access)) {
            LogMethodCell logMethodCell = LogHookConfig.sLambdaMethods.get(desc)
            if (logMethodCell != null) {
                int paramStart = logMethodCell.paramsStart
                if (LogAnalyticsUtil.isStatic(access)) {
                    paramStart = paramStart - 1
                }
                LogAnalyticsUtil.visitMethodWithLoadedParams(methodVisitor, Opcodes.INVOKESTATIC, LogHookConfig.LOG_ANALYTICS_BASE,
                        logMethodCell.agentName, logMethodCell.agentDesc,
                        paramStart, logMethodCell.paramsCount, logMethodCell.opcodes)
                isHasTracked = true
                return
            }
        }

        if (!(LogAnalyticsUtil.isPublic(access) && !LogAnalyticsUtil.isStatic(access))) {
            return
        }

        /**
         * 之前已经添加过埋点代码，忽略
         */
        if (isHasInstrumented) {
            return
        }

        /**
         * Method 描述信息
         */
        String methodNameDesc = methodName + methodDesc

        /**
         * Fragment 生命周期Hook
         *
         * 目前支持 android/support/v4/app/ListFragment 和 androidx/fragment/app/Fragment
         */
        if (LogAnalyticsUtil.isInstanceOfFragment(superName)) {
            LogMethodCell logMethodCell = LogHookConfig.sFragmentMethods.get(methodNameDesc)
            //找到对应的hook方法
            if (logMethodCell != null) {
                Logger.info("try hook fragment:methodNameDesc:" + methodNameDesc + "   fragment:logMethodCell:" + logMethodCell)
                visitedFragMethods.add(methodNameDesc)
                LogAnalyticsUtil.visitMethodWithLoadedParams(methodVisitor, Opcodes.INVOKESTATIC, LogHookConfig.LOG_ANALYTICS_BASE, logMethodCell.agentName, logMethodCell.agentDesc, logMethodCell.paramsStart, logMethodCell.paramsCount, logMethodCell.opcodes)
                isHasTracked = true
            }
        }

        /**
         * hook接口 click
         */

        if (interfaces != null && interfaces.length > 0) {
            LogMethodCell logMethodCell = LogHookConfig.sInterfaceMethods.get(methodNameDesc)
            if (logMethodCell != null && interfaces.contains(logMethodCell.parent)) {
                LogAnalyticsUtil.visitMethodWithLoadedParams(methodVisitor, Opcodes.INVOKESTATIC, LogHookConfig.LOG_ANALYTICS_BASE
                        , logMethodCell.agentName, logMethodCell.agentDesc, logMethodCell.paramsStart, logMethodCell.paramsCount, logMethodCell.opcodes)
                isHasTracked = true
            }
        }

    }

    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode)
    }
/**
 * 该方法是当扫描器扫描到类注解声明时进行调用
 * @param s 注解的类型。它使用的是（“L” + “类型路径” + “;”）形式表述
 * @param b 表示的是，该注解是否在 JVM 中可见
 * 1.RetentionPolicy.SOURCE：声明注解只保留在 Java 源程序中，在编译 Java 类时注解信息不会被写入到 Class。如果使用的是这个配置 ASM 也将无法探测到这个注解。
 * 2.RetentionPolicy.CLASS：声明注解仅保留在 Class 文件中，JVM 运行时并不会处理它，这意味着 ASM 可以在 visitAnnotation 时候探测到它，但是通过Class 反射无法获取到注解信息。
 * 3.RetentionPolicy.RUNTIME：这是最常用的一种声明，ASM 可以探测到这个注解，同时 Java 反射也可以取得注解的信息。所有用到反射获取的注解都会用到这个配置，就是这个原因。
 * @return
 */
    @Override
    AnnotationVisitor visitAnnotation(String s, boolean b) {
        if (s == 'Lsingerstone/com/annotations/AutoTrackDataViewOnClick;') {
            isAutoTrackViewOnClickAnnotation = true
            Logger.info("||发现 ${methodName}${methodDesc} 有注解 @AutoTrackDataViewOnClick")
        }

        if (s == 'Lsingerstone/com/annotations/AutoIgnoreTrackDataOnClick;') {
            isAutoTrackIgnoreTrackOnClick = true
            Logger.info("||发现 ${methodName}${methodDesc} 有注解 @AutoIgnoreTrackDataOnClick")
        }

        if (s == 'Lsingerstone/com/annotations/AutoDataInstrumented;') {
            isHasInstrumented = true
        }

        return super.visitAnnotation(s, b)
    }

}