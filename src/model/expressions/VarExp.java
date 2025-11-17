package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.adt.MyIDictionary;
import model.values.Value;

public class VarExp implements Expression {
    private String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return this.id;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> table) throws ADTException {
        return table.search(this.id);
    }
}
