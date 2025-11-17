package view;

import model.expressions.ArithExp;
import model.expressions.ValueExp;
import model.expressions.VarExp;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;

public class Examples {
    IStmt ex1= new CompStmt(new VarDecStmt("v",new IntType()),
                            new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                                         new PrintStmt(new VarExp("v"))));

    IStmt ex2 = new CompStmt(
            new VarDecStmt("a",new IntType()),
            new CompStmt(new VarDecStmt("b",new IntType()),
                         new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                            ArithExp('*',
                                         new ValueExp(new IntValue(3)),
                                         new ValueExp(new IntValue(5))))),
                            new CompStmt(new AssignStmt("b",
                                                            new ArithExp('+',
                                                                              new VarExp("a"),
                                                                              new ValueExp(new IntValue(1)))),
                                         new PrintStmt(new VarExp("b"))))));
    IStmt ex3 = new CompStmt(new VarDecStmt("a",new BoolType()),
                             new CompStmt(new VarDecStmt("v", new IntType()),
                                          new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                                       new CompStmt(new IfStmt(new VarExp("a"),
                                                                    new AssignStmt("v",new ValueExp(new IntValue(2))),
                                                                    new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                                                    new PrintStmt(new VarExp("v"))))));
    IStmt ex4 = new CompStmt(
            new VarDecStmt("varf", new StringType()),
            new CompStmt(
                    new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                    new CompStmt(
                            new OpenRFileStmt(new VarExp("varf")),
                            new CompStmt(
                                    new VarDecStmt("varc", new IntType()),
                                    new CompStmt(
                                            new ReadFileStmt(new VarExp("varf"), "varc"),
                                            new CompStmt(
                                                    new PrintStmt(new VarExp("varc")),
                                                    new CompStmt(
                                                            new ReadFileStmt(new VarExp("varf"), "varc"),
                                                            new CompStmt(
                                                                    new PrintStmt(new VarExp("varc")),
                                                                    new CloseRFileStmt(new VarExp("varf"))
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            )
    );

    public IStmt[] getExamples(){
        return new IStmt[]{ex1, ex2, ex3, ex4};
    }
}
