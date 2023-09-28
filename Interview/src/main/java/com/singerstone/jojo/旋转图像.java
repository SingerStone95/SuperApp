package com.singerstone.jojo;

import static com.singerstone.jojo.合并区间.printArray;

public class 旋转图像 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        new 旋转图像().rotate(matrix);
        printArray(matrix);
    }

    /**
     * 偶数的情况：
     * ① ② 3 4
     * ① ② 3 4
     * 1 2 3 4
     * 1 2 3 4
     * <p>
     * 奇数的情况
     * ① 2 3
     * ① 2 3
     * 1 2 3
     */

    // 口诀：
    // 横坐标=另一个数的纵坐标；
    // 纵坐标=另一个数的横坐标的镜像
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        int m = len / 2;
        int n = (len + 1) / 2;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[len - 1 - j][i];
                matrix[len - 1 - j][i] = matrix[len - 1 - i][len - 1 - j];
                matrix[len - 1 - i][len - 1 - j] = matrix[j][len - 1 - i];
                matrix[j][len - 1 - i] = tmp;
            }
        }
    }
}

//                matrix[i][j] = matrix[n - 1 - j][i];
//                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
//                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
//                matrix[j][n - 1 - i] = tmp;
