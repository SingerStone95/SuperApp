package com.singerstone.jojo;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/12 23:21
 * @see {@link }
 */
public class N字形变换 {

    public static void main(String[] args) {
        System.out.println(new N字形变换().convert("PAYPALISHIRING", 3));

    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        //0 2 4 6 8
        StringBuilder[] builders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            builders[i] = new StringBuilder();
        }
        int dirct = -1;
        int row = 0;
        for (int i = 0; i < s.length(); i++) { // 这个 i 用不上
            //i % (numRows - 1)==0 也可以作为条件
            if (row == 0 || row == numRows - 1) {
                dirct = -dirct;
            }
            builders[row].append(s.charAt(i));
            row += dirct;
        }
        String result = "";
        for (StringBuilder builder : builders) {
            result += builder.toString();
        }
        return result;
    }

}
