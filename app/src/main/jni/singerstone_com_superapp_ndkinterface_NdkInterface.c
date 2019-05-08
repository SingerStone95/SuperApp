//
// Created by yogachen on 2019-05-07.
//
#include "jni.h"
#include "singerstone_com_superapp_ndkinterface_NdkInterface.h"

JNIEXPORT jstring JNICALL Java_singerstone_com_superapp_ndkinterface_NdkInterface_getServiceName
  (JNIEnv *env, jclass jz){

  return (*env)->NewStringUTF(env,"this is the first time for me to use jni");

  }

