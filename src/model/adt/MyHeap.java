package model.adt;

import java.util.HashMap;

public class MyHeap<T> implements MyIHeap<T> {
    int freeLocation;
    HashMap<Integer, T> heap;
    public MyHeap() {
        this.heap = new HashMap<>();
        this.freeLocation = 0;
    }

    @Override
    public int allocate(T value) {
        this.freeLocation++;
        this.heap.put(this.freeLocation, (T) value);
        return this.freeLocation;
    }

    @Override
    public T free(int addr) {
        return this.heap.remove(addr);
    }

    @Override
    public T get(int addr) {
        return this.heap.get(addr);
    }

    @Override
    public void put(int addr, T value) {
        this.heap.put(addr, value);
    }

    @Override
    public boolean exists(int addr) {
        return this.heap.containsKey(addr);
    }

    @Override
    public HashMap getHeap() {
        return heap;
    }

    @Override
    public void setHeap(HashMap heap) {
        this.heap = heap;
    }

    @Override
    public String toString() {
        return this.heap.toString();
    }
}
