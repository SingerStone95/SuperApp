package com.singerstone.jojo

import java.util.*

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
            val input = arrayOf(intArrayOf(1, 1, 1, 1, 0), intArrayOf(1, 1, 0, 1, 0), intArrayOf(1, 1, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0))
//            println(numOfIslands(input))
            println(numOfIslandsB(input))
        }

        /**
         * bfs
         */
        private fun numOfIslandsB(input: Array<IntArray>): Int {
            val visited = arrayOf(intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0))
            var result = 0
            for (i in input.indices) {
                for (j in input[i].indices) {
                    if (visited[i][j] == 1 || input[i][j] == 0) {
                        continue
                    }
                    result++
                    bfs(input, visited, i, j)
                }
            }
            return result
        }

        private fun bfs(input: Array<IntArray>, visited: Array<IntArray>, i: Int, j: Int) {
            val queue = LinkedList<Pair<Int, Int>>()
            queue.add(Pair(i, j))
            while (queue.isNotEmpty()) {
                val pair = queue.removeFirst()
                if (input[pair.first][pair.second] == 1) {
                    visited[pair.first][pair.second] = 1
                    if (isValid(pair.first - 1, pair.second, input, visited)) {
                        queue.addLast(Pair(pair.first - 1, pair.second))
                    }
                    if (isValid(pair.first + 1, pair.second, input, visited)) {
                        queue.addLast(Pair(pair.first + 1, pair.second))
                    }
                    if (isValid(pair.first, pair.second + 1, input, visited)) {
                        queue.addLast(Pair(pair.first, pair.second + 1))
                    }
                    if (isValid(pair.first, pair.second - 1, input, visited)) {
                        queue.addLast(Pair(pair.first, pair.second - 1))
                    }
                }

            }

        }

        private fun isValid(i: Int, j: Int, input: Array<IntArray>, visited: Array<IntArray>): Boolean {
            if (i < 0 || i >= input[0].size || j >= input.size || j < 0) {
                return false
            }
            if (input[i][j] == 0) {
                return false
            }
            if (visited[i][j] == 1) {
                return false
            }
            return true
        }

        /**
         * dfs
         */
        private fun numOfIslands(input: Array<IntArray>): Int {
            val visited = arrayOf(intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 0, 0))
            var result = 0
            for (i in input.indices) {
                for (j in input[i].indices) {
                    if (input[i][j] == 0 || visited[i][j] == 1) {
                        continue
                    }
                    result++
                    dfs(input, visited, i, j)
                }
            }
            return result

        }

        /**
         * dfs
         */
        private fun dfs(input: Array<IntArray>, visited: Array<IntArray>, i: Int, j: Int) {
            if (i < 0 || i >= input.size || j < 0 || j >= input.size
                    || visited[i][j] == 1 || input[i][j] == 0) {
                return
            }
            visited[i][j] = 1
            dfs(input, visited, i - 1, j)
            dfs(input, visited, i, j - 1)
            dfs(input, visited, i, j + 1)
            dfs(input, visited, i + 1, j)


        }
    }
}

