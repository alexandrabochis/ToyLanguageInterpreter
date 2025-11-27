package model.statements;

import exceptions.FileException;
import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.expressions.Expression;
import model.types.StringType;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.FileReader;

public class OpenRFileStmt implements IStmt {
    private Expression exp;

    public OpenRFileStmt(Expression exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws GeneralException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        Value val = exp.eval(symTable, state.getHeap());
        if(val.getType().equals(new StringType())){
            StringValue strVal = (StringValue) val;
            if(fileTable.isDefined(strVal))
                throw new StatementException("file already opened");
            else{
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(strVal.getValue()));
                    fileTable.add(strVal, reader);
                }
                catch(Exception e){
                    throw new FileException(e.getMessage());
                }
            }
        }

        state.setFileTable(fileTable);
        return state;
    }

    @Override
    public String toString(){
        return "open(" + this.exp.toString() + ')';
    }
}
