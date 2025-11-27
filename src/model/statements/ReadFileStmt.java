package model.statements;

import com.sun.jdi.IntegerValue;
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

public class ReadFileStmt implements IStmt {
    private Expression exp;
    private String varName;

    public ReadFileStmt(Expression exp, String varName) {
        this.exp = exp;
        this.varName = varName;
    }

    public String toString() {
        return exp.toString() + " " + varName;
    }

    @Override
    public PrgState execute(PrgState state) throws GeneralException {
        MyIDictionary<String, Value> symtbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        if(!symtbl.isDefined(varName)){
            throw new FileException("Variable " + varName + " is not defined");
        }
        Value val = symtbl.search(varName);
        if(!val.getType().equals(new IntType()))
            throw new StatementException("Variable " + varName + " is not of type int");

        Value valExp = exp.eval(symtbl, state.getHeap());
        if(!valExp.getType().equals(new StringType()))
            throw new StatementException("Variable " + exp + " is not of type String");

        StringValue strVal = (StringValue) valExp;
        if(fileTable.isDefined(strVal)){
            try{
                BufferedReader reader = fileTable.search(strVal);
                String line = reader.readLine();
                IntValue intVal;
                if(line == null) intVal = new IntValue(0);
                else intVal = new IntValue(Integer.parseInt(line));
                symtbl.update(varName, intVal);
                state.setSymTable(symtbl);
            }
            catch(Exception e){
                throw new FileException(e.getMessage());
            }
        }
        else throw new StatementException("Expression's string value is defined");

        return state;
    }

}
