package com.singerstone.jojo;

public class 二进制中1的个数 {

    public static void main(String[] args) {

        System.out.println(hammingWeight(0b11111111111111111111111111111101));
    }

    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) { //重点1
            count += (n & 1);
            n = n >>> 1; //重点2
        }
        return count;

    }
}
