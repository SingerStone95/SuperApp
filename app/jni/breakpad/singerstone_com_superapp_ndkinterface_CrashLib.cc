//
// Created by yogachen on 2019-05-08.
//
#include <jni.h>
#include <string>
#include "src/client/linux/handler/minidump_descriptor.h"
#include "src/client/linux/handler/exception_handler.h"
#include "singerstone_com_superapp_ndkinterface_CrashLib.h"


bool DumpCallback(const google_breakpad::MinidumpDescriptor &descriptor,
                  void *context,
                  bool succeeded) {
    return succeeded;
}

JNIEXPORT void JNICALL Java_singerstone_com_superapp_ndkinterface_CrashLib_breakpadInit
  (JNIEnv *env, jclass jz, jstring dump_path){
 const char *path = env->GetStringUTFChars(dump_path, 0);

    google_breakpad::MinidumpDescriptor descriptor(path);
    static google_breakpad::ExceptionHandler eh(descriptor, NULL, DumpCallback, NULL, true, -1);
    env->ReleaseStringUTFChars(dump_path, path);
  }