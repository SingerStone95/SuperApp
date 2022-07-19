package com.singerstone.jojo

import java.lang.RuntimeException

class 双栈实现队列 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val queue = Queue()
            queue.offer(1)
            queue.offer(2)
            queue.offer(3)
            print(queue.poll())
            print(queue.poll())
            print(queue.poll())
        }

    }

    class Queue {

        val stack1 = java.util.Stack<Int>()
        val stack2 = java.util.Stack<Int>()

        fun offer(value: Int) {
            stack1.push(value)
        }

        fun poll(): Int {
            if (stack1.isEmpty()) {
                throw RuntimeException("empty")
            }
            var result = 0
            stack2.clear()
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop())
            }
            result = stack2.pop()
            // 再 push 回去
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop())
            }
            return result
        }

    }
}