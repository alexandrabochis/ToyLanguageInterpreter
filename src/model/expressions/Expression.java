package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.GeneralException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.types.Type;
import model.values.Value;

public interface Expression {
    Value eval(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws GeneralException;
    public Expression deepCopy();

    Type typecheck(MyIDictionary<String,Type>typeEnv) throws GeneralException;


}
