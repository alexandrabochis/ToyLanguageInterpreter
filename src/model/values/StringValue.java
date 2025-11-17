package model.values;

import model.types.StringType;
import model.types.Type;

import java.util.Objects;

public class StringValue implements Value{
    private String value;

    public String getValue() {
        return this.value;
    }

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof StringValue)) return false;
        StringValue other = (StringValue)obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
