package com.singerstone.supergradle.asm

import com.amazonaws.util.IOUtils
import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.singerstone.supergradle.util.AutoMatchUtil
import com.singerstone.supergradle.util.AutoTextUtil
import com.singerstone.supergradle.util.Logger
import groovy.io.FileType
import org.apache.commons.codec.digest.DigestUtils
import org.gradle.internal.impldep.org.eclipse.jgit.annotations.NonNull
import javax.annotation.Nullable
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry
import org.apache.commons.io.FileUtils

/**
 * 自动埋点追踪，遍历所有文件更换字节码 然后生成systrace
 *
 * 进入Android/Sdk/platform-tools/systrace目录下
 *
 * python systrace.py -b 8000 -t 5 -o systrace.html
 */
public class AutoTransform extends Transform {

    private static final String VERSION = "v1.0.2"

    @Override
    String getName() {
        return "AutoTrack"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }


    @Override
    public void transform(
            @NonNull Context context,
            @NonNull Collection<TransformInput> inputs,
            @NonNull Collection<TransformInput> referencedInputs,
            @Nullable TransformOutputProvider outputProvider,
            boolean isIncremental) throws IOException, TransformException, InterruptedException {


        if (!incremental) {
            outputProvider.deleteAll()
        }
        def startTime = System.currentTimeMillis()
        /**遍历输入文件*/
        inputs.each { TransformInput input ->
            /**
             * 遍历jar
             */
            input.jarInputs.each { JarInput jarInput ->
                // 获得输出文件path
                File dest = outputProvider.getContentLocation(jarInput.name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
                ///Users/yogachen/Documents/GitProj/SuperApp/app/build/intermediates/transforms/desugar/debug/1.jar
                ///Users/yogachen/Documents/GitProj/SuperApp/app/build/intermediates/transforms/AutoTrack/debug/1.jar
                ///Users/yogachen/Documents/GitProj/SuperApp/app/build/tmp/transformClassesWithAutoTrackForDebug
                Logger.info("-->开始遍历特定jar ${jarInput.file.absolutePath}\n" + dest + "\n" + context.getTemporaryDir() + "<------")

                def modifiedJar = modifyJarFile(jarInput.file, context.getTemporaryDir())
                if (modifiedJar == null) {
                    modifiedJar = jarInput.file
                }
                FileUtils.copyFile(modifiedJar, dest)
            }
            /**
             * 遍历目录
             */
            input.directoryInputs.each { DirectoryInput directoryInput ->
                File dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
                File srcDir = directoryInput.file
                ///Users/yogachen/Documents/GitProj/SuperApp/app/build/intermediates/transforms/desugar/debug/2
                ///Users/yogachen/Documents/GitProj/SuperApp/app/build/intermediates/transforms/AutoTrack/debug/42
                Logger.info("-->开始遍历特定目录  ${srcDir.absolutePath}\n" + dest.absolutePath + "<------")
                if (srcDir) {
                    HashMap<String, File> modifyMap = new HashMap<>()
                    srcDir.traverse(type: FileType.FILES, nameFilter: ~/.*\.class/) {
                        File classFile ->
                            File modified = modifyClassFile(srcDir, classFile, context.getTemporaryDir())
                            if (modified != null) {
                                //key为相对路径
                                modifyMap.put(classFile.absolutePath.replace(srcDir.absolutePath, ""), modified)
                            }
                    }
                    /*
                     * 1.先全部拷贝过去 2.然后拷贝修改过的类，发现重复了，就删除原来拷贝的那个类
                     */
                    FileUtils.copyDirectory(srcDir, dest)
                    //build\intermediates\transforms\AutoTrack\debug\folders\1\1\bbe24caec6165e3b53aef7fbf19549514e15f422
                    modifyMap.entrySet().each { //这里面放的是 temp 文件夹中修改后的类
                        Map.Entry<String, File> en ->
                            File target = new File(dest.absolutePath + en.getKey())
//                            Logger.info(target.getAbsolutePath())
                            if (target.exists()) {
                                target.delete()
                            }
                            FileUtils.copyFile(en.getValue(), target)
                            en.getValue().delete()
                    }
                }

            }
        }

        //计算耗时
        def cost = (System.currentTimeMillis() - startTime) / 1000
        Logger.info("计时结束:费时${cost}秒")
    }

    /**
     * Jar文件中修改对应字节码
     */
    private static File modifyJarFile(File jarFile, File tempDir) {
        if (jarFile) {
            return modifyJar(jarFile, tempDir, true)

        }
        return null
    }

    private static File modifyJar(File jarFile, File tempDir, boolean nameHex) {
        /**
         * 读取原jar
         */
        def file = new JarFile(jarFile)
        /** 设置输出到的jar */
        def hexName = ""
        //防止在temp里面重名
        if (nameHex) {
            hexName = DigestUtils.md5Hex(jarFile.absolutePath).substring(0, 8)
        }
        def outputJar = new File(tempDir, hexName + jarFile.name)
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(outputJar))
        Enumeration enumeration = file.entries()

        while (enumeration.hasMoreElements()) {
            JarEntry jarEntry = (JarEntry) enumeration.nextElement()
            InputStream inputStream = file.getInputStream(jarEntry)

            String entryName = jarEntry.getName()
            String className

            ZipEntry zipEntry = new ZipEntry(entryName)

            jarOutputStream.putNextEntry(zipEntry)

            byte[] modifiedClassBytes = null
            byte[] sourceClassBytes = IOUtils.toByteArray(inputStream)
            if (entryName.endsWith(".class")) {
                className = entryName.replace("/", ".").replace(".class", "")
//                Logger.info("Jar:className:" + className)
                if (AutoMatchUtil.isShouldModifyClass(className)) {
                    modifiedClassBytes = AutoModify.modifyClasses(sourceClassBytes)
                }
            }
            if (modifiedClassBytes == null) {
                jarOutputStream.write(sourceClassBytes)
            } else {
                jarOutputStream.write(modifiedClassBytes)
            }
            jarOutputStream.closeEntry()
        }
        jarOutputStream.close()
        file.close()
        return outputJar
    }

    /**
     * 目录文件中修改对应字节码
     */
    private static File modifyClassFile(File dir, File classFile, File tempDir) {
        File modified = null
        FileOutputStream outputStream = null
        try {
            //去掉绝对路径获取类名
            String className = AutoTextUtil.path2ClassName(classFile.absolutePath.replace(dir.absolutePath + File.separator, ""))
            Logger.info("classFile.absolutePath:" + classFile.absolutePath)
            Logger.info("dir.absolutePath + File.separator:" + dir.absolutePath + File.separator)
            Logger.info("File:className:" + className)
            //过滤R文件
            if (AutoMatchUtil.isShouldModifyClass(className)) {
                byte[] sourceClassBytes = IOUtils.toByteArray(new FileInputStream(classFile))
                byte[] modifiedClassBytes = AutoModify.modifyClasses(sourceClassBytes)
                if (modifiedClassBytes) {
                    modified = new File(tempDir, className.replace('.', '') + '.class')
                    if (modified.exists()) {
                        modified.delete()
                    }
                    modified.createNewFile()
                    outputStream = new FileOutputStream(modified)
                    outputStream.write(modifiedClassBytes)
                }
            } else {
                return classFile
            }
        } catch (Exception e) {
            e.printStackTrace()
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close()
                }
            } catch (Exception e) {
            }
        }
        return modified

    }

    /**
     * 包括两种数据:jar包和class目录，打印出来用于调试
     */
    private static void printlnJarAndDir(Collection<TransformInput> inputs) {

        def classPaths = []
        String buildTypes
        String productFlavors
        inputs.each { TransformInput input ->
            input.directoryInputs.each { DirectoryInput directoryInput ->
                classPaths.add(directoryInput.file.absolutePath)
                buildTypes = directoryInput.file.name
                productFlavors = directoryInput.file.parentFile.name
                Logger.info("||项目class目录：${directoryInput.file.absolutePath}")
            }
            input.jarInputs.each { JarInput jarInput ->
                classPaths.add(jarInput.file.absolutePath)
                Logger.info("||项目jar包：${jarInput.file.absolutePath}")
            }
        }
    }
}
