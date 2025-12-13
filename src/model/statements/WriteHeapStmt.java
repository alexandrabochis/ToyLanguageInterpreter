package model.statements;

import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;
import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.expressions.Expression;
import model.types.RefType;
import model.types.Type;
import model.values.RefValue;
import model.values.Value;

public class WriteHeapStmt implements IStmt{
    public String name;
    public Expression expression;

    public WriteHeapStmt(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }


    @Override
    public PrgState execute(PrgState state) throws GeneralException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();

        if (!symTable.isDefined(this.name)) throw new StatementException("Variable " + name + " not defined");

        Value tableV = symTable.search(this.name);
        if(!(tableV.getType() instanceof RefType))
            throw new StatementException("Variable " + name + " is not a ref type");
        int refAddr = ((RefValue)tableV).getAddr();
        if(!heap.exists(refAddr)) throw new StatementException("Variable " + name + " does not exist on heap");
        Value valExp = this.expression.eval(symTable,heap);
        if(!valExp.getType().equals(heap.get(refAddr).getType()))
            throw new StatementException("exp and var name have different types");

        heap.put(refAddr, valExp);
        //state.setSymTable(symTable);
        //state.setHeap(heap);
        return null;

    }

    @Override
    public IStmt deepCopy() {
        return new  WriteHeapStmt(name, expression);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws GeneralException {
        Type typevar = typeEnv.search(this.name);
        Type typeexp = expression.typecheck(typeEnv);
        if(typevar.equals(new RefType(typeexp))){
            if(typeexp.equals(((RefType)typevar).getInner()))
                return typeEnv;
            else throw new StatementException("Write heap: exp not of correct type");
        }
        else throw new StatementException("Write heap: variable is not of Ref type");
    }

    public String toString(){
        return "wH( "+this.name+", "+this.expression.toString()+")";
    }
}
