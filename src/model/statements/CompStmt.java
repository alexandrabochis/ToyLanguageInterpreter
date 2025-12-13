package model.statements;

import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.types.Type;

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
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(first, second);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws GeneralException {
        return second.typecheck(first.typecheck(typeEnv));
    }
}
