package com.singerstone.jojo

class 验证括号 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(isValid("[{]}"))
        }

        fun isValid(input: String): Boolean {
            val stack = mutableListOf<Char>()
            val map = mapOf(
                '[' to ']',
                '(' to ')',
                '{' to '}'
            )
            for (c in input) {
                if (c == '[' || c == '{' || c == '(') {
                    stack.add(c)
                } else if (c == ']' || c == '}' || c == ')') {
                    if (stack.size == 0) {
                        return false
                    }
                    if (map[stack.removeLast()] != c) {
                        return false
                    }
                } else {
                    return false
                }
            }

            return true
        }
    }
}