package model.expressions;

import exceptions.ExpressionException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.values.Value;

public class ValueExp implements Expression {
    private Value expression;

    public ValueExp(Value expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws ExpressionException {
        return expression;
    }

    @Override
    public String toString(){
        return expression.toString();
    }
}
