package model.types;

import model.values.StringValue;
import model.values.Value;

public class StringType implements Type {
    @Override
    public String toString() {
        return "string";
    }

    @Override
    public boolean equals(Object another){
        return another instanceof StringType;
    }

    @Override
    public Value getDefault() {
        return new StringValue("Default String");
    }

    @Override
    public Type deepCopy() {
        return new StringType();
    }
    //deepcopy
}
