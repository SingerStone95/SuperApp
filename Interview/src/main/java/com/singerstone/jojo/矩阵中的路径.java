package com.singerstone.jojo;

public class 矩阵中的路径 {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'F'}};
        System.out.println(new 矩阵中的路径().exist(board, "ABCCED"));


    }

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

    public boolean exist(char[][] board, String word, int i, int j, int k) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || k >= word.length()) {
            return false;
        }
        if (board[i][j] != word.charAt(k)) {
            return false;
        }
        if (k == word.length() - 1) {
            return true;
        }
        board[i][j] = '\0';
        boolean exist = exist(board, word, i + 1, j, k + 1) ||
                exist(board, word, i - 1, j, k + 1) ||
                exist(board, word, i, j + 1, k + 1) ||
                exist(board, word, i, j - 1, k + 1);
        board[i][j] = word.charAt(k);
        return exist;


    }
}
