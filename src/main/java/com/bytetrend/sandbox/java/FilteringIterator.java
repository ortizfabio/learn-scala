package com.bytetrend.sandbox.java;

import java.util.Iterator;

public class FilteringIterator<E> implements Iterator<E> {
    private final Iterator<E> iterator;
    private final IObjectTest test;
    private E next;

    public FilteringIterator(Iterator<E> iter, IObjectTest test) {
        this.iterator = iter;
        this.test = test;
    }

    private void findNext() {
        while ((next == null && iterator.hasNext())) {
            next = iterator.next();
            if (!test.test(next)) next = null;
        }
    }

    @Override
    public boolean hasNext() {
        findNext();
        return next != null;
    }

    @Override
    public E next() {
        findNext();
        E currentNext = next;
        next = null;
        return currentNext;
    }

    @Override
    public void remove() {
        next = null;
    }
}
