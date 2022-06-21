package com.singerstone.test

class 组合之和 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val array = arrayOf(1, 2, 3, 4)
            val target = 8
            val result = mutableListOf<MutableList<Int>>()
            findArray3(array, result, mutableListOf(), 0, target, 3)

            print(result)
        }

        /**
         * 可以包含重复元素
         */
        private fun findArray(array: Array<Int>, result: MutableList<MutableList<Int>>, temp: MutableList<Int>, start: Int, target: Int) {
            if (target < 0) {
                return
            }
            if (target == 0) {
                result.add(ArrayList(temp))
                return
            }

            for (i in start until array.size) {
                temp.add(array[i])
                findArray(array, result, ArrayList(temp), i, target - array[i])
                temp.removeLast()
            }

        }

        //不可以包含重复元素
        private fun findArray2(array: Array<Int>, result: MutableList<MutableList<Int>>, temp: MutableList<Int>, start: Int, target: Int) {
            if (target < 0) {
                return
            }
            if (target == 0) {
                result.add(ArrayList(temp))
                return
            }

            for (i in start until array.size) {
                if (i > start && (array[i] == array[i - 1])) { //数组相同元素去重
                    continue
                }
                temp.add(array[i])
                // i+1 不能使用刚刚用过的元素
                findArray2(array, result, ArrayList(temp), i + 1, target - array[i])
                temp.removeLast()
            }
        }


        // 限制只能用 count 个数
        private fun findArray3(array: Array<Int>, result: MutableList<MutableList<Int>>, temp: MutableList<Int>, start: Int, target: Int, count: Int) {
            if (target < 0 || count < 0) {
                return
            }
            if (target == 0 && count == 0) {
                result.add(ArrayList(temp))
            }
            for (i in start until array.size) {
                temp.add(array[i])
                findArray3(array, result, ArrayList(temp), i, target - array[i], count - 1)
                temp.removeLast()
            }
        }

    }
}