package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.types.Type;

public interface IStmt {
    PrgState execute(PrgState state) throws GeneralException; //todo exception
    public IStmt deepCopy();
    MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws GeneralException;
}
