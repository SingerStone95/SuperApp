package com.singerstone.jojo;

public class 数组排序之后相邻数的最大差值 {
    public static void main(String[] args) {
        int[] nums = new int[]{494767408,862126209,213511142,768240025,631263193,839199222,990848796,214568815,540853864,760724418,980162605,976743981,168965760,680875496,256709197,970953816,948680062,347254784,732201399,786249847,782805044,40906641,674241381,784330934,175502610,731105392,424650824,549764101,986090032,710542549,249208107,448419816,527708664,158499246,223421723,114552457,466978424,821491411,19614107,115389497,902211438,2644108,744489871,897895073,372311214,551142753,933294894,426217662,217504874,983488406,516890023,426853110,971124103};
        System.out.println(new 数组排序之后相邻数的最大差值().maxGap(nums));

    }

    /**
     * 1,3,5,5
     * 区间长度 (5 - 1)/5 =0.8
     * 每个区间范围： 1-1.8 \ 1.8-2.6 \ 2.6-3.4 \ 3.4-4.2 \ 4.2-5.0 （唯独最后一个数是左闭右闭区间）
     */
    public int maxGap(int[] nums) {
        if (nums.length<=1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        if (max == min) {
            return 0;
        }
        int len = nums.length;
        // 只需 3 个数组保存每个桶的状态：是否有值，最大值，最小值
        boolean[] bucketHasValue = new boolean[len + 1];
        int[] bucketMax = new int[len + 1];
        int[] bucketMin = new int[len + 1];
        // 桶比数的个数多一个 保证一定有一个空桶 , 且最后一个桶一定放的是最后一个元素
        // 每个桶的区间都是左闭右开区间，所以不包含最后一个元素，所以最后一个元素人为干预到最后一个桶（我目前的理解）
        for (int i = 0; i < nums.length; i++) {
            int index = calculateBucketIndex(max, min, nums[i], len);
            if (bucketHasValue[index]) {
                bucketMax[index] = Math.max(bucketMax[index], nums[i]);
                bucketMin[index] = Math.min(bucketMin[index], nums[i]);
            } else {
                bucketMax[index] = nums[i];
                bucketMin[index] = nums[i];
                bucketHasValue[index] = true;
            }
        }
        // 计算每两个有值的桶之间的差值
        int i = 0;
        // 过滤掉前面的空桶
        for (; i <= len; i++) {
            if (bucketHasValue[i]) {
                break;
            }
        }
        int preMax = bucketMax[i];
        i++;
        // 从一个非空桶开始计算差值
        int result = 0;
        for (; i <= len; i++) {
            if (bucketHasValue[i]) {
                result = Math.max(result, bucketMin[i] - preMax);
                preMax = bucketMax[i];
            }
        }

        return result;

    }

    private int calculateBucketIndex(long max, long min, long num, long len) {
        // 计算公式: (num - min)/(max - min)*len
        return (int) ((num - min) * len / (max - min));
    }


}
