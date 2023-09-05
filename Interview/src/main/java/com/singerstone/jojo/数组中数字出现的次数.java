package com.singerstone.jojo;

public class 数组中数字出现的次数 {


    // 找到只出现一次的两个数
    public static void main(String[] args) {
        int[] result = new 数组中数字出现的次数().singleNumbers(new int[]{1, 1, 2, 2, 3, 4});

        for (int num : result) {
            System.out.println(num);
        }

    }

    public int[] singleNumbers(int[] nums) {
        int a = 0;//找到 a^b
        for (int num : nums) {
            a ^= num;
        }
        int b = 1;// 找到不同的位用于分割数组
        while ((a & b) == 0) {
            b <<= 1;
        }
        int[] result = new int[2];
        for (int num : nums) {
            if ((num & b) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }
        return result;

    }

}
