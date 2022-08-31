package com.singerstone.jojo

import java.util.*
import kotlin.math.max
import kotlin.math.min

class 直方图最大矩形 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(getMaxRec(arrayOf(2, 1, 5, 3, 6, 2, 3)))
            println(getMaxRec2(arrayOf(2, 1, 5, 3, 6, 2, 3)))
        }

        /**
         * 普通解法，找局部峰值
         * 个人感觉这个解法最简洁 时间复杂会在倒序的时候退化成 o(n2)
         */
        private fun getMaxRec(array: Array<Int>): Int {
            var result = 0
            var i = 0
            while (i < array.size) {
                if (i + 1 >= array.size || array[i] > array[i + 1]) {
                    var minHeight = array[i]
                    for (j in i downTo 0) {
                        // 回溯的时候需要记录回溯过程的最小值作为矩形的高
                        minHeight = min(minHeight, array[j])
                        result = max(result, minHeight * (i - j + 1))
                    }
                }
                i++
            }
            return result
        }


        /**
         * 单调栈解法
         * 2, 1, 5, 3, 6, 2, 3
         * 0, 1, 2, 3
         */
        fun getMaxRec2(input: Array<Int>): Int {
            var stack = Stack<Int>()
            var array = mutableListOf<Int>()
            array.addAll(input)
            array.add(0)
            var result = 0
            var i = 0
            while (i < array.size) {
                if (stack.isEmpty() || array[stack.peek()] <= array[i]) {
                    stack.push(i++) // 只有在这个分支，i 才会自增
                } else {
                    val t = stack.pop()
                    /**
                     * 其他地方都比较好理解 ，这个地方需要说明一下：
                     * i - stack.peek() - 1
                     * 左边界为什么是 stack.peek() ？ 以用例为例，出栈到第一个 3 的时候 栈里面还剩了 1 ， 这个时候矩形的宽度应该是 5，3 组成的宽度，但是当前栈已经没有 5 了 ，所以只能是栈里面还存在的 1 的坐标+1
                     * 两个点的坐标差本来是 r-l+1
                     * 代入可得 (i-1)-(stack.peek()+1)+1 既是 i - stack.peek() - 1
                     */
                    result = max(result, array[t] * (if (stack.isEmpty()) i else (i - stack.peek() - 1)))

                }
            }


            return result
        }
    }
}