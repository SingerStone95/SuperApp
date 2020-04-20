package com.singerstone.cas;

public class Node<T> {

    T element;
    Node next;

    public Node(T element) {
        this(element, null);
    }

    public Node(T element, Node n) {
        this.element = element;
        next = n;
    }

    public T getElement() {
        return element;
    }

}
