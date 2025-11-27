package model.adt;

import java.util.HashMap;

public interface MyIHeap <T>{
    public int allocate(T value);
    public T free(int addr);

    public T get(int addr);
    public void put(int addr, T value);
    public boolean exists(int addr);

    public HashMap<Integer, T> getHeap();
    public void setHeap(HashMap<Integer, T> heap);
}
