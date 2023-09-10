package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/10 11:54
 * @see {@link }
 */
public class 顺时针打印矩阵 {

    public static void main(String[] args) {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printArray(new 顺时针打印矩阵().spiralOrder(array));
    }


    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int[] result = new int[matrix.length * matrix[0].length];
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        int index = 0;
        while (true) {
            for (int l = left; l <= right; l++) {
                result[index++] = matrix[top][l];
            }
            if (++top > bottom) { // 不能是 >= , 四个边界都是可以被包含的
                break;
            }
            for (int t = top; t <= bottom; t++) {
                result[index++] = matrix[t][right];
            }
            if (--right < left) {
                break;
            }
            for (int r = right; r >= left; r--) {
                result[index++] = matrix[bottom][r];
            }
            if (--bottom < top) {
                break;
            }

            for (int b = bottom; b >= top; b--) {
                result[index++] = matrix[b][left];
            }
            if (++left > right) {
                break;
            }
        }

        return result;

    }
}
