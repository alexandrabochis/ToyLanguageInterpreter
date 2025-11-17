package repository;

import exceptions.FileException;
import exceptions.GeneralException;
import model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Repository implements IRepository{
    private LinkedList<PrgState> prgStates;
    String logFilePath;

    public Repository(String filePath) throws FileException {
        try{
           PrintWriter testPath= new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
        }
        catch (Exception e){
            throw new FileException(e.getMessage());
        }
        this.prgStates = new LinkedList<>();
        this.logFilePath = filePath;
    }

    @Override
    public PrgState getCurrentPrgState() {
        PrgState current = this.prgStates.getFirst();
        this.prgStates.removeFirst();
        return current;
    }

    @Override
    public void addPrgState(PrgState prgState) {
        this.prgStates.add(prgState);
    }

    @Override
    public void logPrgStateExec(PrgState prgState) throws GeneralException {
        PrintWriter logFile;
        try{
            logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        }
        catch(Exception e){
            throw new FileException(e.getMessage());
        }
        logFile.println(prgState.toString());
        logFile.flush();
        if(prgState.getExeStack().isEmpty()){
            logFile.close();
        }
    }
}
