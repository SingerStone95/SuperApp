package com.singerstone.jojo;

public class 把数字翻译成字符串 {
    public static void main(String[] args) {
        System.out.println(new 把数字翻译成字符串().translateNum2(12258));

    }


    // dp[i] 表示以 i 结尾的子串翻译成字符串的个数
    // dp[i] 取决于它前两个字符是否能组成合法的字符
    // dp[i] = num[i-2]num[i-1] ? dp[i-1]+dp[i-2] : dp[i-1]
    // 你可能会疑惑 dp[i-1] == dp[i], 这个就跟爬楼梯一样 最后一个数的组成，可能是1个字符 也有可能是2个字符,这里由两个字符组成是有前提条件的
    // 边界条件: dp[0] ==1 dp[1] ==1  dp[2] 反推出 dp[0] = 1
    public int translateNum2(int num) {
        String s = String.valueOf(num);
        if (s.length() == 1) {
            return 1;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;

        //为了方便写代码，这里的i表示第i个数
        for (int i = 2; i <= s.length(); i++) {
            int pre = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if (pre > 9 && pre < 26) {
                dp[i] = dp[i - 1] + dp[i - 2];

            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[s.length()];
    }


    //输入: 12258
    //输出: 5
    //解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
    public int translateNum(int num) {
        String nums = String.valueOf(num);
        int start = 0;
        dfs(nums, start);
        return result;
    }

    int result = 0;

    void dfs(String s, int left) {
        if (left > s.length()) {
            return;
        } else if (left == s.length()) {
            result++;
        }
        dfs(s, left + 1);
        if (left + 2 <= s.length()) {
            String sub2 = s.substring(left, left + 2);
            if (sub2.startsWith("0")) {
                // 也可以替换下面一句 sub2.compareTo("9") > 0 && sub2.compareTo("26") 达到同样的效果
                return;
            }
            if (sub2.compareTo("0") >= 0 && sub2.compareTo("26") < 0) {
                left += 2;
                dfs(s, left);
            }
        }
    }

}
