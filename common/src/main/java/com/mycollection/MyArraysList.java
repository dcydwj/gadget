package com.mycollection;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @Description:
 * @Author: congying.deng@ymm56.com
 * @Time: 2018-08-14 17:11
 * @Version: 1.0
 */
public class MyArraysList<T> implements Iterable<T> {


    private int theSize;

    private T[] theItems;


    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }


    public T get(int idx) {
        if (idx >= size() || idx < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];
    }

    public T set(int idx, T newVal) {
        if (idx >= size() || idx < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }


    public void ensureCapacity(int newCapacity) {
        if (theSize > newCapacity) {
            return;
        }

        T[] old = theItems;
        theItems = (T[]) new Object[newCapacity];

        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }


    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}
