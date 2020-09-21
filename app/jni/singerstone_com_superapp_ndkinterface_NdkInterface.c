//
// Created by yogachen on 2019-05-07.
//
#include <jni.h>
#include <stdlib.h>
#include <android/log.h>
#include <string.h>
#include "singerstone_com_superapp_ndkinterface_NdkInterface.h"

JNIEXPORT jstring JNICALL Java_singerstone_com_superapp_ndkinterface_NdkInterface_getServiceName
  (JNIEnv *env, jclass jz){

  return (*env)->NewStringUTF(env,"时间不在于你拥有多少，而在于你怎样使用");

  }

JNIEXPORT jstring JNICALL Java_singerstone_com_superapp_ndkinterface_NdkInterface_genCrash
  (JNIEnv *env, jclass jz){
  int *a = NULL;
      *a = 1;
      return (*env)->NewStringUTF(env,"人为创建一个 Crash");
  }

  JNIEXPORT jstring JNICALL Java_singerstone_com_superapp_ndkinterface_NdkInterface_genMallocOOM
    (JNIEnv *env, jclass jz){
   char* p=(char *) malloc (500*1024*1024*1024);

    memset(p,0,sizeof(char) * 500*1024*1024*1024);

    __android_log_print(ANDROID_LOG_ERROR, "yogachen", "The value of 1 + 1 is %d", 1+1);
    
    return (*env)->NewStringUTF(env,"创建一个 malloc OOM 3");

    }

    JNIEXPORT jstring JNICALL Java_singerstone_com_superapp_ndkinterface_NdkInterface_genMmapOOM
      (JNIEnv *env, jclass jz){

      return (*env)->NewStringUTF(env,"创建一个 mmap OOM");

     }