package repository;

import exceptions.GeneralException;
import model.PrgState;

import java.util.LinkedList;
import java.util.List;

public interface IRepository {
    //PrgState getCurrentPrgState();
    void addPrgState(PrgState prgState);
    void logPrgStateExec(PrgState prgState) throws GeneralException;

    List<PrgState> getProgramList(); //2
    void setProgramList(List<PrgState> programList);


}
