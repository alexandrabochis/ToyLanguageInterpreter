package model.values;

import model.types.BoolType;
import model.types.Type;

public class BoolValue implements Value{
    private boolean value;

    public BoolValue(boolean v) {
        this.value = v;
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    public String toString(){
        return Boolean.toString(value);
    }

    //TODO equals

    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof BoolValue)) return false;
        BoolValue another = (BoolValue) o;
        return this.value == another.value;

    }
}
