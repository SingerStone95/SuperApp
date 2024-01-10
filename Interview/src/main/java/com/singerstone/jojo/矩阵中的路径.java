package com.singerstone.jojo;

public class 矩阵中的路径 {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'F'}};
        System.out.println(new 矩阵中的路径().exist(board, "ABCCED"));


    }

    /**
     * 任意起点
     */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;

    }

    // k : 字符串第 K 位
    public boolean exist(char[][] board, String word, int i, int j, int k) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || k >= word.length()) {
            return false;
        }
        if (board[i][j] != word.charAt(k)) {
            return false;
        }
        // k 是最后一个位置且通过判断，返回 true
        if (k == word.length() - 1) {
            return true;
        }
        board[i][j] = '\0'; // 小技巧，遍历过的位置赋值为 '\0' ，防止被回溯到，省略了一个额外的 visit 数组
        boolean exist = exist(board, word, i + 1, j, k + 1) ||
                exist(board, word, i - 1, j, k + 1) ||
                exist(board, word, i, j + 1, k + 1) ||
                exist(board, word, i, j - 1, k + 1); // 上下左右 4 个方向
        board[i][j] = word.charAt(k);
        return exist;


    }
}
