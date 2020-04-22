package com.singerstone.cas;

/**
 * author : yogachen
 * date   : 2020-04-22
 * desc   :
 */
public interface Queue<E> {

    int size();


    boolean isEmpty();

    boolean offer(E var1);

    E poll();
}
