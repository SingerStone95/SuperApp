package com.singerstone.jojo;

public class x的平方根 {

    public static void main(String[] args) {
        System.out.println(mySqrt(1));
        
    }

    //8====2
    public static int mySqrt(int x) {
        int left = 0;
        int right = x;
        int result = -1;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (mid * mid <= x) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;

    }

}
