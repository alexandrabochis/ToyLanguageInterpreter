package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws GeneralException; //todo exception

}
