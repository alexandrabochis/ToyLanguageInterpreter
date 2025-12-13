package model.statements;

import exceptions.GeneralException;
import model.PrgState;
import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.adt.MyStack;
import model.types.Type;
import model.values.IntValue;
import model.values.Value;

public class ForkStmt implements IStmt {
    IStmt statement;

    public ForkStmt(IStmt statement) {
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws GeneralException {

        MyStack<IStmt> newExecutionStack = new MyStack<>();
        //newExecutionStack.push(statement);

        MyIDictionary<String, Value> cloneSymbolTable = state.getSymTable().deepCopy();

        int newID = state.getId()*10;
//        int cid = newID, cnt = 0;
//        while (cid>9){cnt++; cid /= 10;}
//        newID += cnt;
        PrgState newThread = new PrgState(newExecutionStack, cloneSymbolTable, state.getOut(), this.statement, state.getFileTable(), state.getHeap(), newID);

        return newThread;

    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws GeneralException {
        statement.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }

    public String toString(){
        return "Fork( " +  statement.toString() + " )";

    }
}
