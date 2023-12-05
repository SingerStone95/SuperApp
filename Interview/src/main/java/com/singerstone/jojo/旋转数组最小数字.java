package com.singerstone.jojo;

public class 旋转数组最小数字 {


    public static void main(String[] args) {
        int[] array = new int[]{3, 3, 3, 3, 6, 1, 2, 3, 3, 3, 3, 3, 3, 3};
        System.out.println(new 旋转数组最小数字().minArray(array));

    }


    //6123333
    // 关键点 相等时候右边界-- （题目要求有可能出现重复）
    // 相等的时候两种处理方式
    // 1. 收缩右边界 --1
    // 2. 退化成线性查找,从当前的left+1 一直到right
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            //中点在单调区间
            if (numbers[mid] < numbers[right]) {
                right = mid;
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else {
                //right--;
                int pre = left;
                for (int i = left + 1; i <= right; i++) {
                    if (numbers[i] < numbers[pre]) {
                        pre = i;
                    }
                }
                return numbers[pre];
            }
        }
        return numbers[left];


    }


}
