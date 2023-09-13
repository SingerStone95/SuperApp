package com.singerstone.jojo;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/12 23:48
 * @see {@link }
 */
public class 比较版本号 {

    public static void main(String[] args) {
        System.out.println(new 比较版本号().compareVersion("1.1","1.1.1"));

    }

    // 0.1  011.1
    // 常规方法就是分割字符串
    // 优化方法就是双指针 节省空间复杂度
    public int compareVersion(String version1, String version2) {
        int left = 0;
        int right = 0;
        while (left < version1.length() || right < version2.length()) {
            int num1 = 0;
            while (left < version1.length() && version1.charAt(left) != '.') {
                num1 = num1 * 10 + (version1.charAt(left) - '0');
                left++;
            }
            left++;
            int num2 = 0;
            while (right < version2.length() && version2.charAt(right) != '.') {
                num2 = num2 * 10 + (version2.charAt(right) - '0');
                right++;
            }
            right++;
            if (num1 != num2) {
                return num1 - num2 < 0 ? -1 : 1;
            }
        }
        return 0;

    }

}
