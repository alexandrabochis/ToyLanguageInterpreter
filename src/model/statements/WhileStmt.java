package model.statements;

import exceptions.GeneralException;
import exceptions.StatementException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.expressions.Expression;
import model.types.BoolType;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;

import javax.swing.plaf.synth.SynthDesktopIconUI;

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
        if(!val.getType().equals(new BoolType())) {

            throw new StatementException("Exp not boolean");
        }
        if(val.equals(new BoolValue(true))){
            stack.push(new WhileStmt(expression,stmt));
            stack.push(stmt);
        }
        //state.setExeStack(stack);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new  WhileStmt(expression,stmt);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws GeneralException {
        //System.out.println("while type checking");
        Type typeexp = expression.typecheck(typeEnv);
        if(typeexp.equals(new BoolType())){
            stmt.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else throw new StatementException("WHILE exp not boolean");
    }

    public String toString() {
        return "While(" + expression + ")" + "{" + this.stmt.toString() + "}";
    }
}
