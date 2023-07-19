#需要使用 ndk-17c / ndk-r16b 版本编译,需要注意的是17版本开始就不再支持armeabi架构
#Android.mk和Application.mk默认必须存放在名字为jni目录的文件夹下，因为ndk-build会到上一级目录查找jni目录
#如果需要更改到当前路径，执行ndk-build -B V=1 NDK_PROJECT_PATH=. APP_BUILD_SCRIPT=./Android.mk NDK_APPLICATION_MK=./Application.mk

#直接在当前路径执行 ndk-build (需要先配置环境变量)

#生成.h文件的脚本 javah -classpath /Users/yogachen/Documents/GitProj/SuperApp/app/build/intermediates/classes （编译生成的class目录） -d jni singerstone.com.superapp.ndkinterface（接口文件的包名+类名，不要加后缀）

#建议开发的时候直接把 jni 目录打开在 VS Code 中开发，AS无法格式化C++代码是很恼火的


# ollvm 的环境和breakpad有冲突，需要分开编译!
# ollvm4.0 编译后 ndk 所依赖的文件已经上传到百度云盘 逆向工具包/反编译
# clang.exe clang++.exe clang-format.exe 复制到ndk的toolchains\llvm\prebuilt\windows-x86_64\bin文件夹下，覆盖目标文件
# __stddef_max_align_t.h stddef.h stdarg.h float.h 复制到ndk的sysroot\usr\include文件夹下

LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)


LOCAL_MODULE := NdkInterface
#ollvm 参数
# -mllvm -sub -mllvm -bcf -mllvm -fla 由于某种原因开启bcf（虚假控制流）会编译不过，因此去掉该参数
LOCAL_CFLAGS += -mllvm -sub -mllvm -fla
LOCAL_SRC_FILES := superapp/singerstone_com_superapp_ndkinterface_NdkInterface.cpp superapp/have_fun.cpp
LOCAL_LDLIBS := -llog
include $(BUILD_SHARED_LIBRARY)
#暂时不要编译breakpad
#include $(LOCAL_PATH)/breakpad/Android.mk




