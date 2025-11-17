package model.expressions;


import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.GeneralException;
import model.adt.MyIDictionary;
import model.types.BoolType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

public class ArithExp implements Expression {
    Expression e1;
    Expression e2;

    int op; // +1 | -2 | *3 | /4

    public  ArithExp(char op, Expression first, Expression second) {
        this.e1 = first;
        this.e2 = second;
        switch (op) {
            case '+' -> this.op = 1;
            case '-' -> this.op = 2;
            case '*' -> this.op = 3;
            case '/' -> this.op = 4;
        }
    }

    @Override
    public Value eval(MyIDictionary<String, Value> table) throws GeneralException {
        Value v1, v2;
        v1 = e1.eval(table);
        if(v1.getType().equals(new IntType())) {
            v2 = e2.eval(table);
            if(v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();

                switch(op) {
                    case 1 -> {return new IntValue(n1 + n2);}
                    case 2 -> {return new IntValue(n1 - n2);}
                    case 3 -> {return new IntValue(n1 * n2);}
                    case 4 -> {
                        if(n2==0) throw new ExpressionException("division by 0"); //todo
                        else return new IntValue(n1 / n2);}
                    default -> {throw new ExpressionException("op invalid");}
                }

            }
            else throw new ExpressionException("2nd not integer");
        }
        else throw new ExpressionException("1st not integer");
    }

    @Override
    public String toString() {
        return switch (op){
            case 1 -> e1.toString()+ "+" +e2.toString();
            case 2 -> e1.toString()+ "-" +e2.toString();
            case 3 -> e1.toString()+ "*" +e2.toString();
            case 4 -> e1.toString()+ "/" +e2.toString();
            default -> "";
        };
    }
}
