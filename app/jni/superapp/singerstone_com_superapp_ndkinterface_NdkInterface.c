//
// Created by yogachen on 2019-05-07.
//
#include <jni.h>
#include <stdlib.h>
#include <android/log.h>
#include <sys/mman.h>
#include <string.h>
#include "singerstone_com_superapp_ndkinterface_NdkInterface.h"

JNIEXPORT jstring JNICALL Java_singerstone_com_superapp_ndkinterface_NdkInterface_getServiceName(JNIEnv *env, jclass jz)
{
    return (*env)->NewStringUTF(env, "时间不在于你拥有多少，而在于你怎样使用");
}

JNIEXPORT jstring JNICALL Java_singerstone_com_superapp_ndkinterface_NdkInterface_genCrash(JNIEnv *env, jclass jz)
{
    int *a = NULL;
    *a = 1;
    return (*env)->NewStringUTF(env, "人为创建一个 Crash");
}

JNIEXPORT jstring JNICALL Java_singerstone_com_superapp_ndkinterface_NdkInterface_genMallocOOM(JNIEnv *env, jclass jz)
{
    for (int i = 0; i < 200; i++)
    {
        char *p = (char *)malloc(1024 * 1024 * sizeof(char));
        if (p == NULL)
        {
            __android_log_print(ANDROID_LOG_ERROR, "yogachen", "malloc failed!");
        }
        else
        {
            __android_log_print(ANDROID_LOG_ERROR, "yogachen", "malloc success %d!", (int)p);
            memset(p, 0, sizeof(char) * 1024 * 1024);
        }
    }

    //free(p);

    __android_log_print(ANDROID_LOG_ERROR, "yogachen", "code is run here");

    return (*env)->NewStringUTF(env, "创建一个 malloc OOM 3");
}

JNIEXPORT jstring JNICALL Java_singerstone_com_superapp_ndkinterface_NdkInterface_genMmapOOM(JNIEnv *env, jclass jz)
{

    for (int i = 0; i < 1024 * 50; i++)
    {
        int N = 5; // Number of elements for the array
        int *ptr = mmap(NULL, N * sizeof(int),
                        PROT_READ | PROT_WRITE,
                        MAP_PRIVATE | MAP_ANONYMOUS,
                        0, 0);
        if (ptr == MAP_FAILED)
        {
            __android_log_print(ANDROID_LOG_ERROR, "yogachen", "Mapping Failed\n");
            return (*env)->NewStringUTF(env, "创建一个 mmap OOM");
        }
        else
        {
            __android_log_print(ANDROID_LOG_ERROR, "yogachen", "Mapping success\n");
        }

        /* int err = munmap(ptr, 10*sizeof(int));

        if(err != 0){
            __android_log_print(ANDROID_LOG_ERROR, "yogachen", "UnMapping Failed\n");
            return (*env)->NewStringUTF(env, "创建一个 mmap OOM");
        }*/
    }

    return (*env)->NewStringUTF(env, "创建一个 mmap OOM");
}