package model.values;

import model.types.IntType;
import model.types.Type;

public class IntValue implements Value{
    private int value;
    public IntValue(int v) {
        this.value = v;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }


    public boolean  equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof IntValue)) return false;
        IntValue another = (IntValue) o;
        return this.value == another.value;

    }
}
