package model.adt;

import exceptions.ADTException;

import java.util.*;

public class MyDictionary<T1, T2> implements MyIDictionary<T1, T2> {
    private Map<T1, T2> map;

    public MyDictionary() {
        this.map = new HashMap<>();
    }


    @Override
    public void add(T1 key, T2 value) throws ADTException {
        this.map.put(key, value);
    }

    @Override
    public T2 remove(T1 key) throws ADTException {
        return this.map.remove(key);

    }

    @Override
    public void update(T1 key, T2 value) throws ADTException {
        this.map.replace(key, value);

    }

    @Override
    public T2 search(T1 key) throws ADTException {
        return this.map.get(key);

    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean isDefined(T1 key) {
        return map.containsKey(key);
    }

    @Override
    public Collection<T2> values() {
        return this.map.values();
    }

    @Override
    public Map<T1, T2> getDictionary() {
        return map;
    }

    @Override
    public MyIDictionary<T1, T2> deepCopy() {
        MyDictionary<T1, T2> clone = new MyDictionary<>();
        for(T1 key : map.keySet())
            clone.map.put(key, map.get(key));
        return clone;
    }

    @Override
    public String toString(){
        return this.map.toString();
    }
}
