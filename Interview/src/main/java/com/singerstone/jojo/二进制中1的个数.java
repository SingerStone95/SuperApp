package com.singerstone.jojo;

public class 二进制中1的个数 {

    public static void main(String[] args) {

        System.out.println(hammingWeight(0b111000111));
    }

    public static int hammingWeight(int n) {
        int count = 0;
        while (n > 0) {
            count += (n & 1);
            n = n / 2;
        }
        return count;

    }
}
