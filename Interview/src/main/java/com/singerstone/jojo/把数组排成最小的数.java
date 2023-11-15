package com.singerstone.jojo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class 把数组排成最小的数 {

    public static void main(String[] args) {
        int[] array = new int[]{22, 12, 8, 4, 2};
        System.out.println(new 把数组排成最小的数().minNumber(array));

    }

    public String minNumber(int[] nums) {
        ArrayList<Integer> array = new ArrayList<>();
        for (int num : nums) {
            array.add(num);
        }
        Collections.sort(array, (o1, o2) -> {

           return  (String.valueOf(o1) + o2).compareTo(String.valueOf(o2)
                    + o1);

        });

        StringBuilder builder = new StringBuilder();
        for (int num : array) {
            builder.append(num);
        }

        return builder.toString();

    }


}
