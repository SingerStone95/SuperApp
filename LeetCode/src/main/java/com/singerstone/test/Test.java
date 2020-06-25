package com.singerstone.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {

       int[] nums=new int[]{1,1,1,2,2,3,3,3,4,5};
       new Test().removeDuplicates(nums);

       System.out.println(nums);

    }

    private static void handle(Bean a) {
        a=new Bean();
        a.a = "123";
        a.b = 100;
    }

    public static class Bean {
        public String a;
        public int b;

        @Override
        public String toString() {
            return a + " " + b;
        }
    }
        public int removeDuplicates ( int[] nums){

            int i = 1, count = 1, length = nums.length;
            while (i < length) {
                if (nums[i] == nums[i - 1]) {

                    count++;

                    if (count > 2) {

                        this.remove(nums, i);
                        i--;

                        length--;
                    }
                } else {
                    count = 1;
                }
                i++;
            }

            return length;
        }

    void remove(int[] nums,int del){
        for(int i=del;i<nums.length-1;i++){
            nums[i]=nums[i+1];
        }
    }
}
