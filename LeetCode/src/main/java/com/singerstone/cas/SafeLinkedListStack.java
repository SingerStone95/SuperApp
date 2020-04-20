package com.singerstone.cas;

public class SafeLinkedListStack<E> implements Stack<E> {

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
        newNode.next = mHead.next;
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
}