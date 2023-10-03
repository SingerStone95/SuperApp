package com.singerstone.jojo;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/10/2 16:30
 * @see {@link }
 */
public class 数组中字母出现的次数2 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 1, 1, 2, 3, 3, 3, 5, 5, 5};
        System.out.println(new 数组中字母出现的次数2().trainingPlan(array));
    }

    /**
     * 输入：actions = [12, 1, 6, 12, 6, 12, 6]
     * 输出：1
     * 最简单的hashmap就不要写了
     * 直接上二进制法： 每个位数上%3余数变化 00-01-10
     * 难点在于找到左边一位 two 右边一位 one 的二进制关系，数电
     *
     */
    public int trainingPlan(int[] actions) {
        int ones = 0, twos = 0;
        for (int action : actions) {
            ones = ones ^ action & ~twos;
            twos = twos ^ action & ~ones;
        }
        return ones;
    }
}
