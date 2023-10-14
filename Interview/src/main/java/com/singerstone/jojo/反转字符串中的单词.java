package com.singerstone.jojo;

public class 反转字符串中的单词 {

    public static void main(String[] args) {
        System.out.println(new 反转字符串中的单词().reverseWords2("a good   example"));

    }

    /**
     * 示例 1：
     * <p>
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     * 示例 2：
     * <p>
     * 输入：s = "  hello world  "
     * 输出："world hello"
     * 解释：反转后的字符串中不能存在前导空格和尾随空格。
     * 示例 3：
     * <p>
     * 输入：s = "a good   example"
     * 输出："example good a"
     * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
     */
    public String reverseWords(String s) {
        String[] items = s.trim().split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = items.length - 1; i >= 0; i--) {
            String tmp = items[i].trim();
            if (tmp.isEmpty()) {
                continue;
            }
            builder.append(tmp);
            if (i != 0) {
                builder.append(" ");
            }

        }
        return builder.toString();

    }

    /**
     * 面试的时候用这个版本，上面那个版本复杂度太高了
     */
    public String reverseWords2(String s) {
        s = s.trim();
        int left = s.length() - 1;
        int right = left;
        StringBuilder builder = new StringBuilder();
        while (left >= 0) {
            while (left >= 0 && s.charAt(left) != ' ') {
                left--;
            }
            builder.append(s, left + 1, right + 1).append(" ");
            while (left >= 0 && s.charAt(left) == ' ') {
                left--;
            }
            right = left;


        }

        return builder.toString().trim();
    }
}
