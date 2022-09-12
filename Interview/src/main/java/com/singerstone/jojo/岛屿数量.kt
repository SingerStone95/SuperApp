package com.singerstone.jojo

import java.util.*
import kotlin.collections.ArrayList

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

            /**
             * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
             * Output: [1,1,2,3]
             */
            val input2 = arrayOf<IntArray>(intArrayOf(0, 0), intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 1))
            print(island2(3, 3, input2))
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

        /**
         * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
         * Output: [1,1,2,3]
         */
        private fun island2(m: Int, n: Int, input: Array<IntArray>): ArrayList<Int> {
            val result = arrayListOf<Int>()
            var num = 0
            val island = Array<Int>(m * n) {
                -1
            }
            for (i in input) {
                //算出在island的坐标
                val index = i[0] * n + i[1]
                if (island[index] == -1) {
                    island[index] = index
                    num++
                }
                // 依次遍历他的邻居 1,2,3,4 左上右下
                for (direct in 1..4) {
                    val neighbor = getNeighbor(direct, i[0], i[1])
                    if (neighbor[0] < 0 || neighbor[0] >= m
                            || neighbor[1] < 0 || neighbor[1] >= n
                            || island[neighbor[0] * n + neighbor[1]] == -1) {
                        continue
                    }
                    // 获取邻居的根节点
                    val q = getRoot(i[0] * n + i[1], island)
                    val p = getRoot(neighbor[0] * n + neighbor[1], island)
                    if (p != q) {
                        // 让邻居节点的根节点指向当前节点，可以理解成一个链表，在链表上的元素都是一个集合，这个是比较巧妙的
                        island[p] = q
                        num--
                    }
                }
                result.add(num)
            }
            return result
        }

        private fun getRoot(index: Int, island: Array<Int>): Int {
            return if (island[index] != index) {
                getRoot(island[index], island)
            } else {
                index
            }
        }


        //1,2,3,4 左上右下
        private fun getNeighbor(i: Int, i1: Int, i2: Int): IntArray {
            return if (i == 1) {
                intArrayOf(i1 - 1, i2)
            } else if (i == 2) {
                intArrayOf(i1, i2 - 1)
            } else if (i == 3) {
                intArrayOf(i1 + 1, i2)
            } else {
                intArrayOf(i1, i2 + 1)
            }
        }
    }
}

