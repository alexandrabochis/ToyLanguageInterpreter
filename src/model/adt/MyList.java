package model.adt;

import exceptions.ADTException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class MyList<T> implements MyIList<T> {
    private LinkedList<T> list;

    public MyList(){
        this.list =  new LinkedList<>();
    }

    @Override
    public void add(T value) {
        this.list.add(value);
    }

    @Override
    public void remove(int pos) throws ADTException {
        try{
            this.list.remove(pos);
        }
        catch(Exception e){
            throw new ADTException("Cannot remove element from list");
        }
    }

    @Override
    public T get(int pos) throws ADTException {
        try{
            return this.list.get(pos);
        }
        catch(Exception e){
            throw new ADTException("Cannot get element from list");
        }
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        //synchronized (this.list){
        return this.list.iterator();
       // }
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        list.forEach(action);
    }

    @Override
    public List<T> getList() {
        return list;
    }

    @Override
    public String toString(){
        return this.list.toString();
    }
}
