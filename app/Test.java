package singerstone.com.superapp.javassist;Classfile /Users/yogachen/Documents/GitProj/SuperApp/app/build/intermediates/classes/debug/singerstone/com/superapp/TouchEvent/Test.class
  Last modified 2019-6-25; size 1755 bytes
  MD5 checksum 2c42c0f2852df2323e24f9e0a498ccab
  Compiled from "Test.java"
public class singerstone.com.superapp.TouchEvent.Test
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #10.#32        // java/lang/Object."<init>":()V
   #2 = InvokeDynamic      #0:#37         // #0:onClick:()Landroid/view/View$OnClickListener;
   #3 = Methodref          #7.#38         // android/view/View.setOnClickListener:(Landroid/view/View$OnClickListener;)V
   #4 = InvokeDynamic      #1:#37         // #1:onClick:()Landroid/view/View$OnClickListener;
   #5 = InvokeDynamic      #2:#41         // #2:onclick:()Lsingerstone/com/superapp/TouchEvent/ITest;
   #6 = InterfaceMethodref #42.#43        // singerstone/com/superapp/TouchEvent/ITest.onclick:(Landroid/view/View;)V
   #7 = Class              #44            // android/view/View
   #8 = Methodref          #7.#45         // android/view/View.setVisibility:(I)V
   #9 = Class              #46            // singerstone/com/superapp/TouchEvent/Test
  #10 = Class              #47            // java/lang/Object
  #11 = Utf8               <init>
  #12 = Utf8               ()V
  #13 = Utf8               Code
  #14 = Utf8               LineNumberTable
  #15 = Utf8               LocalVariableTable
  #16 = Utf8               this
  #17 = Utf8               Lsingerstone/com/superapp/TouchEvent/Test;
  #18 = Utf8               test
  #19 = Utf8               (Landroid/view/View;)V
  #20 = Utf8               view
  #21 = Utf8               Landroid/view/View;
  #22 = Utf8               iTest
  #23 = Utf8               Lsingerstone/com/superapp/TouchEvent/ITest;
  #24 = Utf8               test2
  #25 = Utf8               lambda$test$2
  #26 = Utf8               view1
  #27 = Utf8               lambda$test$1
  #28 = Utf8               v
  #29 = Utf8               lambda$test$0
  #30 = Utf8               SourceFile
  #31 = Utf8               Test.java
  #32 = NameAndType        #11:#12        // "<init>":()V
  #33 = Utf8               BootstrapMethods
  #34 = MethodHandle       #6:#48         // invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #35 = MethodType         #19            //  (Landroid/view/View;)V
  #36 = MethodHandle       #6:#49         // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$0:(Landroid/view/View;)V
  #37 = NameAndType        #50:#54        // onClick:()Landroid/view/View$OnClickListener;
  #38 = NameAndType        #55:#56        // setOnClickListener:(Landroid/view/View$OnClickListener;)V
  #39 = MethodHandle       #6:#57         // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$1:(Landroid/view/View;)V
  #40 = MethodHandle       #6:#58         // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$2:(Landroid/view/View;)V
  #41 = NameAndType        #59:#60        // onclick:()Lsingerstone/com/superapp/TouchEvent/ITest;
  #42 = Class              #61            // singerstone/com/superapp/TouchEvent/ITest
  #43 = NameAndType        #59:#19        // onclick:(Landroid/view/View;)V
  #44 = Utf8               android/view/View
  #45 = NameAndType        #62:#63        // setVisibility:(I)V
  #46 = Utf8               singerstone/com/superapp/TouchEvent/Test
  #47 = Utf8               java/lang/Object
  #48 = Methodref          #64.#65        // java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #49 = Methodref          #9.#66         // singerstone/com/superapp/TouchEvent/Test.lambda$test$0:(Landroid/view/View;)V
  #50 = Utf8               onClick
  #51 = Class              #67            // android/view/View$OnClickListener
  #52 = Utf8               OnClickListener
  #53 = Utf8               InnerClasses
  #54 = Utf8               ()Landroid/view/View$OnClickListener;
  #55 = Utf8               setOnClickListener
  #56 = Utf8               (Landroid/view/View$OnClickListener;)V
  #57 = Methodref          #9.#68         // singerstone/com/superapp/TouchEvent/Test.lambda$test$1:(Landroid/view/View;)V
  #58 = Methodref          #9.#69         // singerstone/com/superapp/TouchEvent/Test.lambda$test$2:(Landroid/view/View;)V
  #59 = Utf8               onclick
  #60 = Utf8               ()Lsingerstone/com/superapp/TouchEvent/ITest;
  #61 = Utf8               singerstone/com/superapp/TouchEvent/ITest
  #62 = Utf8               setVisibility
  #63 = Utf8               (I)V
  #64 = Class              #70            // java/lang/invoke/LambdaMetafactory
  #65 = NameAndType        #71:#74        // metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #66 = NameAndType        #29:#19        // lambda$test$0:(Landroid/view/View;)V
  #67 = Utf8               android/view/View$OnClickListener
  #68 = NameAndType        #27:#19        // lambda$test$1:(Landroid/view/View;)V
  #69 = NameAndType        #25:#19        // lambda$test$2:(Landroid/view/View;)V
  #70 = Utf8               java/lang/invoke/LambdaMetafactory
  #71 = Utf8               metafactory
  #72 = Class              #76            // java/lang/invoke/MethodHandles$Lookup
  #73 = Utf8               Lookup
  #74 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #75 = Class              #77            // java/lang/invoke/MethodHandles
  #76 = Utf8               java/lang/invoke/MethodHandles$Lookup
  #77 = Utf8               java/lang/invoke/MethodHandles
{
  public singerstone.com.superapp.TouchEvent.Test();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 5: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lsingerstone/com/superapp/TouchEvent/Test;

  void test(android.view.View);
    descriptor: (Landroid/view/View;)V
    flags:
    Code:
      stack=2, locals=3, args_size=2
         0: aload_1
         1: invokedynamic #2,  0              // InvokeDynamic #0:onClick:()Landroid/view/View$OnClickListener;
         6: invokevirtual #3                  // Method android/view/View.setOnClickListener:(Landroid/view/View$OnClickListener;)V
         9: aload_1
        10: invokedynamic #4,  0              // InvokeDynamic #1:onClick:()Landroid/view/View$OnClickListener;
        15: invokevirtual #3                  // Method android/view/View.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        18: invokedynamic #5,  0              // InvokeDynamic #2:onclick:()Lsingerstone/com/superapp/TouchEvent/ITest;
        23: astore_2
        24: aload_2
        25: aconst_null
        26: invokeinterface #6,  2            // InterfaceMethod singerstone/com/superapp/TouchEvent/ITest.onclick:(Landroid/view/View;)V
        31: return
      LineNumberTable:
        line 7: 0
        line 10: 9
        line 11: 18
        line 12: 24
        line 13: 31
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      32     0  this   Lsingerstone/com/superapp/TouchEvent/Test;
            0      32     1  view   Landroid/view/View;
           24       8     2 iTest   Lsingerstone/com/superapp/TouchEvent/ITest;
}
SourceFile: "Test.java"
InnerClasses:
     public static #52= #51 of #7; //OnClickListener=class android/view/View$OnClickListener of class android/view/View
     public static final #73= #72 of #75; //Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
BootstrapMethods:
  0: #34 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #35 (Landroid/view/View;)V
      #36 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$0:(Landroid/view/View;)V
      #35 (Landroid/view/View;)V
  1: #34 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #35 (Landroid/view/View;)V
      #39 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$1:(Landroid/view/View;)V
      #35 (Landroid/view/View;)V
  2: #34 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #35 (Landroid/view/View;)V
      #40 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$2:(Landroid/view/View;)V
      #35 (Landroid/view/View;)V