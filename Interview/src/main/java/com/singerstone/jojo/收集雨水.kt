package com.singerstone.jojo

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


            print(collectWater(water))
            println()
            print(maxWater(water))

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

        //接雨水，算最大面积,栅栏
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