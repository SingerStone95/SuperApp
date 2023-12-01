package com.singerstone.jojo.projects.cas;

public interface Stack<E> {

    int size();


    boolean isEmpty();

    void push(E e);

    E pop();


    E peek();
}