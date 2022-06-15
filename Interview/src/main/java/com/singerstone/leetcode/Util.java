package com.singerstone.leetcode;

public class Util {
    public static void swip(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public static int max(int a, int b, int c) {
        int temp;
        if (a > b) {
            temp = a;
        } else {
            temp = b;
        }
        if (temp > c) {
            return temp;
        } else {
            return c;
        }
    }

    public static int min(int a, int b) {
        if (a > b) {
            return b;
        } else {
            return a;
        }
    }

    public static int min(int a, int b, int c) {
        int temp;
        if (a < b) {
            temp = a;
        } else {
            temp = b;
        }
        if (temp < c) {
            return temp;
        } else {
            return c;
        }
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            System.out.print(" ");
        }
    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}