package com.singerstone.cas;

public class UnSafeLinkedListStack<E> implements Stack<E> {

    Node<E> mHead;
    private volatile int mSize;

    public UnSafeLinkedListStack() {
        mHead = new Node(null);
        mSize = 0;
    }

    @Override
    public int size() {
        return mSize;
    }

    @Override
    public boolean isEmpty() {
        return mSize == 0;

    }

    @Override
    public void push(E e) {
        Node<E> newNode = new Node<>(e);
        SleepUtil.sleep(50); //强制切线程
        newNode.next = mHead.next;
        SleepUtil.sleep(50); //强制切线程
        mHead.next = newNode;
        mSize++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stank is Empty!");
        }
        Node<E> p = mHead.next;
        E result = p.element;
        mHead.next = p.next;
        mSize--;
        return result;

    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stank is Empty!");
        }
        Node<E> p = mHead.next;
        E result = p.element;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = mHead.next;
        while (temp != null) {
            result.append(temp.element);
            temp = temp.next;

        }
        return result.toString();
    }
}