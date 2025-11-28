package model.expressions;

import exceptions.ExpressionException;
import exceptions.GeneralException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

public class RelExp implements Expression {
    private Expression exp1;
    private Expression exp2;
    private String op;

    public RelExp(Expression exp1, Expression exp2, String op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }
    @Override
    public String toString() {
        return exp1.toString()+ this.op + exp2.toString();

    }

    @Override
    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws GeneralException {
        Value v1, v2;
        v1 = exp1.eval(table, heap);
        if(v1.getType().equals(new IntType())){
            v2 = exp2.eval(table, heap);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1 = i1.getValue();
                int n2 = i2.getValue();

                switch(op){
                    case "<" -> {return new BoolValue(n1<n2);}
                    case "<=" -> {return new BoolValue(n1<=n2);}
                    case ">" -> {return new BoolValue(n1>n2);}
                    case ">=" -> {return new BoolValue(n1>n2);}
                    case "==" -> {return new BoolValue(n1==n2);}
                    case "!=" -> {return new BoolValue(n1!=n2);}

                    default -> throw new ExpressionException("Invalid operation");

                }
            }
            else throw new ExpressionException("second operand is not int");
        }else throw new ExpressionException("first operand is not int");
    }

    @Override
    public Expression deepCopy() {
        return new RelExp(this.exp1, this.exp2, this.op);
    }

}
