package com.singerstone.jojo

class 二分查找 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var array = arrayOf(1, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 5, 6)
            println(findRight(array, 3))

            var roteArray = arrayOf(4, 5, 6, 7, 8, 1, 2, 3)
            println(findRoteArray(roteArray, 3))

            println(findRoteArrayMin(roteArray))
        }


        /**
         * 标准二分查找，只能查一本
         * 左闭右闭
         */
        fun find(array: Array<Int>, target: Int): Int {
            var left = 0
            var right = array.size - 1
            while (left <= right) {
                var mid = left + (right - left) / 2
                when {
                    array[mid] == target -> {
                        return mid
                    }
                    mid > target -> {
                        right = mid - 1
                    }
                    else -> {
                        left = mid + 1
                    }
                }
            }
            return -1

        }

        /**
         * 查左边界
         */
        fun findLeft(array: Array<Int>, target: Int): Int {
            var left = 0
            var right = array.size - 1
            while (left < right) {
                val mid = left + (right - left) / 2
                when {
                    array[mid] >= target -> {
                        right = mid
                    }
                    else -> {
                        // 因为区间因为mid,中点天然向左偏移，所以这里左区间搜索需要+1 （不然会死循环）
                        left = mid + 1
                    }
                }
            }
            return if (array[left] == target) return left else -1
        }

        /**
         * 查右边界
         */
        fun findRight(array: Array<Int>, target: Int): Int {
            var left = 0
            var right = array.size - 1
            while (left < right) {
                val mid = left + (right - left) / 2 + 1
                when {
                    array[mid] <= target -> {
                        left = mid
                    }
                    else -> {
                        // 因为区间因为mid+1 ,中点天然向右偏移，所以这里右区间搜索需要-1 （不然会死循环）
                        right = mid - 1
                    }
                }
            }
            return if (array[right] == target) return right else -1
        }

        /**
         * 旋转数组查询特定值(采用的是查找单个数的写法，即<= +1 -1 )
         *
         * 789123456
         */
        fun findRoteArray(array: Array<Int>, target: Int): Int {
            var left = 0
            var right = array.size - 1
            while (left <= right) {
                val mid = left + (right - left) / 2
                if (array[mid] == target) {
                    return mid
                } else if (array[mid] <= array[right]) { // mid的右边是有序区间
                    if (target > array[mid] && target <= array[right]) {
                        left = mid + 1
                    } else {
                        right = mid - 1
                    }
                } else { // mid 的左边是有序de
                    if (target >= array[left] && target < array[mid]) {
                        right = mid - 1
                    } else {
                        left = mid + 1
                    }

                }
            }

            return -1

        }

        /**
         * 旋转数组查询最小值(Log n 解法)
         * 561234
         * 思路，因为不是查找单个明确的值不能用标准二分查找的写法
         * 已知 最小值一定是在小连续区间的第一个
         * 问题就转化为查找小连续区间的第一个值，当中点落在小连续区间时候，就压缩右边界，
         * 反之压缩左边界，直到退出循环 l==mid==左边界最小值
         *
         */
        fun findRoteArrayMin(array: Array<Int>): Int {
            var l = 0
            var r = array.size - 1
            if (array[l] < array[r]) {
                return l
            }
            while (l < r) {
                val mid = l + (r - l) / 2
                if (array[mid] <= array[r]) {
                    r = mid
                } else {
                    l = mid + 1
                }
            }
            return l
        }

    }

}