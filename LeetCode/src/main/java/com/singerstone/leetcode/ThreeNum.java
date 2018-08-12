package com.singerstone.leetcode;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 三数和为0
 */
public class ThreeNum {

    public static void main(String[] args) {

        int[] array = { -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7 };

        ThreeNum threeNum = new ThreeNum();

        threeNum.getAnswer(array);
    }

    public void getAnswer(int[] array) {
        Arrays.sort(array);
        // List<List<Integer>> resList = new ArrayList<>();
        int last = -1;

        for (int i = 0; i < array.length; i++) {
            // System.out.println(i);
            if (array[i] > 0) {
                break;
            }
            if (i != 0) {
                if (last == array[i]) {
                    last = array[i];
                    continue;
                }
            }
            int left = 0;
            int right = array.length - 1;
            last = array[i];
            int sum = 0 - array[i];
            while (left < right) {
                if (sum < array[left] + array[right]) {
                    right--;
                } else if (sum > array[left] + array[right]) {
                    left++;
                } else {
                    if (left == i) {
                        left++;
                        continue;
                    } else if (right == i) {
                        right--;
                    } else {
                        List<Integer> oneAnswer = new ArrayList<>();
                        oneAnswer.add(array[i]);
                        oneAnswer.add(array[left]);
                        oneAnswer.add(array[right]);

                        Collections.sort(oneAnswer);
                        // resList.add(oneAnswer);
                        System.out.println(oneAnswer);
                        left++;
                        right--;
                    }
                }
            }
        }
        // System.out.println(resList);

    }
}