package com.singerstone.jojo;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/17 10:43
 * @see {@link }
 */
public class 不同的二叉搜索树 {

    public static void main(String[] args) {
        System.out.println(new 不同的二叉搜索树().numTrees(3));
    }

    /**
     * 1. 二叉搜索树满足 左小右大
     * 2. 给一个长度 n 的排序数组，考虑每一个节点作为根节点，把每一个节点作为根节点的组合总和就是答案,组合的个数只跟数组长度有关
     * 3. 根节点把数组分为两部分，每一个部分又可以拆分递归到第二步，直到 n = 1，即长度为 1 时，二叉搜索树的个数，显然为1
     * 4. 因此有递推公式： f（n）= ∑（i=0..n-1）f(i)*f(n-i-1) （i 表示左子树的个数）  f 的含义是 n 个节点不同二叉树的个数
     * 5. 比如 f(2) = f(0)*f(1) + f(1)*f(0)  f(3)= f(0)*f(2) + f(1)*f(1) + f(2)*f(0)
     * 6. f(0)=1 方便做乘法
     *
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
