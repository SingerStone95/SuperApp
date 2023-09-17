package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

import java.util.Stack;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/13 21:26
 * @see {@link }
 */
public class 每日温度 {

    public static void main(String[] args) {
        printArray(new 每日温度().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }

    //输入: temperatures = [73,74,75,71,69,72,76,73]
    //输出: [1,1,4,2,1,1,0,0]
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            result[stack.pop()] = 0;
        }
        return result;
    }
}
