#include "singerstone_com_superapp_ndkinterface_NdkInterface.h"
//#include "string.h"
JNIEXPORT jstring JNICALL Java_singerstone_com_superapp_ndkinterface_NdkInterface_getServiceName(JNIEnv *env, jobject obj){
     return env->NewStringUTF((char*) "来自C语言的返回值 哈哈哈哈");
  }