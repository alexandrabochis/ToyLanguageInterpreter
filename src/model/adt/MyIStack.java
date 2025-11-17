package model.adt;

import exceptions.ADTException;

public interface MyIStack<T> {
    T pop() throws ADTException;
    void  push(T v);
    T top() throws ADTException;
    boolean isEmpty();
    int size();
}
