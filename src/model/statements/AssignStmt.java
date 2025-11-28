package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.expressions.Expression;
import model.types.Type;
import model.values.Value;

public class AssignStmt implements IStmt{
    private String id;
    private Expression expression;

    public AssignStmt(String id, Expression expression){
        this.id = id;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws GeneralException {
        MyIDictionary<String, Value> symTable = state.getSymTable();

        if(symTable.isDefined(id)){
            Value val = expression.eval(symTable, state.getHeap());
            Type typeID = (symTable.search(id).getType());

            if(val.getType().equals(typeID)){
                symTable.update(id,val);
            }
            else{
                throw new StatementException("type and val do not match");
            }

        }else{
            throw new StatementException("var not declared before");
        }

        //state.setSymTable(symTable);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new  AssignStmt(id, expression);
    }

    @Override
    public String toString() {
        return id + "=" + expression.toString();
    }

}
