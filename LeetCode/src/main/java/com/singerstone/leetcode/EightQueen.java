package com.singerstone.leetcode;

public class EightQueen {

    static int count = 0;
    static int[][] chess = new int[8][8];

    public static void main(String[] args) {
        settleQueen(0);
        System.out.println("answer count:" + count );

    }

    public static void settleQueen(int n) {
        if (n == 8) {
            count++;
            System.out.println("answer:" + count);
            printBoard(chess);
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    chess[n][j] = 0;
                }

                if (checkBoard(chess, n, i)) {
                    chess[n][i] = 1;
                    settleQueen(n + 1); //分叉
                }
            }
        }
    }

    public static boolean checkBoard(int[][] arr, int x, int y) {
        for (int i = x; i >= 0; i--) {
            if (arr[i][y] == 1) {
                return false;
            }
        }
        for (int i = x, j = y; i >= 0 && j >= 0; i--, j--) {
            if (arr[i][j] == 1) {
                return false;
            }
        }
        for (int i = x, j = y; i >= 0 && j < arr.length; i--, j++) {
            if (arr[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    public static void printBoard(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void initBoard(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = 0;
            }
        }
    }
}