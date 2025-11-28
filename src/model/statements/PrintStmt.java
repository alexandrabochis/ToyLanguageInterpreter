package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;
import model.adt.MyIList;
import model.expressions.Expression;
import model.values.Value;

public class PrintStmt implements IStmt{
    Expression exp;

    public PrintStmt(Expression e){
        this.exp = e;
    }

    public String toString(){
        return "print(" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws GeneralException {
        MyIList<Value> out = state.getOut();
        out.add(exp.eval(state.getSymTable(), state.getHeap()));
        //state.setOut(out);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new  PrintStmt(exp);
    }

}
