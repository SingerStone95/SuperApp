#include "have_fun.h"
#include <android/log.h>
#include <stdlib.h>
#include <string.h>
#include <iostream>
AppCheck::AppCheck() {}

AppCheck::~AppCheck() {}

// 由于jvm和c++对中文的编码不一样，因此需要转码。 utf8/16转换成gb2312
char* AppCheck::JstringToChar(JNIEnv* env, jstring jstr) {
  char* rtn = NULL;
  jclass clsstring = env->FindClass("java/lang/String");
  jstring strencode = env->NewStringUTF("GB2312");
  jmethodID mid =
      env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
  jbyteArray barr = (jbyteArray)env->CallObjectMethod(jstr, mid, strencode);
  jsize alen = env->GetArrayLength(barr);
  jbyte* ba = env->GetByteArrayElements(barr, JNI_FALSE);
  if (alen > 0) {
    rtn = (char*)malloc(alen + 1);
    memcpy(rtn, ba, alen);
    rtn[alen] = 0;
  }
  env->ReleaseByteArrayElements(barr, ba, 0);
  return rtn;
}

// 由于jvm和c++对中文的编码不一样，因此需要转码。gb2312转换成utf8/16
jstring AppCheck::CharToJstring(JNIEnv* env, const char* str) {
  jclass strClass = (env)->FindClass("Ljava/lang/String;");
  jmethodID ctorID =
      (env)->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");
  jbyteArray bytes = (env)->NewByteArray(strlen(str));
  (env)->SetByteArrayRegion(bytes, 0, strlen(str), (jbyte*)str);
  jstring encoding = (env)->NewStringUTF("GB2312");
  return (jstring)(env)->NewObject(strClass, ctorID, bytes, encoding);
}

void AppCheck::CheckAppState(JNIEnv* env) {
  __android_log_print(ANDROID_LOG_ERROR, "yogachen", "1");
  if (!env) {
    __android_log_print(ANDROID_LOG_ERROR, "yogachen", "1");
    return;
  }
  jclass clz_hav_fun =
      env->FindClass("singerstone/com/superapp/ndkinterface/HaveFun");
  if (!clz_hav_fun) {
    return;
  }
  jmethodID method_get_sign = env->GetStaticMethodID(
      clz_hav_fun, "GetSign", "(Ljava/lang/String;)Ljava/lang/String;");
  if (!method_get_sign) {
    return;
  }
  jstring param = env->NewStringUTF("this is key");
  jstring result =
      (jstring)env->CallStaticObjectMethod(clz_hav_fun, method_get_sign, param);
  char* chardata = AppCheck::JstringToChar(env, result);
  __android_log_print(ANDROID_LOG_ERROR, "yogachen", "CheckAppState result: %s",
                      chardata);
  if (std::strcmp(chardata, "this is password") != 0) {
    std::exit(9);
  }
}
