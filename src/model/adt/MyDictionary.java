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
        try{
            this.map.put(key, value);
        }
        catch(Exception e){
            throw new ADTException("Can't add value");
        }
    }

    @Override
    public T2 remove(T1 key) throws ADTException {
        try{
            return this.map.remove(key);
        }
        catch(Exception e){
            throw new ADTException("Can't remove value");
        }
    }

    @Override
    public void update(T1 key, T2 value) throws ADTException {
        try{
            this.map.replace(key, value);
        }
        catch(Exception e){
            throw new ADTException("Can't update value");
        }
    }

    @Override
    public T2 search(T1 key) throws ADTException {
        try{
            return this.map.get(key);
        }
        catch(Exception e){
            throw new ADTException("Can't search value");
        }
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
    public String toString(){
        return this.map.toString();
    }
}
