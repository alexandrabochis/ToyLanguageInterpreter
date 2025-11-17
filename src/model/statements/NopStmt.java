package model.statements;

import exceptions.StatementException;
import model.PrgState;

public class NopStmt implements IStmt {
    public NopStmt(){}



    @Override
    public String toString(){
        return "\n";
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException {
        return state;
    }
}
