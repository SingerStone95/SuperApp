#include <jni.h>

class AppCheck {
 public:
  AppCheck();
  ~AppCheck();
  void CheckAppState(JNIEnv* env);
  static jstring CharToJstring(JNIEnv* env, const char* str);
  static char* JstringToChar(JNIEnv* env, jstring jstr);

 private:
};
