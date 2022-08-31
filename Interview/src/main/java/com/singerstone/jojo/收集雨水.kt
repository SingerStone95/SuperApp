package com.singerstone.jojo

import java.util.*
import kotlin.math.min

/**
 * 有两种解法 这里实现最优的第二种
 * 单指针 两次遍历,第一次从左往右找到每个节点的最大值，第二次从右到左找到每个节点的最大值，取两者最小值，减去当前节点的值
 * 双指针 一次遍历
 * 42题
 */
class 收集雨水 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

//            val water = arrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
            val water = arrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)


            println(collectWater(water))
            println(collectWater2(water))
            println(maxWater(water))

        }

        // 接雨水，能接多少接多少
        private fun collectWater(water: Array<Int>): Int {
            var left = 0
            var right = water.size - 1
            var res = 0
            while (left < right) {
                val mini = min(water[left], water[right])
                //小的那一边先开始
                if (mini == water[left]) {
                    left++
                    while (left < right && water[left] < mini) {
                        res += (mini - water[left])
                        left++

                    }

                } else {
                    right--
                    while (left < right && water[right] < mini) {
                        res += (mini - water[right])
                        right--
                    }

                }
            }

            return res

        }

        // 接雨水，能接多少接多少 ,单调栈解法
        // 1, 8, 6, 2, 2, 5, 4, 8, 3, 7
        private fun collectWater2(water: Array<Int>): Int {
            var i = 0
            var result = 0
            var stack = Stack<Int>()
            while (i < water.size) {
                // 这里维持一个递减的栈
                if (stack.isEmpty() || water[stack.peek()] >= water[i]) {
                    stack.push(i++)
                } else {
                    var top = stack.pop()
                    if (stack.isEmpty()) {
                        continue
                    }
                    val left = stack.peek() //这里定义一个临时变量方便理解
                    result += (min(water[i], water[left]) - water[top]) * (i - left - 1)
                }
            }

            return result

        }


        //接雨水，算最大面积,栅栏(最终是找到两条栅栏，中间能承载的雨水最多)
        private fun maxWater(water: Array<Int>): Int {
            var left = 0
            var right = water.size - 1
            var max = 0
            while (left < right) {
                max = kotlin.math.max(max, (right - left) * min(water[left], water[right]))
                if (water[left] < water[right]) {
                    left++
                } else {
                    right--
                }

            }
            return max
        }
    }


}