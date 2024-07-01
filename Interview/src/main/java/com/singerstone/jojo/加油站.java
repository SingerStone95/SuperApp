package com.singerstone.jojo;

public class 加油站 {
    public static void main(String[] args) {
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};
        System.out.println(new 加油站().canCompleteCircuit(gas, cost));
    }

    /**
     * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     * <p>
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
     * <p>
     * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
     * 示例 1:
     * <p>
     * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
     * 输出: 3
     * 示例 2:
     * <p>
     * 输入: gas = [2,3,4], cost = [3,4,3]
     * 输出: -1
     * <p>
     * 暴力直接超时，有个推论，a 只能到达 b ，那么 a 到 b 之间的节点最多也只能到达 b
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int start = 0; start < gas.length; start++) {
            int last = 0;
            boolean can = true;
            for (int i = start; i < start + gas.length; i++) {
                int cur = i % gas.length;
                last = last + gas[cur] - cost[cur];
                if (last < 0) {
                    can = false;
                    start = i; // 这一步的优化大大降低复杂度
                    break;
                }
            }
            if (can) {
                return start;
            }
        }

        return -1;
    }
}
