package com.singerstone;

/**
 * 安装 ASM ByteCode Viewer
 * 在 .class 文件右键查看即可
 */
public class TestByteCode {
    public static void main(String[] args) {
        // 拆箱装箱字节码
        Integer num1 = 127;
        Integer num2 = 127;
        System.out.println(num1 == num2);

        //
        System.out.println(new TestByteCode().level1(1, 2));
        System.out.println(getStaticNum(10));
    }

    int mA = 10;

    int level1(int a1, int a2) {
        synchronized (TestByteCode.class) {
            int r = a1 + a2;
            return level2(r, mA);
        }
    }

    int level2(int a1, int a2) {
        synchronized (this) {
            int a = mA;
            mA = a + a1 + a2;
            return mA;
        }
    }

    public static int getStaticNum(int a) {
        return a + 1;

    }

}
