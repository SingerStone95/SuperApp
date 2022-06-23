package com.singerstone.jojo

class 全排列 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = arrayOf(1, 2, 3, 4)
            val result = arrayListOf<ArrayList<Int>>()
            val visit = ArrayList<Boolean>(array.size)
            for (i in array){
                visit.add(false)

            }
            permutations(array, result, visit, arrayListOf<Int>())
            print(result)

        }


        private fun permutations(array: Array<Int>, result: ArrayList<ArrayList<Int>>, visit: ArrayList<Boolean>, tmp: ArrayList<Int>) {
            if (tmp.size == array.size) {
                result.add(ArrayList(tmp))
                return
            }
            for (i in array.indices) {
                if (visit[i]) continue
                visit[i] = true
                tmp.add(array[i])
                permutations(array, result, visit, ArrayList(tmp))
                visit[i] = false
                tmp.removeLast()
            }

        }


    }
}