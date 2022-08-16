package com.singerstone.jojo

import java.util.*

class 滑动窗口 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var input = arrayOf<Int>()
            printArray(maxSlideWindow(input, 2))

        }

        fun maxSlideWindow(input: Array<Int>, k: Int): Array<Int> {
            val result = arrayListOf<Int>()
            val list = LinkedList<Int>()
            for (i in input.indices) {
                if (!list.isEmpty() && list.first + k == i) { //超过长度了
                    list.removeFirst()
                }
                //input[i] 是即将要加进来的值
                while (!list.isEmpty() && input[list.last] < input[i]) {
                    list.removeLast()
                }
                list.add(i)
                if (i >= k - 1) {
                    result.add(list.first)
                }
            }
            return result.toArray(arrayOf())

        }
    }

}

fun printArray(input: Array<Int>) {
    input.forEach {
        print(it)
    }
    println()
}