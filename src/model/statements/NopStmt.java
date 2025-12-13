package model.statements;

import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.types.Type;

public class NopStmt implements IStmt {
    public NopStmt(){}



    @Override
    public String toString(){
        return "\n";
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException {
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws GeneralException {
        return typeEnv;
    }
}
