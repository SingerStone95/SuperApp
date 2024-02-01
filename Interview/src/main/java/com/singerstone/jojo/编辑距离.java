package com.singerstone.jojo;

public class 编辑距离 {
    public static void main(String[] args) {
        System.out.println(new 编辑距离().minDistance2("horse", "ros"));
        System.out.println(new 编辑距离().minDistance2("intention", "execution"));
        System.out.println(new 编辑距离().minDistance("dinitrophen", "acetylphenylhy"));

    }

    /**
     * dp 解法
     * dp[i][j] 含义：word1[0-i] 到 word2[0-j] 的最短距离
     */
    public int minDistance2(String word1, String word2) {
        int dp[][] = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(/**删除 abb ab*/dp[i - 1][j], /**新增 ab abb*/dp[i][j - 1]) , /**修改 abb abc*/dp[i - 1][j - 1]) + 1;
                }
            }

        }
        return dp[word1.length()][word2.length()];
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

    /**
     * 递归加备忘录写法 ，不加备忘录必超时，加了其实也超时
     * record 记录起始点分别是 i,j 的子串的最小距离
     */
    public int minDistance(String word1, String word2) {
        record = new int[word1.length() + 1][word2.length() + 1];
        minDistance(word1, word2, 0, 0, 0);
        return min;
    }

    int[][] record;
    int min = Integer.MAX_VALUE;

    public void minDistance(String word1, String word2, int start1, int start2, int handle) {
        String tmp1 = word1.substring(start1);
        String tmp2 = word2.substring(start2);
        if (tmp1.length() == 0 || tmp2.length() == 0) { // 有一个串走到末尾都开始结算
            handle += Math.abs(tmp1.length() - tmp2.length());
            min = Math.min(handle, min);
            record[start1][start2] = min;
            return;
        }
        if (record[start1][start2] != 0) {
            return;
        }
        if (tmp1.charAt(0) == tmp2.charAt(0)) {
            minDistance(word1, word2, start1 + 1, start2 + 1, handle);
        } else {
            minDistance(word1, word2, start1 + 1, start2 + 1, handle + 1); // 替换
            minDistance(word1, word2, start1 + 1, start2, handle + 1); // 删除
            minDistance(word1, word2, start1, start2 + 1, handle + 1); // 增加
        }
    }

}
