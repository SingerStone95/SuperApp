package com.singerstone.jojo

class 括号问题 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            print(makeDfs(arrayListOf(), "", 3, 3))
        }

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
    }
}