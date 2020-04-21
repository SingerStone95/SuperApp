package com.singerstone.cas;

import java.util.concurrent.atomic.AtomicBoolean;

public class SafeLinkedListStack<E> implements Stack<E> {
    AtomicBoolean lock = new AtomicBoolean(false);

    Node<E> mHead;
    int mSize;

    public SafeLinkedListStack() {
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
        do {
            if (lock.compareAndSet(false, true)) {
                newNode.next = mHead.next;
                mHead.next = newNode;
                mSize++;
                lock.set(false);
                break;
            }
        } while (true);
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stank is Empty!");
        }
        do {
            if (lock.compareAndSet(false, true)) {
                Node<E> p = mHead.next;
                E result = p.element;
                mHead.next = p.next;
                mSize--;
                lock.set(false);
                return result;
            }
        } while (true);

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
}