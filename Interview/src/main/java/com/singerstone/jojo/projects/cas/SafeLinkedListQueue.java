package com.singerstone.jojo.projects.cas;

import java.util.concurrent.atomic.AtomicBoolean;

public class SafeLinkedListQueue<E> implements Queue<E> {
    private volatile AtomicBoolean lock = new AtomicBoolean(false);
    Node<E> mHead;
    Node<E> mTail;
    int mSize = 0;

    public SafeLinkedListQueue() {
        mHead = new Node(new Object());
        mTail = mHead;

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
    public void offer(E var1) {
        do {
            if (lock.compareAndSet(false, true)) {
                mTail.next = new Node<>(var1);
                mTail = mTail.next;
                mSize++;
                lock.set(false);
                break;
            }

        } while (true);

    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new RuntimeException("Stank is Empty!");
        }
        do {
            if (lock.compareAndSet(false, true)) {
                Node<E> result = mHead.next;
                mHead.next = result.next;
                result.next = null;
                mSize--;
                lock.set(false);
                return result.element;
            }
        } while (true);
    }

    @Override
    public String toString() {
        String result = "";
        Node temp = mHead.next;
        while (temp != null) {
            result += temp.element;
            temp = temp.next;

        }
        return result;
    }
}
