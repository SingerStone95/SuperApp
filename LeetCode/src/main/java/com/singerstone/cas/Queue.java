package com.singerstone.cas;

/**
 * author : yogachen
 * date   : 2020-04-22
 * desc   :
 */
public interface Queue<E> {

    int size();


    boolean isEmpty();

    void offer(E var1);

    E poll();
}
