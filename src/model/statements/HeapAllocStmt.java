package model.statements;

import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.adt.MyIList;
import model.expressions.Expression;
import model.types.RefType;
import model.values.RefValue;
import model.values.Value;

public class HeapAllocStmt implements IStmt{
    String name;
    Expression exp;
    public HeapAllocStmt(String name, Expression exp) {
        this.name = name;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws GeneralException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();

        if(!symTbl.isDefined(this.name))
            throw new StatementException("Variable " + this.name + " is not defined");
        Value var = symTbl.search(this.name);
        if(! (var.getType() instanceof RefType))
            throw new StatementException("Variable " + this.name + " is not of type RefType");

        Value newVal = this.exp.eval(state.getSymTable(), heap);
        if(!(newVal.getType().equals(((RefType)var.getType()).getInner())))
            throw new StatementException("type of Variable " + this.name + " is not the same as exp value");
        int addr = heap.allocate(newVal);
        symTbl.update(this.name, new RefValue(addr, newVal.getType()));

        state.setHeap(heap);
        state.setSymTable(symTbl);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new HeapAllocStmt(this.name, this.exp);
    }

    @Override
    public String toString() {
        return "new("+this.name+","+this.exp+")";
    }
}
