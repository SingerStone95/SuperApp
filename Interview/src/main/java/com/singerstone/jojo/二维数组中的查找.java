package com.singerstone.jojo;

public class 二维数组中的查找 {

    public static void main(String[] args) {

        int arr[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(findNumberIn2DArray(arr, 10));
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length==0){
            return false;
        }
        int m = 0;
        int n = matrix[0].length - 1;
        while (m < matrix.length && n >= 0) {
            if (target == matrix[m][n]) {
                return true;
            } else if (target > matrix[m][n]) {
                m++;
            } else {
                n--;

            }
        }

        return false;

    }
}
