package model.statements;

import exceptions.StatementException;
import model.PrgState;
import model.adt.MyIStack;

public class CompStmt implements IStmt{

    IStmt first;
    IStmt second;

    public CompStmt(IStmt first, IStmt second){
        this.first = first;
        this.second = second;
    }

    public String toString(){
        return "("+first.toString() + " " + second.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException {
        MyIStack<IStmt> stk = state.getExeStack();
        stk.push(second);
        stk.push(first);
        return state;
    }
}
