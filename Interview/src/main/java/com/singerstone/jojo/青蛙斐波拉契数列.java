package com.singerstone.jojo;

public class 青蛙斐波拉契数列 {

/*    public int numWays(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;

        } else if (n == 2) {
            return 2;
        }

        return numWays(n - 2) + numWays(n - 1);
    }*/

    public int numWays(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;

        } else if (n == 2) {
            return 2;
        }
        int n0 = 1;
        int n1 = 2;
        for (int i = 3; i <= n; i++) {
            n0 = n1 + n0;
            n1 = n0 + n1;

        }
        return n0;

    }
}
