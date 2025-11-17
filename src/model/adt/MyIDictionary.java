package model.adt;

import exceptions.ADTException;

import java.util.Collection;

public interface MyIDictionary <T1,T2>{
    void add(T1 key, T2 value)throws ADTException;
    T2 remove(T1 key) throws ADTException;
    void update(T1 key, T2 value) throws ADTException;
    T2 search(T1 key) throws ADTException;
    boolean isEmpty();
    boolean isDefined(T1 key);
    Collection<T2> values();
}
