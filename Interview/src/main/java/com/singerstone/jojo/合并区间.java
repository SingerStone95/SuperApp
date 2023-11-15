package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class 合并区间 {
    public static void main(String[] args) {

        int[][] array = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        printArray(new 合并区间().merge(array));


    }

    public static void printArray(int[][] intervals) {
        for (int[] array : intervals) {
            for (int num : array) {
                System.out.print(num);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        ArrayList<int[]> result = new ArrayList<>();
        int[] temp = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] array = intervals[i];
            if (array[0] <= temp[1]) { //可以连续
                temp[1] = Math.max(array[1], temp[1]);
            } else {
                result.add(temp);
                temp = intervals[i];
            }
            if (i==intervals.length-1){
                result.add(temp);
            }
        }

        return result.toArray(new int[0][]); // 返回二维数组的惯用写法
    }

}
