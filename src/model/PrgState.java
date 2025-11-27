package model;

import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.adt.MyIList;
import model.adt.MyIStack;
import model.statements.IStmt;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIHeap<Value> heap;
    IStmt originalProgram; //optional

    public PrgState(MyIStack<IStmt> estk, MyIDictionary<String, Value> symtbl, MyIList<Value> o) {
        this.exeStack = estk;
        this.symTable = symtbl;
        this.out = o;
    }

    public PrgState(MyIStack<IStmt> estk, MyIDictionary<String, Value> symtbl, MyIList<Value> o, MyIHeap<Value> heap) {
        this.exeStack = estk;
        this.symTable = symtbl;
        this.out = o;
        this.heap = heap;
    }

    public PrgState(MyIStack<IStmt> estk, MyIDictionary<String, Value> symtbl, MyIList<Value> o, IStmt ogPrg, MyIDictionary<StringValue, BufferedReader> ftbl, MyIHeap<Value> heap){
        this.exeStack = estk;
        this.symTable = symtbl;
        this.out = o;
        this.originalProgram = ogPrg;
        this.fileTable = ftbl;
        this.heap = heap;
        this.exeStack.push(this.originalProgram);
    }

    public PrgState(MyIStack<IStmt> estk, MyIDictionary<String, Value> symtbl, MyIList<Value> o, IStmt ogPrg, MyIDictionary<StringValue, BufferedReader> ftbl){
        this.exeStack = estk;
        this.symTable = symtbl;
        this.out = o;
        this.originalProgram = ogPrg;
        this.fileTable = ftbl;
        this.exeStack.push(this.originalProgram);
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public void setHeap(MyIHeap<Value> heap) {
        this.heap = heap;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }
    public MyIHeap<Value> getHeap() {
        return heap;
    }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    @Override
    public String toString(){
        return "ProgramState: \n" +
                "~ exeStack= " + exeStack.toString() + "\n"+
                "~ symTable= " + symTable.toString() + "\n"+
                "~ fileTable= " + fileTable.toString() + "\n"+
                "~ Heap= " + heap.toString() + "\n"+
                "~ out= " + out.toString() + "\n\n";
    }

}
