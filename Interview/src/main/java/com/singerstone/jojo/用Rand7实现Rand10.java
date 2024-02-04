package com.singerstone.jojo;

import java.util.Random;

public class 用Rand7实现Rand10 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(new 用Rand7实现Rand10().rand10_1());
        }

    }

    // 拒绝采样1 枚举
    public int rand10() {
        int sum;
        do {
            int row = rand7();
            int column = rand7();
            // 00 01 02 03 04 05 06
            // 10 11 12 13 14 15 16
            sum = row * 7 + column; //1-49
        } while (sum > 40);

        int i = sum % 10;
        return i == 0 ? 10 : i;
    }


    // 拒绝采样2 相乘 1/10 = 1/2 * 1/5
    public int rand10_1() {
        int rand1; // 生成 1-6 用来生成 奇偶
        do {
            rand1 = rand7();
        } while (rand1 > 6);
        int rand2; // 生成 1-5 用来生成数
        do {
            rand2 = rand7();
        } while (rand2 > 5);

        // 构造概率
        return rand1 % 2 == 0 ? rand2 : rand2 + 5;


    }


    //make 1-7
    private int rand7() {
        return new Random().nextInt(7) + 1;
    }
}
