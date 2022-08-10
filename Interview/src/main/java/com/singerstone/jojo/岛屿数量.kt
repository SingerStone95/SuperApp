package com.singerstone.jojo

class 岛屿数量 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            /**
             * 1, 1, 1, 1, 0
             * 1, 1, 0, 0, 0
             * 1, 1, 0, 0, 0
             * 0, 0, 0, 0, 1
             */
            val input = arrayOf(intArrayOf(1, 1, 1, 1, 0), intArrayOf(1, 1, 0, 1, 0), intArrayOf(1, 1, 0, 0, 0), intArrayOf(0, 0, 0, 0, 1))
            println(numOfIslands(input))
        }

        private fun numOfIslands(input: Array<IntArray>): Int {
            val visited = arrayOf(intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0))
            var result = 0
            for (i in input.indices) {
                for (j in input[i].indices) {
                    if (input[i][j] == 0 || visited[i][j] == 1) {
                        continue
                    }
                    result++
                    visitIsland(input, visited, i, j)
                }
            }
            return result

        }

        private fun visitIsland(input: Array<IntArray>, visited: Array<IntArray>, i: Int, j: Int) {
            if (i < 0 || i >= input.size || j < 0 || j >= input.size
                    || visited[i][j] == 1 || input[i][j] == 0) {
                return
            }
            visited[i][j] = 1
            visitIsland(input, visited, i - 1, j)
            visitIsland(input, visited, i, j - 1)
            visitIsland(input, visited, i, j + 1)
            visitIsland(input, visited, i + 1, j)


        }
    }
}

