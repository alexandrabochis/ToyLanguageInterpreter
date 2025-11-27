package model.expressions;

import exceptions.ExpressionException;
import exceptions.GeneralException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.values.RefValue;
import model.values.Value;

public class ReadHeapExp implements Expression {
    Expression exp;

    public ReadHeapExp(Expression exp) {
        this.exp = exp;
    }



    @Override
    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws GeneralException {
        Value value = exp.eval(table, heap);
        if(!(value instanceof RefValue))
            throw new ExpressionException("expression is not of Ref type");
        RefValue ref = (RefValue)value;
        int addr = ref.getAddr();
        if(!(heap.exists(addr)))
            throw new ExpressionException("not allocated");

        return heap.get(addr);
    }

    public String toString(){
        return "rH("+exp.toString()+")";
    }
}
