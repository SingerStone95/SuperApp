package com.singerstone.jojo

import kotlin.math.max

class 括号问题 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            print(makeDfs(arrayListOf(), "", 3, 3))

            println(longestValid("(()()"))
        }

        /**
         * 生成括号
         */
        fun makeDfs(result: ArrayList<String>, tmp: String, left: Int, right: Int): ArrayList<String> {
            if (left > right || left < 0 || right < 0) {
                return result
            }
            if (left == 0 && right == 0) {
                result.add(tmp)
                return result
            }
            makeDfs(result, "$tmp(", left - 1, right)
            makeDfs(result, "$tmp)", left, right - 1)
            return result
        }

        /**
         * 最长有效括号
         * (()) 1
         * (()() 2
         * ()))) 3
         * 分别对应下面3种case
         */
        fun longestValid(input: String): Int {
            val stack = java.util.Stack<Int>()
            var result = 0
            var left = 0
            for (i in input.indices) {
                if (input[i] == '(') {
                    stack.push(i)
                } else {
                    if (stack.isEmpty()) { //3
                        left = i + 1
                    } else {
                        stack.pop()
                        if (stack.isEmpty()) {
                            result = max(result, i - left + 1) // 1
                        } else {
                            result = max(result, i - stack.peek()) //2
                        }

                    }
                }
            }
            return result
        }

    }
}