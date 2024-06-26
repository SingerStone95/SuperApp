package com.singerstone.jojo;

import java.lang.reflect.Array;
import java.util.Arrays;

public class H指数 {
    public static void main(String[] args) {
        int[] array = new int[]{3, 0, 6, 1, 5};
        System.out.println(new H指数().hIndex(array));
    }

    /**
     * 示例 1：
     * <p>
     * 输入：citations = [3,0,6,1,5]
     * 输出：3
     * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
     * 由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
     * 示例 2：
     * <p>
     * 输入：citations = [1,3,1]
     * 输出：1
     */

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int result = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] > result) {
                result++;
            } else {
                break;
            }

        }

        return result;

    }
}
