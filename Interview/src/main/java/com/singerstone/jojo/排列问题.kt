package com.singerstone.jojo

import java.util.*

class 排列问题 {
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
            println(result)

            println(phoneNum("23"))
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


        fun phoneNum(input: String): MutableList<String> {
            val phoneMap = mapOf(
                0 to "",
                1 to "",
                2 to "abc",
                3 to "def",
                4 to "ghi",
                5 to "jkl",
                6 to "mno",
                7 to "pqrs",
                8 to "tuv",
                9 to "wxyz"
            )
            val result = arrayListOf<String>()
            phoneNumDfs(phoneMap, result, "", input, 0)
            return result
        }

        //235
        private fun phoneNumDfs(
            phoneMap: Map<Int, String>,
            result: ArrayList<String>,
            tmp: String,
            input: String,
            level_: Int
        ) {
            var level = level_
            if (level >= input.length) {
                result.add(tmp)
                return
            }
            for (c in phoneMap[input[level] - '0']!!) {
                phoneNumDfs(phoneMap, result, tmp + c, input, level + 1)
            }
        }


    }
}