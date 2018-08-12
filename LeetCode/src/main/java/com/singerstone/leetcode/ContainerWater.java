package com.singerstone.leetcode;

public class ContainerWater {

    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8, 2, 3};
        new ContainerWater().findwood(arr);
    }

    void findwood(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int rLeft = 0;
        int rRight = arr.length - 1;
        int rMax = 0;
        while (left < right) {
            int water = (right - left + 1) * (arr[left] + arr[right]);
            if (rMax < water) {
                rMax = water;
                rLeft = left;
                rRight = right;
            }
            if (arr[left] < arr[right]) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(rLeft + " " + rRight + " " + rMax);

    }
}