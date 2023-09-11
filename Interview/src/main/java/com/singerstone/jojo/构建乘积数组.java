package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/10 12:09
 * @see {@link }
 * 不能用除法
 */
public class 构建乘积数组 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        printArray(new 构建乘积数组().constructArr(array));

    }

    //输入: [1,2,3,4,5]
    //输出: [120,60,40,30,24]
    public int[] constructArr(int[] a) {
        if (a.length == 0) {
            return a;
        }
        int[] left = new int[a.length]; // left[i] 表示 a[0]*..a[i-1]
        int[] right = new int[a.length]; // right[i] 表示 a[i+1]*..a[len-1]

        left[0] = 1;
        for (int i = 1; i < a.length; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }
        right[a.length - 1] = 1;
        for (int i = a.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }

        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = left[i] * right[i];
        }

        return result;


    }
}
