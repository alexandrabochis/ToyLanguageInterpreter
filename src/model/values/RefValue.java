package model.values;

import model.types.RefType;
import model.types.Type;

import java.sql.Ref;

public class RefValue implements Value{
    int address;
    Type locationType;
    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddr() {
        return address;
    }

    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public Value deepCopy() {
        return  new  RefValue(address, locationType);
    }

    public String toString() {
        return "(" + address + "," + locationType + ")";

    }
}
