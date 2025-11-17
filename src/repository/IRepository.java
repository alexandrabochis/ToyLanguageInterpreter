package repository;

import exceptions.GeneralException;
import model.PrgState;

public interface IRepository {
    PrgState getCurrentPrgState();
    void addPrgState(PrgState prgState);
    void logPrgStateExec(PrgState prgState) throws GeneralException;
}
