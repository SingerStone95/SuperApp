package com.singerstone.jojo

import java.util.*

class 全排列 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = arrayOf(1, 1, 2)
            val result = arrayListOf<ArrayList<Int>>()
            val visit = ArrayList<Boolean>(array.size)
            for (i in array) {
                visit.add(false)

            }
//            permutations(array, result, visit, arrayListOf<Int>())
//            print(result)


            // 不包含重复
            Arrays.sort(array)
            permutations2(array, result, visit, arrayListOf<Int>())
            print(result)
        }


        /**
         * 该排列方式在数组有重复元素的时候会有重复
         *
         */
        private fun permutations(
            array: Array<Int>,
            result: ArrayList<ArrayList<Int>>,
            visit: ArrayList<Boolean>,
            tmp: ArrayList<Int>
        ) {
            if (tmp.size == array.size) {
                result.add(ArrayList(tmp))
                return
            }
            for (i in array.indices) {
                if (visit[i]) continue
                visit[i] = true
                tmp.add(array[i])
                permutations(array, result, visit, ArrayList(tmp))
                visit[i] = false
                tmp.removeLast()
            }
        }

        /**
         *
         * 不包含重复排列
         *   if (i > 0 && array[i] == array[i - 1] && !visit[i - 1]) continue
         *   如果 visit[i - 1] == true ，说明在递归的上一层拿了 array[i-1] 即使 array[i] 和它相等 ，也可以拿，因为他们是值相同不不同的对象而已
         *   如果 visit[i - 1] == false ,说明是在同一层递归的，下一个for循环 ，这个时候
         */
        private fun permutations2(
            array: Array<Int>,
            result: ArrayList<ArrayList<Int>>,
            visit: ArrayList<Boolean>,
            tmp: ArrayList<Int>
        ) {
            if (tmp.size >= array.size) {
                result.add(ArrayList(tmp))
                return
            }
            for (i in array.indices) {
                if (visit[i]) continue
                if (i > 0 && array[i] == array[i - 1] && !visit[i - 1]) continue
                visit[i] = true
                tmp.add(array[i])
                permutations2(array, result, visit, ArrayList(tmp))
                visit[i] = false
                tmp.removeLast()
            }
        }

    }
}