Classfile /Users/yogachen/Documents/GitProj/SuperApp/app/build/intermediates/classes/debug/singerstone/com/superapp/TouchEvent/Test.class
  Last modified 2019-7-2; size 4624 bytes
  MD5 checksum 611c3000d45d272bde5e983280742662
  Compiled from "Test.java"
public class singerstone.com.superapp.TouchEvent.Test
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
    #1 = Methodref          #21.#99       // java/lang/Object."<init>":()V
    #2 = InvokeDynamic      #0:#104       // #0:onclick:()Lsingerstone/com/superapp/TouchEvent/ITest;
    #3 = InvokeDynamic      #1:#106       // #1:onClick:(IJLjava/lang/Object;)Landroid/view/View$OnClickListener;
    #4 = InvokeDynamic      #2:#109       // #2:onClick:()Landroid/content/DialogInterface$OnClickListener;
    #5 = InvokeDynamic      #3:#112       // #3:onItemClick:()Lsingerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener;
    #6 = InvokeDynamic      #4:#115       // #4:onItemClick:()Landroid/widget/AdapterView$OnItemClickListener;
    #7 = InvokeDynamic      #5:#118       // #5:onCheckedChanged:()Landroid/widget/RadioGroup$OnCheckedChangeListener;
    #8 = InvokeDynamic      #6:#121       // #6:onCheckedChanged:()Landroid/widget/CompoundButton$OnCheckedChangeListener;
    #9 = Class              #122          // android/view/View
   #10 = Methodref          #9.#123       // android/view/View.setVisibility:(I)V
   #11 = Class              #124          // java/lang/StringBuilder
   #12 = Methodref          #11.#99       // java/lang/StringBuilder."<init>":()V
   #13 = Methodref          #11.#125      // java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
   #14 = String             #126          //
   #15 = Methodref          #11.#127      // java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
   #16 = Methodref          #11.#128      // java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
   #17 = Methodref          #11.#129      // java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
   #18 = Methodref          #11.#130      // java/lang/StringBuilder.toString:()Ljava/lang/String;
   #19 = Methodref          #131.#132     // singerstone/com/superapp/utils/L.i:(Ljava/lang/String;)V
   #20 = Class              #133          // singerstone/com/superapp/TouchEvent/Test
   #21 = Class              #134          // java/lang/Object
   #22 = Utf8               <init>
   #23 = Utf8               ()V
   #24 = Utf8               Code
   #25 = Utf8               LineNumberTable
   #26 = Utf8               LocalVariableTable
   #27 = Utf8               this
   #28 = Utf8               Lsingerstone/com/superapp/TouchEvent/Test;
   #29 = Utf8               test
   #30 = Utf8               (IJLjava/lang/Object;)V
   #31 = Utf8               in
   #32 = Utf8               I
   #33 = Utf8               j
   #34 = Utf8               J
   #35 = Utf8               o
   #36 = Utf8               Ljava/lang/Object;
   #37 = Utf8               iTest
   #38 = Utf8               Lsingerstone/com/superapp/TouchEvent/ITest;
   #39 = Utf8               listener
   #40 = Class              #135          // android/view/View$OnClickListener
   #41 = Utf8               OnClickListener
   #42 = Utf8               InnerClasses
   #43 = Utf8               Landroid/view/View$OnClickListener;
   #44 = Utf8               listener1
   #45 = Class              #137          // android/content/DialogInterface$OnClickListener
   #46 = Utf8               Landroid/content/DialogInterface$OnClickListener;
   #47 = Utf8               onItemClickListener
   #48 = Class              #139          // singerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener
   #49 = Utf8               OnItemClickListener
   #50 = Utf8               Lsingerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener;
   #51 = Utf8               listener2
   #52 = Class              #141          // android/widget/AdapterView$OnItemClickListener
   #53 = Utf8               Landroid/widget/AdapterView$OnItemClickListener;
   #54 = Utf8               listener3
   #55 = Class              #143          // android/widget/RadioGroup$OnCheckedChangeListener
   #56 = Utf8               OnCheckedChangeListener
   #57 = Utf8               Landroid/widget/RadioGroup$OnCheckedChangeListener;
   #58 = Utf8               listener4
   #59 = Class              #145          // android/widget/CompoundButton$OnCheckedChangeListener
   #60 = Utf8               Landroid/widget/CompoundButton$OnCheckedChangeListener;
   #61 = Utf8               test2
   #62 = Utf8               (Landroid/view/View;)V
   #63 = Utf8               view
   #64 = Utf8               Landroid/view/View;
   #65 = Utf8               lambda$test$6
   #66 = Utf8               (Landroid/widget/CompoundButton;Z)V
   #67 = Utf8               buttonView
   #68 = Utf8               Landroid/widget/CompoundButton;
   #69 = Utf8               isChecked
   #70 = Utf8               Z
   #71 = Utf8               lambda$test$5
   #72 = Utf8               (Landroid/widget/RadioGroup;I)V
   #73 = Utf8               group
   #74 = Utf8               Landroid/widget/RadioGroup;
   #75 = Utf8               checkedId
   #76 = Utf8               lambda$test$4
   #77 = Utf8               (Landroid/widget/AdapterView;Landroid/view/View;IJ)V
   #78 = Utf8               parent
   #79 = Utf8               Landroid/widget/AdapterView;
   #80 = Utf8               view12
   #81 = Utf8               position
   #82 = Utf8               id
   #83 = Utf8               lambda$test$3
   #84 = Utf8               (ILsingerstone/com/superapp/Dialog/IDialogItem;)V
   #85 = Utf8               item
   #86 = Utf8               Lsingerstone/com/superapp/Dialog/IDialogItem;
   #87 = Utf8               lambda$test$2
   #88 = Utf8               (Landroid/content/DialogInterface;I)V
   #89 = Utf8               dialogInterface
   #90 = Utf8               Landroid/content/DialogInterface;
   #91 = Utf8               i
   #92 = Utf8               lambda$test$1
   #93 = Utf8               (IJLjava/lang/Object;Landroid/view/View;)V
   #94 = Utf8               v
   #95 = Utf8               lambda$test$0
   #96 = Utf8               view1
   #97 = Utf8               SourceFile
   #98 = Utf8               Test.java
   #99 = NameAndType        #22:#23       // "<init>":()V
  #100 = Utf8               BootstrapMethods
  #101 = MethodHandle       #6:#146       // invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #102 = MethodType         #62           //  (Landroid/view/View;)V
  #103 = MethodHandle       #6:#147       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$0:(Landroid/view/View;)V
  #104 = NameAndType        #148:#149     // onclick:()Lsingerstone/com/superapp/TouchEvent/ITest;
  #105 = MethodHandle       #6:#150       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$1:(IJLjava/lang/Object;Landroid/view/View;)V
  #106 = NameAndType        #151:#152     // onClick:(IJLjava/lang/Object;)Landroid/view/View$OnClickListener;
  #107 = MethodType         #88           //  (Landroid/content/DialogInterface;I)V
  #108 = MethodHandle       #6:#153       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$2:(Landroid/content/DialogInterface;I)V
  #109 = NameAndType        #151:#154     // onClick:()Landroid/content/DialogInterface$OnClickListener;
  #110 = MethodType         #84           //  (ILsingerstone/com/superapp/Dialog/IDialogItem;)V
  #111 = MethodHandle       #6:#155       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$3:(ILsingerstone/com/superapp/Dialog/IDialogItem;)V
  #112 = NameAndType        #156:#157     // onItemClick:()Lsingerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener;
  #113 = MethodType         #77           //  (Landroid/widget/AdapterView;Landroid/view/View;IJ)V
  #114 = MethodHandle       #6:#158       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$4:(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
  #115 = NameAndType        #156:#159     // onItemClick:()Landroid/widget/AdapterView$OnItemClickListener;
  #116 = MethodType         #72           //  (Landroid/widget/RadioGroup;I)V
  #117 = MethodHandle       #6:#160       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$5:(Landroid/widget/RadioGroup;I)V
  #118 = NameAndType        #161:#162     // onCheckedChanged:()Landroid/widget/RadioGroup$OnCheckedChangeListener;
  #119 = MethodType         #66           //  (Landroid/widget/CompoundButton;Z)V
  #120 = MethodHandle       #6:#163       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$6:(Landroid/widget/CompoundButton;Z)V
  #121 = NameAndType        #161:#164     // onCheckedChanged:()Landroid/widget/CompoundButton$OnCheckedChangeListener;
  #122 = Utf8               android/view/View
  #123 = NameAndType        #165:#166     // setVisibility:(I)V
  #124 = Utf8               java/lang/StringBuilder
  #125 = NameAndType        #167:#168     // append:(I)Ljava/lang/StringBuilder;
  #126 = Utf8
  #127 = NameAndType        #167:#169     // append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
  #128 = NameAndType        #167:#170     // append:(J)Ljava/lang/StringBuilder;
  #129 = NameAndType        #167:#171     // append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
  #130 = NameAndType        #172:#173     // toString:()Ljava/lang/String;
  #131 = Class              #174          // singerstone/com/superapp/utils/L
  #132 = NameAndType        #91:#175      // i:(Ljava/lang/String;)V
  #133 = Utf8               singerstone/com/superapp/TouchEvent/Test
  #134 = Utf8               java/lang/Object
  #135 = Utf8               android/view/View$OnClickListener
  #136 = Class              #176          // android/content/DialogInterface
  #137 = Utf8               android/content/DialogInterface$OnClickListener
  #138 = Class              #177          // singerstone/com/superapp/Dialog/DialogItemAdapter
  #139 = Utf8               singerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener
  #140 = Class              #178          // android/widget/AdapterView
  #141 = Utf8               android/widget/AdapterView$OnItemClickListener
  #142 = Class              #179          // android/widget/RadioGroup
  #143 = Utf8               android/widget/RadioGroup$OnCheckedChangeListener
  #144 = Class              #180          // android/widget/CompoundButton
  #145 = Utf8               android/widget/CompoundButton$OnCheckedChangeListener
  #146 = Methodref          #181.#182     // java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #147 = Methodref          #20.#183      // singerstone/com/superapp/TouchEvent/Test.lambda$test$0:(Landroid/view/View;)V
  #148 = Utf8               onclick
  #149 = Utf8               ()Lsingerstone/com/superapp/TouchEvent/ITest;
  #150 = Methodref          #20.#184      // singerstone/com/superapp/TouchEvent/Test.lambda$test$1:(IJLjava/lang/Object;Landroid/view/View;)V
  #151 = Utf8               onClick
  #152 = Utf8               (IJLjava/lang/Object;)Landroid/view/View$OnClickListener;
  #153 = Methodref          #20.#185      // singerstone/com/superapp/TouchEvent/Test.lambda$test$2:(Landroid/content/DialogInterface;I)V
  #154 = Utf8               ()Landroid/content/DialogInterface$OnClickListener;
  #155 = Methodref          #20.#186      // singerstone/com/superapp/TouchEvent/Test.lambda$test$3:(ILsingerstone/com/superapp/Dialog/IDialogItem;)V
  #156 = Utf8               onItemClick
  #157 = Utf8               ()Lsingerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener;
  #158 = Methodref          #20.#187      // singerstone/com/superapp/TouchEvent/Test.lambda$test$4:(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
  #159 = Utf8               ()Landroid/widget/AdapterView$OnItemClickListener;
  #160 = Methodref          #20.#188      // singerstone/com/superapp/TouchEvent/Test.lambda$test$5:(Landroid/widget/RadioGroup;I)V
  #161 = Utf8               onCheckedChanged
  #162 = Utf8               ()Landroid/widget/RadioGroup$OnCheckedChangeListener;
  #163 = Methodref          #20.#189      // singerstone/com/superapp/TouchEvent/Test.lambda$test$6:(Landroid/widget/CompoundButton;Z)V
  #164 = Utf8               ()Landroid/widget/CompoundButton$OnCheckedChangeListener;
  #165 = Utf8               setVisibility
  #166 = Utf8               (I)V
  #167 = Utf8               append
  #168 = Utf8               (I)Ljava/lang/StringBuilder;
  #169 = Utf8               (Ljava/lang/String;)Ljava/lang/StringBuilder;
  #170 = Utf8               (J)Ljava/lang/StringBuilder;
  #171 = Utf8               (Ljava/lang/Object;)Ljava/lang/StringBuilder;
  #172 = Utf8               toString
  #173 = Utf8               ()Ljava/lang/String;
  #174 = Utf8               singerstone/com/superapp/utils/L
  #175 = Utf8               (Ljava/lang/String;)V
  #176 = Utf8               android/content/DialogInterface
  #177 = Utf8               singerstone/com/superapp/Dialog/DialogItemAdapter
  #178 = Utf8               android/widget/AdapterView
  #179 = Utf8               android/widget/RadioGroup
  #180 = Utf8               android/widget/CompoundButton
  #181 = Class              #190          // java/lang/invoke/LambdaMetafactory
  #182 = NameAndType        #191:#194     // metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #183 = NameAndType        #95:#62       // lambda$test$0:(Landroid/view/View;)V
  #184 = NameAndType        #92:#93       // lambda$test$1:(IJLjava/lang/Object;Landroid/view/View;)V
  #185 = NameAndType        #87:#88       // lambda$test$2:(Landroid/content/DialogInterface;I)V
  #186 = NameAndType        #83:#84       // lambda$test$3:(ILsingerstone/com/superapp/Dialog/IDialogItem;)V
  #187 = NameAndType        #76:#77       // lambda$test$4:(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
  #188 = NameAndType        #71:#72       // lambda$test$5:(Landroid/widget/RadioGroup;I)V
  #189 = NameAndType        #65:#66       // lambda$test$6:(Landroid/widget/CompoundButton;Z)V
  #190 = Utf8               java/lang/invoke/LambdaMetafactory
  #191 = Utf8               metafactory
  #192 = Class              #196          // java/lang/invoke/MethodHandles$Lookup
  #193 = Utf8               Lookup
  #194 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #195 = Class              #197          // java/lang/invoke/MethodHandles
  #196 = Utf8               java/lang/invoke/MethodHandles$Lookup
  #197 = Utf8               java/lang/invoke/MethodHandles
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
        line 16: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lsingerstone/com/superapp/TouchEvent/Test;

  void test(int, long, java.lang.Object);
    descriptor: (IJLjava/lang/Object;)V
    flags:
    Code:
      stack=4, locals=12, args_size=4
         0: invokedynamic #2,  0              // InvokeDynamic #0:onclick:()Lsingerstone/com/superapp/TouchEvent/ITest;
         5: astore        5
         7: iload_1
         8: lload_2
         9: aload         4
        11: invokedynamic #3,  0              // InvokeDynamic #1:onClick:(IJLjava/lang/Object;)Landroid/view/View$OnClickListener;
        16: astore        6
        18: invokedynamic #4,  0              // InvokeDynamic #2:onClick:()Landroid/content/DialogInterface$OnClickListener;
        23: astore        7
        25: invokedynamic #5,  0              // InvokeDynamic #3:onItemClick:()Lsingerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener;
        30: astore        8
        32: invokedynamic #6,  0              // InvokeDynamic #4:onItemClick:()Landroid/widget/AdapterView$OnItemClickListener;
        37: astore        9
        39: invokedynamic #7,  0              // InvokeDynamic #5:onCheckedChanged:()Landroid/widget/RadioGroup$OnCheckedChangeListener;
        44: astore        10
        46: invokedynamic #8,  0              // InvokeDynamic #6:onCheckedChanged:()Landroid/widget/CompoundButton$OnCheckedChangeListener;
        51: astore        11
        53: return
      LineNumberTable:
        line 18: 0
        line 19: 7
        line 23: 18
        line 26: 25
        line 29: 32
        line 32: 39
        line 35: 46
        line 39: 53
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      54     0  this   Lsingerstone/com/superapp/TouchEvent/Test;
            0      54     1    in   I
            0      54     2     j   J
            0      54     4     o   Ljava/lang/Object;
            7      47     5 iTest   Lsingerstone/com/superapp/TouchEvent/ITest;
           18      36     6 listener   Landroid/view/View$OnClickListener;
           25      29     7 listener1   Landroid/content/DialogInterface$OnClickListener;
           32      22     8 onItemClickListener   Lsingerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener;
           39      15     9 listener2   Landroid/widget/AdapterView$OnItemClickListener;
           46       8    10 listener3   Landroid/widget/RadioGroup$OnCheckedChangeListener;
           53       1    11 listener4   Landroid/widget/CompoundButton$OnCheckedChangeListener;
}
SourceFile: "Test.java"
InnerClasses:
     public static #41= #40 of #9; //OnClickListener=class android/view/View$OnClickListener of class android/view/View
     public static #41= #45 of #136; //OnClickListener=class android/content/DialogInterface$OnClickListener of class android/content/DialogInterface
     public static #49= #48 of #138; //OnItemClickListener=class singerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener of class singerstone/com/superapp/Dialog/DialogItemAdapter
     public static #49= #52 of #140; //OnItemClickListener=class android/widget/AdapterView$OnItemClickListener of class android/widget/AdapterView
     public static #56= #55 of #142; //OnCheckedChangeListener=class android/widget/RadioGroup$OnCheckedChangeListener of class android/widget/RadioGroup
     public static #56= #59 of #144; //OnCheckedChangeListener=class android/widget/CompoundButton$OnCheckedChangeListener of class android/widget/CompoundButton
     public static final #193= #192 of #195; //Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
BootstrapMethods:
  0: #101 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #102 (Landroid/view/View;)V
      #103 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$0:(Landroid/view/View;)V
      #102 (Landroid/view/View;)V
  1: #101 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #102 (Landroid/view/View;)V
      #105 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$1:(IJLjava/lang/Object;Landroid/view/View;)V
      #102 (Landroid/view/View;)V
  2: #101 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #107 (Landroid/content/DialogInterface;I)V
      #108 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$2:(Landroid/content/DialogInterface;I)V
      #107 (Landroid/content/DialogInterface;I)V
  3: #101 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #110 (ILsingerstone/com/superapp/Dialog/IDialogItem;)V
      #111 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$3:(ILsingerstone/com/superapp/Dialog/IDialogItem;)V
      #110 (ILsingerstone/com/superapp/Dialog/IDialogItem;)V
  4: #101 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #113 (Landroid/widget/AdapterView;Landroid/view/View;IJ)V
      #114 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$4:(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
      #113 (Landroid/widget/AdapterView;Landroid/view/View;IJ)V
  5: #101 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #116 (Landroid/widget/RadioGroup;I)V
      #117 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$5:(Landroid/widget/RadioGroup;I)V
      #116 (Landroid/widget/RadioGroup;I)V
  6: #101 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #119 (Landroid/widget/CompoundButton;Z)V
      #120 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$6:(Landroid/widget/CompoundButton;Z)V
      #119 (Landroid/widget/CompoundButton;Z)V