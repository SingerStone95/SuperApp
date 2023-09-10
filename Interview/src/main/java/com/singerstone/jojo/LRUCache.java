package com.singerstone.jojo;

import java.util.HashMap;

/**
 * @des: HashMap + 双向链表的实现方式
 * @author: yogachen
 * @date: 2023/9/10 10:09
 *
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 1);
        System.out.println(cache);
        cache.put(2, 2);
        System.out.println(cache);
        cache.put(3, 3);
        System.out.println(cache);
        System.out.println(cache.get(4));
        System.out.println(cache.get(2));
        System.out.println(cache);
        cache.put(1, 8);
        System.out.println(cache);
        cache.put(4, 4);
        System.out.println(cache);
        cache.put(1, 5); System.out.println(cache);


    }

    private HashMap<Integer, Entry> map;
    private int capacity;
    private int size = 0;
    private Entry head;
    private Entry tail;

    // 定义存储数据结构 Entry
    private static class Entry {

        public int value;
        public int key;
        public Entry pre;
        public Entry next;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Entry(-1, -1);
        tail = new Entry(-1, -1);
        head.next = tail;
        tail.pre = head;
        size = 0;

    }

    public int get(int key) {
        Entry old = map.get(key);
        if (old == null) {
            return -1;
        } else {
            moveToHead(old);
            return old.value;
        }
    }

    public void put(int key, int value) {
        Entry old = map.get(key);
        if (old == null) {
            Entry entry = new Entry(key, value);
            map.put(key, entry);
            addToHead(entry);
            size++;
        } else {
            old.value = value;
            moveToHead(old);
        }
        tryResize();
    }

    private void moveToHead(Entry entry) {
        // 先remove
        entry.pre.next = entry.next;
        entry.next.pre = entry.pre;
        // 再add
        addToHead(entry);


    }

    private void addToHead(Entry entry) {
        entry.next = head.next;
        head.next = entry;
        entry.next.pre = entry;
        entry.pre = head;
    }

    private void tryResize() {
        while (size > capacity) {
            Entry remove = tail.pre;
            map.remove(remove.key);
            tail.pre = remove.pre;
            remove.pre.next = tail;
            size--;
        }

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(size);
        builder.append("\t");
        Entry entry = head.next;
        while (entry != tail) {
            builder.append(entry.key);
            builder.append("-");
            builder.append(entry.value);
            builder.append("\t");
            entry = entry.next;
        }
        return builder.toString();
    }
}
