package model.statements;

import exceptions.FileException;
import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.expressions.Expression;
import model.types.IntType;
import model.types.StringType;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;

public class CloseRFileStmt implements IStmt{
    private Expression exp;


    public CloseRFileStmt(Expression exp) {
        this.exp = exp;

    }

    public String toString() {
        return "closed(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws GeneralException {
        MyIDictionary<String, Value> symtbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();


        Value val = exp.eval(symtbl,  state.getHeap());
        if(val.getType().equals(new StringType())) {

            StringValue strVal = (StringValue) val;
            if (!fileTable.isDefined(strVal))
                throw new StatementException("File does not exist");
            else{
                try{
                    BufferedReader reader = fileTable.search(strVal);
                    reader.close();
                    fileTable.remove(strVal);
                }
                catch (Exception e){
                    throw new FileException(e.getMessage());
                }
            }
        }
        else throw new StatementException("Invalid expression");

        return state;
    }

}
