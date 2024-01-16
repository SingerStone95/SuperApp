package com.singerstone.jojo;

public class 只出现一次的数字 {
    public static void main(String[] args) {
        System.out.println(new 只出现一次的数字().singleNumber(new int[]{1, 1, 2, 2, 3}));
        System.out.println(new 只出现一次的数字().singleNumber2(new int[]{1, 1, 1, 2, 2, 2, 3}));

    }

    /**
     * 其他数字均出现两次
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result = result ^ num;
        }
        return result;

    }

    /**
     * 其他数字均出现3次
     * 思路：统计所有数字中每个位中1出现的总数，那么对于某个位，1出现的次数一定是3的倍数+1或0，那么对这个数%3得到的结果就是目的数字在该位上的值。
     */
    public int singleNumber2(int[] nums) {
        int result = 0;
        // 整型按32位处理
        for (int i = 0; i < 32; i++) { // 统计每一位上 1 的个数
            int count = 0;
            for (int num : nums) {
                if (((num >> i) & 1) == 1) {
                    count++;
                }
            }
            if (count % 3 == 1) { // 这里只会是 1 或者 0 ，不可能为 2
                result = result | (1 << i);
            }

        }
        return result;
    }

}
