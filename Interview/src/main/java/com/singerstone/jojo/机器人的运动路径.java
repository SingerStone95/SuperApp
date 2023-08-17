package com.singerstone.jojo;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class 机器人的运动路径 {

    public static void main(String[] args) {
        System.out.println(new 机器人的运动路径().movingCount(16, 8, 4));
    }

    // bfs 解法
    public int movingCount(int m, int n, int k) {

        int result = 0;
        boolean[][] visited = new boolean[m][n];
        LinkedList<int[]> queue = new LinkedList<>();
        queue.addLast(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] position = queue.removeFirst();
            if (visited[position[0]][position[1]]
                    || getSum(position[0]) + getSum(position[1]) > k) {
                //这一步很关键 当 getSum(position[0]) + getSum(position[1] 不能满足条件的时候，就不再往前搜索了
                continue;
            }
            result++;
            visited[position[0]][position[1]] = true;
            if (position[0] + 1 < m) {
                queue.offer(new int[]{position[0] + 1, position[1]});
            }
            if (position[1] + 1 < n) {
                queue.offer(new int[]{position[0], position[1] + 1});
            }


        }

        return result;
    }



    int getSum(int n) {
        int result = 0;
        while (n > 0) {
            result += n % 10;
            n /= 10;
        }
        return result;
    }

}
