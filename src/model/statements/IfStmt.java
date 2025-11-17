package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.expressions.Expression;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.Value;

public class IfStmt implements IStmt {
    private Expression expression;
    private IStmt thenStmt;
    private IStmt elseStmt;

    public IfStmt(Expression expression, IStmt thenStmt, IStmt elseStmt) {
        this.expression = expression;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public PrgState execute(PrgState state) throws GeneralException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIStack<IStmt> stack = state.getExeStack();
        Value condition = expression.eval(state.getSymTable());
        if(!condition.getType().equals(new BoolType()))
            throw new StatementException("Condition expression is not boolean");
        else{
            if(condition.equals(new BoolValue(true)))
                stack.push(thenStmt);
            else
                stack.push(elseStmt);
        }

        state.setExeStack(stack);
        return state;
    }

    @Override
    public String toString(){
        return "(IF(" + expression.toString() + ") THEN(" + thenStmt.toString() + ") ELSE(" + elseStmt.toString() + "))";
    }

}
