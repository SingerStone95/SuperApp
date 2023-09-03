package com.singerstone.jojo;

import java.util.Deque;
import java.util.LinkedList;

public class 队列的最大值 {

    public static void main(String[] args) {
        MaxQueue queue= new MaxQueue();
        queue.pop_front();
        queue.push_back(1);
        queue.push_back(5);
        queue.push_back(7);
        System.out.println(queue.max_value());
        System.out.println(queue.pop_front());
        System.out.println(queue.pop_front());
        System.out.println(queue.pop_front());
        System.out.println(queue.max_value());


    }

    static class MaxQueue {

        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> min = new LinkedList<>();

        public MaxQueue() {

        }

        public int max_value() {
            if (min.isEmpty()) {
                return -1;
            }
            return min.getFirst();

        }

        public void push_back(int value) {
            queue.addLast(value);
            while (!min.isEmpty()) {
                if (value > min.getLast()) {
                    min.removeLast();
                }
            }
            min.addLast(value);

        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            int pop = queue.removeFirst();
            if (!min.isEmpty() && min.getFirst() == pop) {
                min.removeFirst();
            }
            return pop;

        }
    }


}
