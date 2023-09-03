package com.singerstone.jojo;

public class 旋转数组最小数字 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 3};
        System.out.println(new 旋转数组最小数字().minArray(array));

    }

    //[3,4,5,1,2]
    //[2,2,2,0,1]
    // [1,3,5]

    // 关键点 相等时候右边界--
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
                right--;
            }
        }
        return numbers[left];


    }


}
