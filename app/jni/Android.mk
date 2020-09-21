#需要使用ndk-17c版本编译,需要注意的是17版本开始就不再支持armeabi架构
#Android.mk和Application.mk默认必须存放在名字为jni目录的文件夹下，因为ndk-build会到上一级目录查找jni目录
#如果需要更改到当前路径，执行ndk-build -B V=1 NDK_PROJECT_PATH=. APP_BUILD_SCRIPT=./Android.mk NDK_APPLICATION_MK=./Application.mk

#直接在当前路径执行 ndk-build (需要先配置环境变量)

#生成.h文件的脚本 javah -classpath /Users/yogachen/Documents/GitProj/SuperApp/app/build/intermediates/classes （编译生成的class目录） -d jni singerstone.com.superapp.ndkinterface（接口文件的包名+类名，不要加后缀）


LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := NdkInterface
LOCAL_SRC_FILES := singerstone_com_superapp_ndkinterface_NdkInterface.c
LOCAL_LDLIBS := -llog
include $(BUILD_SHARED_LIBRARY)
include $(LOCAL_PATH)/breakpad/Android.mk