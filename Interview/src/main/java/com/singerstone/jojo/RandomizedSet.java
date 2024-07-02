package com.singerstone.jojo;

/**
 *
 * 实现RandomizedSet 类：
 *
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 *
 * 题目要求是实现一个 O(1) 复杂度的 Set ，纯数据结构问题
 * 1.首先 Map 的插入，移除操作 时间复杂度是 O(1) ，但是 getRandom 确无法做到随机返回一个数
 * 2.数组是可以随机按下标返回一个数，但是无法做到插入的 O(1) 复杂度
 * 3.综上可以结合一下两者的优势
 */
public class RandomizedSet {
    public static void main(String[] args) {

    }


    public RandomizedSet() {

    }

    public boolean insert(int val) {
        return false;

    }

    public boolean remove(int val) {
        return false;

    }

    public int getRandom() {
        return 0;

    }
}
