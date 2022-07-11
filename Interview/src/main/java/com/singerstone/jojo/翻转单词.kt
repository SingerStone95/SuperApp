package com.singerstone.jojo

class 翻转单词 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var input = "i am a good dog"

            println(reverse(input))
        }

        fun reverse(input: String): String {
            return input.trim().split(" ").reversed().joinToString(" ")
        }


    }


}