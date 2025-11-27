package model.expressions;

import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;
import model.adt.MyIStack;
import model.statements.IStmt;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.Value;

public class WhileStmt implements IStmt {
    private Expression expression;
    private IStmt stmt;

    public WhileStmt(Expression expression, IStmt stmt) {
        this.expression = expression;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws GeneralException {
        MyIStack<IStmt> stack = state.getExeStack();

        Value val = this.expression.eval(state.getSymTable(), state.getHeap());
        if(!val.getType().equals(new BoolType()))
            throw new StatementException("Exp not boolean");

        if(val.equals(new BoolValue(true))){
            stack.push(new WhileStmt(expression,stmt));
            stack.push(stmt);
        }
        state.setExeStack(stack);
        return state;
    }

    public String toString() {
        return "While(" + expression + ")" + "{" + this.stmt.toString() + "}";
    }
}
