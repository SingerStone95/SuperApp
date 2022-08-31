package com.singerstone.jojo

class 二分查找 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var array = arrayOf(1, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 5, 6)
            println(findRight(array, 3))

            var roteArray = arrayOf(4, 5, 6, 7, 8, 1, 2, 3)
            println(findRoteArray(roteArray, 3))
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
         * 旋转数组查询
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
         */
       fun findRoteArrayMin(array: Array<Int>): Int {
           return 0
        }

    }

}