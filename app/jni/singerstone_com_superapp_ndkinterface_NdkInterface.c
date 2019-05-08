//
// Created by yogachen on 2019-05-07.
//
#include <jni.h>
#include "singerstone_com_superapp_ndkinterface_NdkInterface.h"

JNIEXPORT jstring JNICALL Java_singerstone_com_superapp_ndkinterface_NdkInterface_getServiceName
  (JNIEnv *env, jclass jz){

  return (*env)->NewStringUTF(env,"时间不在于你拥有多少，而在于你怎样使用");

  }

JNIEXPORT jstring JNICALL Java_singerstone_com_superapp_ndkinterface_NdkInterface_genCrash
  (JNIEnv *env, jclass jz){
  int *a = NULL;
      *a = 1;
      return (*env)->NewStringUTF(env,"人为创建一个Crash");
  }