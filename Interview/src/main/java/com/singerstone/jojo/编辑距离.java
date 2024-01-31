package com.singerstone.jojo;

public class 编辑距离 {
    public static void main(String[] args) {
        System.out.println(new 编辑距离().minDistance("horse", "ros"));
        System.out.println(new 编辑距离().minDistance("intention", "execution"));
        System.out.println(new 编辑距离().minDistance("dinitrophenylhydrazine", "acetylphenylhydrazine"));

    }

    /**
     * 示例 1：
     * <p>
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     * 示例 2：
     * <p>
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     */
    public int minDistance(String word1, String word2) {
        minDistance(word1, word2, 0, 0, 0);
        return min;
    }

    int min = Integer.MAX_VALUE;

    public void minDistance(String word1, String word2, int start1, int start2, int handle) {
        String tmp1 = word1.substring(start1);
        String tmp2 = word2.substring(start2);
        if (tmp1.length() == 0 || tmp2.length() == 0) {
            handle += Math.abs(tmp1.length() - tmp2.length());
            min = Math.min(handle, min);
            return;
        }
        if (tmp1.charAt(0) == tmp2.charAt(0)) {
            minDistance(word1, word2, start1 + 1, start2 + 1, handle);
        }
        minDistance(word1, word2, start1 + 1, start2 + 1, handle + 1); // 替换
        minDistance(word1, word2, start1 + 1, start2, handle + 1); // 删除
        minDistance(word1, word2, start1, start2 + 1, handle + 1); // 增加
    }

}
