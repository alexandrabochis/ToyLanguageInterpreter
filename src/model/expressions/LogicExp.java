package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.GeneralException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.types.BoolType;
import model.types.IntType;
import model.types.Type;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

public class LogicExp implements  Expression {

    Expression e1;
    Expression e2;

    int op; //1 &    2 |

    public  LogicExp(Expression first, Expression second, int op) {
        this.e1 = first;
        this.e2 = second;

    }

    @Override
    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws GeneralException {
        Value v1, v2;
        v1 = e1.eval(table, heap);
        if(v1.getType().equals(new BoolType())) {
            v2 = e2.eval(table, heap);
            if(v2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue)v1;
                BoolValue i2 = (BoolValue)v2;
                boolean b1, b2;
                b1 = i1.getValue();
                b2 = i2.getValue();

                switch(op) {
                    case 1 -> {return new BoolValue(b1 & b2);}
                    case 2 -> {return new BoolValue(b1 | b2);}
                    default -> {throw new ExpressionException("invalid op");}
                }

            }
            else throw new ExpressionException("2nd not boolean");
        }
        else throw new ExpressionException("1st not boolean");
    }

    @Override
    public Expression deepCopy() {
        return new LogicExp(this.e1, this.e2, this.op);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws GeneralException {
        Type t1 = e1.typecheck(typeEnv);
        Type t2 = e2.typecheck(typeEnv);
        if(t1.equals(new BoolType())){
            if (t2.equals(new BoolType()))
                return new BoolType();
            else throw new ExpressionException("logic exp: second operand is not boolean");
        }
        else throw new ExpressionException("logic exp: first operand is not boolean");
    }

    @Override
    public String toString(){
        return switch (op){
            case 1 -> e1.toString()+ "& "+e2.toString();
            case 2 -> e1.toString()+ "| "+e2.toString();
            default -> "";
        };
    }
}
