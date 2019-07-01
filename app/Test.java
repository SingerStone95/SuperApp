Classfile /Users/yogachen/Documents/GitProj/SuperApp/app/build/intermediates/classes/debug/singerstone/com/superapp/TouchEvent/Test.class
Last modified 2019-7-1; size 4046 bytes
        MD5 checksum 12c4f24f051e3c4d7ee59e6e1708325f
        Compiled from "Test.java"
public class singerstone.com.superapp.TouchEvent.Test
        minor version: 0
        major version: 52
        flags: ACC_PUBLIC, ACC_SUPER
        Constant pool:
        #1 = Methodref          #12.#84       // java/lang/Object."<init>":()V
        #2 = InvokeDynamic      #0:#89        // #0:onclick:()Lsingerstone/com/superapp/TouchEvent/ITest;
        #3 = InvokeDynamic      #1:#91        // #1:onClick:()Landroid/view/View$OnClickListener;
        #4 = InvokeDynamic      #2:#94        // #2:onClick:()Landroid/content/DialogInterface$OnClickListener;
        #5 = InvokeDynamic      #3:#97        // #3:onItemClick:()Lsingerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener;
        #6 = InvokeDynamic      #4:#100       // #4:onItemClick:()Landroid/widget/AdapterView$OnItemClickListener;
        #7 = InvokeDynamic      #5:#103       // #5:onCheckedChanged:()Landroid/widget/RadioGroup$OnCheckedChangeListener;
        #8 = InvokeDynamic      #6:#106       // #6:onCheckedChanged:()Landroid/widget/CompoundButton$OnCheckedChangeListener;
        #9 = Class              #107          // android/view/View
        #10 = Methodref          #9.#108       // android/view/View.setVisibility:(I)V
        #11 = Class              #109          // singerstone/com/superapp/TouchEvent/Test
        #12 = Class              #110          // java/lang/Object
        #13 = Utf8               <init>
   #14 = Utf8               ()V
           #15 = Utf8               Code
           #16 = Utf8               LineNumberTable
           #17 = Utf8               LocalVariableTable
           #18 = Utf8               this
           #19 = Utf8               Lsingerstone/com/superapp/TouchEvent/Test;
           #20 = Utf8               test
           #21 = Utf8               (Landroid/view/View;)V
           #22 = Utf8               view
           #23 = Utf8               Landroid/view/View;
           #24 = Utf8               iTest
           #25 = Utf8               Lsingerstone/com/superapp/TouchEvent/ITest;
           #26 = Utf8               listener
           #27 = Class              #111          // android/view/View$OnClickListener
           #28 = Utf8               OnClickListener
           #29 = Utf8               InnerClasses
           #30 = Utf8               Landroid/view/View$OnClickListener;
           #31 = Utf8               listener1
           #32 = Class              #113          // android/content/DialogInterface$OnClickListener
           #33 = Utf8               Landroid/content/DialogInterface$OnClickListener;
           #34 = Utf8               onItemClickListener
           #35 = Class              #115          // singerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener
           #36 = Utf8               OnItemClickListener
           #37 = Utf8               Lsingerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener;
           #38 = Utf8               listener2
           #39 = Class              #117          // android/widget/AdapterView$OnItemClickListener
           #40 = Utf8               Landroid/widget/AdapterView$OnItemClickListener;
           #41 = Utf8               listener3
           #42 = Class              #119          // android/widget/RadioGroup$OnCheckedChangeListener
           #43 = Utf8               OnCheckedChangeListener
           #44 = Utf8               Landroid/widget/RadioGroup$OnCheckedChangeListener;
           #45 = Utf8               listener4
           #46 = Class              #121          // android/widget/CompoundButton$OnCheckedChangeListener
           #47 = Utf8               Landroid/widget/CompoundButton$OnCheckedChangeListener;
           #48 = Utf8               test2
           #49 = Utf8               lambda$test$6
           #50 = Utf8               (Landroid/widget/CompoundButton;Z)V
           #51 = Utf8               buttonView
           #52 = Utf8               Landroid/widget/CompoundButton;
           #53 = Utf8               isChecked
           #54 = Utf8               Z
           #55 = Utf8               lambda$test$5
           #56 = Utf8               (Landroid/widget/RadioGroup;I)V
           #57 = Utf8               group
           #58 = Utf8               Landroid/widget/RadioGroup;
           #59 = Utf8               checkedId
           #60 = Utf8               I
           #61 = Utf8               lambda$test$4
           #62 = Utf8               (Landroid/widget/AdapterView;Landroid/view/View;IJ)V
           #63 = Utf8               parent
           #64 = Utf8               Landroid/widget/AdapterView;
           #65 = Utf8               view12
           #66 = Utf8               position
           #67 = Utf8               id
           #68 = Utf8               J
           #69 = Utf8               lambda$test$3
           #70 = Utf8               (ILsingerstone/com/superapp/Dialog/IDialogItem;)V
           #71 = Utf8               item
           #72 = Utf8               Lsingerstone/com/superapp/Dialog/IDialogItem;
           #73 = Utf8               lambda$test$2
           #74 = Utf8               (Landroid/content/DialogInterface;I)V
           #75 = Utf8               dialogInterface
           #76 = Utf8               Landroid/content/DialogInterface;
           #77 = Utf8               i
           #78 = Utf8               lambda$test$1
           #79 = Utf8               v
           #80 = Utf8               lambda$test$0
           #81 = Utf8               view1
           #82 = Utf8               SourceFile
           #83 = Utf8               Test.java
           #84 = NameAndType        #13:#14       // "<init>":()V
           #85 = Utf8               BootstrapMethods
           #86 = MethodHandle       #6:#122       // invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
           #87 = MethodType         #21           //  (Landroid/view/View;)V
           #88 = MethodHandle       #6:#123       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$0:(Landroid/view/View;)V
           #89 = NameAndType        #124:#125     // onclick:()Lsingerstone/com/superapp/TouchEvent/ITest;
           #90 = MethodHandle       #6:#126       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$1:(Landroid/view/View;)V
           #91 = NameAndType        #127:#128     // onClick:()Landroid/view/View$OnClickListener;
           #92 = MethodType         #74           //  (Landroid/content/DialogInterface;I)V
           #93 = MethodHandle       #6:#129       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$2:(Landroid/content/DialogInterface;I)V
           #94 = NameAndType        #127:#130     // onClick:()Landroid/content/DialogInterface$OnClickListener;
           #95 = MethodType         #70           //  (ILsingerstone/com/superapp/Dialog/IDialogItem;)V
           #96 = MethodHandle       #6:#131       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$3:(ILsingerstone/com/superapp/Dialog/IDialogItem;)V
           #97 = NameAndType        #132:#133     // onItemClick:()Lsingerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener;
           #98 = MethodType         #62           //  (Landroid/widget/AdapterView;Landroid/view/View;IJ)V
           #99 = MethodHandle       #6:#134       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$4:(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
           #100 = NameAndType        #132:#135     // onItemClick:()Landroid/widget/AdapterView$OnItemClickListener;
           #101 = MethodType         #56           //  (Landroid/widget/RadioGroup;I)V
           #102 = MethodHandle       #6:#136       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$5:(Landroid/widget/RadioGroup;I)V
           #103 = NameAndType        #137:#138     // onCheckedChanged:()Landroid/widget/RadioGroup$OnCheckedChangeListener;
           #104 = MethodType         #50           //  (Landroid/widget/CompoundButton;Z)V
           #105 = MethodHandle       #6:#139       // invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$6:(Landroid/widget/CompoundButton;Z)V
           #106 = NameAndType        #137:#140     // onCheckedChanged:()Landroid/widget/CompoundButton$OnCheckedChangeListener;
           #107 = Utf8               android/view/View
           #108 = NameAndType        #141:#142     // setVisibility:(I)V
           #109 = Utf8               singerstone/com/superapp/TouchEvent/Test
           #110 = Utf8               java/lang/Object
           #111 = Utf8               android/view/View$OnClickListener
           #112 = Class              #143          // android/content/DialogInterface
           #113 = Utf8               android/content/DialogInterface$OnClickListener
           #114 = Class              #144          // singerstone/com/superapp/Dialog/DialogItemAdapter
           #115 = Utf8               singerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener
           #116 = Class              #145          // android/widget/AdapterView
           #117 = Utf8               android/widget/AdapterView$OnItemClickListener
           #118 = Class              #146          // android/widget/RadioGroup
           #119 = Utf8               android/widget/RadioGroup$OnCheckedChangeListener
           #120 = Class              #147          // android/widget/CompoundButton
           #121 = Utf8               android/widget/CompoundButton$OnCheckedChangeListener
           #122 = Methodref          #148.#149     // java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
           #123 = Methodref          #11.#150      // singerstone/com/superapp/TouchEvent/Test.lambda$test$0:(Landroid/view/View;)V
           #124 = Utf8               onclick
           #125 = Utf8               ()Lsingerstone/com/superapp/TouchEvent/ITest;
           #126 = Methodref          #11.#151      // singerstone/com/superapp/TouchEvent/Test.lambda$test$1:(Landroid/view/View;)V
           #127 = Utf8               onClick
           #128 = Utf8               ()Landroid/view/View$OnClickListener;
           #129 = Methodref          #11.#152      // singerstone/com/superapp/TouchEvent/Test.lambda$test$2:(Landroid/content/DialogInterface;I)V
           #130 = Utf8               ()Landroid/content/DialogInterface$OnClickListener;
           #131 = Methodref          #11.#153      // singerstone/com/superapp/TouchEvent/Test.lambda$test$3:(ILsingerstone/com/superapp/Dialog/IDialogItem;)V
           #132 = Utf8               onItemClick
           #133 = Utf8               ()Lsingerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener;
           #134 = Methodref          #11.#154      // singerstone/com/superapp/TouchEvent/Test.lambda$test$4:(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
           #135 = Utf8               ()Landroid/widget/AdapterView$OnItemClickListener;
           #136 = Methodref          #11.#155      // singerstone/com/superapp/TouchEvent/Test.lambda$test$5:(Landroid/widget/RadioGroup;I)V
           #137 = Utf8               onCheckedChanged
           #138 = Utf8               ()Landroid/widget/RadioGroup$OnCheckedChangeListener;
           #139 = Methodref          #11.#156      // singerstone/com/superapp/TouchEvent/Test.lambda$test$6:(Landroid/widget/CompoundButton;Z)V
           #140 = Utf8               ()Landroid/widget/CompoundButton$OnCheckedChangeListener;
           #141 = Utf8               setVisibility
           #142 = Utf8               (I)V
           #143 = Utf8               android/content/DialogInterface
           #144 = Utf8               singerstone/com/superapp/Dialog/DialogItemAdapter
           #145 = Utf8               android/widget/AdapterView
           #146 = Utf8               android/widget/RadioGroup
           #147 = Utf8               android/widget/CompoundButton
           #148 = Class              #157          // java/lang/invoke/LambdaMetafactory
           #149 = NameAndType        #158:#161     // metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
           #150 = NameAndType        #80:#21       // lambda$test$0:(Landroid/view/View;)V
           #151 = NameAndType        #78:#21       // lambda$test$1:(Landroid/view/View;)V
           #152 = NameAndType        #73:#74       // lambda$test$2:(Landroid/content/DialogInterface;I)V
           #153 = NameAndType        #69:#70       // lambda$test$3:(ILsingerstone/com/superapp/Dialog/IDialogItem;)V
           #154 = NameAndType        #61:#62       // lambda$test$4:(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
           #155 = NameAndType        #55:#56       // lambda$test$5:(Landroid/widget/RadioGroup;I)V
           #156 = NameAndType        #49:#50       // lambda$test$6:(Landroid/widget/CompoundButton;Z)V
           #157 = Utf8               java/lang/invoke/LambdaMetafactory
           #158 = Utf8               metafactory
           #159 = Class              #163          // java/lang/invoke/MethodHandles$Lookup
           #160 = Utf8               Lookup
           #161 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
           #162 = Class              #164          // java/lang/invoke/MethodHandles
           #163 = Utf8               java/lang/invoke/MethodHandles$Lookup
           #164 = Utf8               java/lang/invoke/MethodHandles
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
        line 15: 0
        LocalVariableTable:
        Start  Length  Slot  Name   Signature
        0       5     0  this   Lsingerstone/com/superapp/TouchEvent/Test;

        void test(android.view.View);
        descriptor: (Landroid/view/View;)V
        flags:
        Code:
        stack=1, locals=9, args_size=2
        0: invokedynamic #2,  0              // InvokeDynamic #0:onclick:()Lsingerstone/com/superapp/TouchEvent/ITest;
        5: astore_2
        6: invokedynamic #3,  0              // InvokeDynamic #1:onClick:()Landroid/view/View$OnClickListener;
        11: astore_3
        12: invokedynamic #4,  0              // InvokeDynamic #2:onClick:()Landroid/content/DialogInterface$OnClickListener;
        17: astore        4
        19: invokedynamic #5,  0              // InvokeDynamic #3:onItemClick:()Lsingerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener;
        24: astore        5
        26: invokedynamic #6,  0              // InvokeDynamic #4:onItemClick:()Landroid/widget/AdapterView$OnItemClickListener;
        31: astore        6
        33: invokedynamic #7,  0              // InvokeDynamic #5:onCheckedChanged:()Landroid/widget/RadioGroup$OnCheckedChangeListener;
        38: astore        7
        40: invokedynamic #8,  0              // InvokeDynamic #6:onCheckedChanged:()Landroid/widget/CompoundButton$OnCheckedChangeListener;
        45: astore        8
        47: return
        LineNumberTable:
        line 17: 0
        line 18: 6
        line 21: 12
        line 24: 19
        line 27: 26
        line 30: 33
        line 33: 40
        line 37: 47
        LocalVariableTable:
        Start  Length  Slot  Name   Signature
        0      48     0  this   Lsingerstone/com/superapp/TouchEvent/Test;
        0      48     1  view   Landroid/view/View;
        6      42     2 iTest   Lsingerstone/com/superapp/TouchEvent/ITest;
        12      36     3 listener   Landroid/view/View$OnClickListener;
        19      29     4 listener1   Landroid/content/DialogInterface$OnClickListener;
        26      22     5 onItemClickListener   Lsingerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener;
        33      15     6 listener2   Landroid/widget/AdapterView$OnItemClickListener;
        40       8     7 listener3   Landroid/widget/RadioGroup$OnCheckedChangeListener;
        47       1     8 listener4   Landroid/widget/CompoundButton$OnCheckedChangeListener;
        }
        SourceFile: "Test.java"
        InnerClasses:
public static #28= #27 of #9; //OnClickListener=class android/view/View$OnClickListener of class android/view/View
public static #28= #32 of #112; //OnClickListener=class android/content/DialogInterface$OnClickListener of class android/content/DialogInterface
public static #36= #35 of #114; //OnItemClickListener=class singerstone/com/superapp/Dialog/DialogItemAdapter$OnItemClickListener of class singerstone/com/superapp/Dialog/DialogItemAdapter
public static #36= #39 of #116; //OnItemClickListener=class android/widget/AdapterView$OnItemClickListener of class android/widget/AdapterView
public static #43= #42 of #118; //OnCheckedChangeListener=class android/widget/RadioGroup$OnCheckedChangeListener of class android/widget/RadioGroup
public static #43= #46 of #120; //OnCheckedChangeListener=class android/widget/CompoundButton$OnCheckedChangeListener of class android/widget/CompoundButton
public static final #160= #159 of #162; //Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
        BootstrapMethods:
        0: #86 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
        Method arguments:
        #87 (Landroid/view/View;)V
        #88 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$0:(Landroid/view/View;)V
        #87 (Landroid/view/View;)V
        1: #86 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
        Method arguments:
        #87 (Landroid/view/View;)V
        #90 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$1:(Landroid/view/View;)V
        #87 (Landroid/view/View;)V
        2: #86 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
        Method arguments:
        #92 (Landroid/content/DialogInterface;I)V
        #93 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$2:(Landroid/content/DialogInterface;I)V
        #92 (Landroid/content/DialogInterface;I)V
        3: #86 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
        Method arguments:
        #95 (ILsingerstone/com/superapp/Dialog/IDialogItem;)V
        #96 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$3:(ILsingerstone/com/superapp/Dialog/IDialogItem;)V
        #95 (ILsingerstone/com/superapp/Dialog/IDialogItem;)V
        4: #86 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
        Method arguments:
        #98 (Landroid/widget/AdapterView;Landroid/view/View;IJ)V
        #99 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$4:(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
        #98 (Landroid/widget/AdapterView;Landroid/view/View;IJ)V
        5: #86 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
        Method arguments:
        #101 (Landroid/widget/RadioGroup;I)V
        #102 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$5:(Landroid/widget/RadioGroup;I)V
        #101 (Landroid/widget/RadioGroup;I)V
        6: #86 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
        Method arguments:
        #104 (Landroid/widget/CompoundButton;Z)V
        #105 invokestatic singerstone/com/superapp/TouchEvent/Test.lambda$test$6:(Landroid/widget/CompoundButton;Z)V
        #104 (Landroid/widget/CompoundButton;Z)V