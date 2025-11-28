package model.adt;

import exceptions.ADTException;

import java.util.Stack;

public class MyStack <T> implements MyIStack<T> {
    private final Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<>();
    }

    @Override
    public T pop() throws ADTException {
        try{
            return this.stack.pop();
        }
        catch(Exception e){
            throw new ADTException("Error popping from stack");
        }
    }

    @Override
    public void push(T v) {
        this.stack.push(v);
    }

    @Override
    public T top() throws ADTException {
        try{
            return this.stack.peek();
        }
        catch(Exception e){
            throw new ADTException("Error popping from stack--at peek");
        }
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public int size() {
        return this.stack.size();
    }
    @Override
    public String toString() {
        return this.stack.toString();
    }
}
