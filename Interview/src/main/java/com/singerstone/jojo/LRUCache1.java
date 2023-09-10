package com.singerstone.jojo;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * 面试的时候面试官让写才能写 不然不要写
 *
 * @des: 1. 重写构造函数 2. 重写 get 方法 3. 重写 removeEldestEntry 方法
 * @author: yogachen
 * @date: 2023/9/10 10:00
 * @see {@link }
 */
public class LRUCache1 extends LinkedHashMap<Integer, Integer> {

    int capacity;

    // 简单继承版本
    public LRUCache1(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;

    }

    public int get(int key) {
        return super.getOrDefault(key, -1);

    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
