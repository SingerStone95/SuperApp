package com.singerstone.leetcode;

public class TwoSortArray {
    public static void main(String[] args) {

        int[] array1 = { 1, 2, 5, 6 };
        int[] array2 = { 1, 3, 4, 7, 8 };

        System.out.println("answer:" + new TwoSortArray().getMidNum(array1, array2));
    }

    /**
     * 两个数组的中位数
     */
    float getMidNum(int[] array1, int[] array2) {
        float result = 0;
        int addLength = array1.length + array2.length;
        if (addLength == 0) {
            return 0;
        }
        int[] addArray = new int[addLength];
        int k = 0;
        boolean isQ = true;
        if (addLength % 2 == 0) {
            isQ = false;
        } else {
            isQ = true;
        }
        int index1 = 0; // array1的指针
        int index2 = 0; // array2的指针
        int addIndex = 0; // 大数组合并的角标
        while (addIndex < addLength) {
            if (index1 < array1.length && index1 < array2.length) { // 两个列表都没到尾部
                if (array1[index1] < array2[index2]) {
                    addArray[addIndex++] = array1[index1];
                    index1++;
                } else {
                    addArray[addIndex++] = array2[index2];
                    index2++;

                }
            } else if (index1 < array1.length && !(index2 < array2.length)) { // array2到尾部
                addArray[addIndex++] = array1[index1];
                index1++;
            } else if (!(index1 < array1.length) && index2 < array2.length) { // array1 到尾部
                addArray[addIndex++] = array2[index2];
                index2++;
            } else { // 都到了尾部（一般不会走到这里）

            }
        }

        if (isQ) { // 奇数个
            result = addArray[addLength / 2];
        } else { // 偶数个
            result = (addArray[addLength / 2] + addArray[addLength / 2 - 1]) / 2.0f;
        }
        Util.printArray(addArray);
        return result;
    }
}