package controller;

import exceptions.GeneralException;
import model.PrgState;
import model.adt.MyIStack;
import model.statements.IStmt;
import repository.IRepository;

public class Controller {
    private IRepository repository;
    boolean displayFlag;

    public Controller(IRepository repository) {
        this.repository = repository;
        this.displayFlag = false;
    }

    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public void add(PrgState prgState) {
        this.repository.addPrgState(prgState);
    }

    public PrgState oneStep(PrgState state) throws Exception{
        MyIStack<IStmt> stack = state.getExeStack();
        if(stack.isEmpty()){
            throw new Exception("Stack is empty");
        }else{
            IStmt current = stack.pop();
            return current.execute(state);
        }
    }

    public void allSteps() throws Exception {
        PrgState program = repository.getCurrentPrgState();
        repository.logPrgStateExec(program);
        if(this.displayFlag){
            System.out.println(displayState(program));
        }
        while(!program.getExeStack().isEmpty()){


            try{
                oneStep(program);

            }catch(Exception e){
                throw new GeneralException(e.getMessage());
            }
            if(this.displayFlag)
                System.out.println(displayState(program));
            repository.logPrgStateExec(program);
        }
        if(!this.displayFlag){
            System.out.println(displayState(program));
        }

    }

    public String displayState(PrgState state)  {
        return state.toString();
    }

}
