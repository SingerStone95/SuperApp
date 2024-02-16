package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.List;

public class 八皇后 {

    public static void main(String[] args) {
        System.out.println(new 八皇后().solveNQueens(8).size());

    }

    /**
     * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
     * <p>
     * 注意：本题相对原题做了扩展
     * <p>
     * 示例:
     * <p>
     * 输入：4 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]] 解释: 4 皇后问题存在如下两个不同的解法。
     * [ [".Q..",  // 解法 1 "...Q", "Q...", "..Q."],
     * <p>
     * ["..Q.",  // 解法 2 "Q...", "...Q", ".Q.."] ]
     */

    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        boolean[][] queen = new boolean[n][n];
        dfs(queen, 0, n);
        return result;
    }

    void dfs(boolean[][] queen, int row, int n) {
        if (row >= n) {
            List<String> tmp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder rs = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    rs.append(queen[i][j] ? "Q" : ".");
                }
                tmp.add(rs.toString());
            }
            result.add(tmp);
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!isLeftTop(row, j, queen, n) && !isRightTop(row, j, queen, n) && !isTop(row, j,
                    queen, n)) {
                queen[row][j] = true;
                dfs(queen, row + 1, n);
                queen[row][j] = false;
            }
        }
    }

    private boolean isLeftTop(int row, int j, boolean[][] queen, int n) {
        boolean hasQueue = false;
        while (row >= 0 && j >= 0) {
            if (queen[row][j]) {
                return true;
            }
            row--;
            j--;
        }
        return hasQueue;
    }

    private boolean isRightTop(int row, int j, boolean[][] queen, int n) {
        boolean hasQueue = false;
        while (row >= 0 && j < n) {
            if (queen[row][j]) {
                return true;
            }
            row--;
            j++;
        }
        return hasQueue;
    }

    private boolean isTop(int row, int j, boolean[][] queen, int n) {
        boolean hasQueue = false;
        while (row >= 0) {
            if (queen[row][j]) {
                return true;
            }
            row--;
        }
        return hasQueue;

    }


}
