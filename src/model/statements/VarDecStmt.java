package model.statements;

import exceptions.ADTException;
import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.types.Type;
import model.values.Value;

public class VarDecStmt implements IStmt {
    private String name;
    private Type type;

    public VarDecStmt(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws GeneralException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if(symTable.isDefined(name))
            throw new StatementException("Variable already exists: " + name);
        else{
            symTable.add(name, type.getDefault());
        }
        //state.setSymTable(symTable);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new  VarDecStmt(name, type);
    }

    @Override
    public String toString(){
        return type.toString() + " " + name;
    }
}
