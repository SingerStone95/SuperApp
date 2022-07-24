package com.singerstone.jojo

class 第K大的数 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            print(findKNum(arrayOf(5,6,4,1,3,2), 0, 2, 1))
        }

        fun findKNum(input: Array<Int>, left: Int, right: Int, k: Int): Int {
            if (left > right) {
                return -1
            }
            if (left < 0 && right >= input.size) {
                return -1
            }

            var part = getPart(input, left, right)
            // 第part大的数
            if (k == part) {
                return input[k]
            } else if (k < part) {
                //在左边
                return findKNum(input, left, part - 1, k)
            } else {
                //在右边
                return findKNum(input, part + 1, right, k-part)
            }
        }

        /**
         * 左边大右边小
         */
        //232145 1234567
        private fun getPart(input: Array<Int>, left: Int, right: Int): Int {
            var ip = left //指向大于等于基准值的第最后一个
            val vp = input[left]
            for (i in left + 1..right) {
                if (input[i] >= vp) {
                    swap(input, i, ++ip)
                }
            }
            swap(input, left, ip)
            return ip

        }

        private fun swap(input: Array<Int>, i: Int, ip: Int) {
            val tmp = input[i]
            input[i] = input[ip]
            input[ip] = tmp
        }

    }


}