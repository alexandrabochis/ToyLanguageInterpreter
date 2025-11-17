package model.adt;

import exceptions.ADTException;

import java.util.List;
import java.util.function.Consumer;

public interface MyIList<T> extends Iterable<T> {
    void add(T value);
    void remove(int pos) throws ADTException;
    T get(int pos) throws ADTException;
    int size();
    boolean isEmpty();

    @Override
    void forEach(Consumer<? super T> action);

    List<T> getList();
}
