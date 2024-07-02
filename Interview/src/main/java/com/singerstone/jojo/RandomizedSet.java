package com.singerstone.jojo;

import java.util.*;

/**
 * 实现RandomizedSet 类：
 * <p>
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 * <p>
 * 题目要求是实现一个 O(1) 复杂度的 Set ，纯数据结构问题
 * 1.首先 Map 的插入，移除操作 时间复杂度是 O(1) ，但是 getRandom 确无法做到随机返回一个数
 * 2.数组是可以随机按下标返回一个数，但是无法做到插入的 O(1) 复杂度
 * 3.综上可以结合一下两者的优势，主要的细节在于 remove 操作，怎么对数组执行一个O（1）的 remove 呢？
 */
public class RandomizedSet {
    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
        set.insert(0);
        set.remove(0);
        set.insert(0);
        System.out.println(set.list.size());
    }


    private List<Integer> list = new ArrayList<>();
    private Map<Integer, Integer> map = new HashMap<>();

    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if (map.get(val) != null) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;

    }

    public boolean remove(int val) {
        Integer old_index = map.remove(val);
        if (old_index == null) {
            return false;
        }
        int last_index = list.size() - 1;
        int last_value = list.get(last_index);
        if (last_value != val) { // 如果移除的就是最后一个
            list.set(old_index, last_value);
            map.put(last_value, old_index);
        }
        list.remove(last_index);

        return true;

    }

    public int getRandom() {
        int index = new Random().nextInt(list.size());
        return list.get(index);

    }
}
