package controller;

import exceptions.FileException;
import exceptions.GeneralException;
import model.PrgState;
import model.adt.MyIStack;
import model.statements.IStmt;
import model.values.RefValue;
import model.values.Value;
import repository.IRepository;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;
    boolean displayFlag;
    private ExecutorService executor;

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

    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap){
        Set<Integer> reacheableAddr = new HashSet<>(symTableAddr);

        boolean changed = false;
        do{
            changed = false;
            List<Integer> currentReachable = new ArrayList<>(reacheableAddr);

            for (Integer addr : currentReachable){
                if(heap.containsKey(addr)){
                    Value value = heap.get(addr);
                    if(value instanceof RefValue){
                        RefValue ref = (RefValue)value;
                        int refAddr = ref.getAddr();
                        if(!reacheableAddr.contains(refAddr)){
                            reacheableAddr.add(refAddr);
                            changed = true;
                        }
                    }
                }
            }
        }while(changed);

        return heap.entrySet().stream()
                        .filter(e->reacheableAddr.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap){
        return heap.entrySet().stream()
                        .filter(e->symTableAddr.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

//    public void allSteps() throws Exception {
//        PrgState program = repository.getCurrentPrgState();
//        repository.logPrgStateExec(program);
//        if(this.displayFlag){
//            System.out.println(displayState(program));
//        }
//        while(!program.getExeStack().isEmpty()){
//
//
//            try{
//                oneStep(program);
//
//            }catch(Exception e){
//                throw new GeneralException(e.getMessage());
//            }
//            if(this.displayFlag)
//                System.out.println(displayState(program));
//            repository.logPrgStateExec(program);
//            program.getHeap().setHeap((HashMap<Integer, Value>) safeGarbageCollector(
//                    getAddrFromSymTable(program.getSymTable().getDictionary().values()),
//                    program.getHeap().getHeap()));
//            repository.logPrgStateExec(program);
//        }
//        if(!this.displayFlag){
//            System.out.println(displayState(program));
//        }
//
//    }

    public void allSteps() throws Exception{
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedProgram(repository.getProgramList());
        while(prgList.size()>0){


            prgList.forEach(program -> program.getHeap().setHeap((HashMap<Integer, Value>) safeGarbageCollector(
                    getAddrFromSymTable(program.getSymTable().getDictionary().values()),
                    program.getHeap().getHeap())));

            oneStepForAllPrograms(prgList);
            prgList = removeCompletedProgram(repository.getProgramList());
        }
        executor.shutdownNow();

        repository.setProgramList(prgList);
        repository.getProgramList().forEach(p->System.out.println(displayState(p)));
    }

    public void oneStepForAllPrograms(List<PrgState> prgList) throws Exception {

        for (PrgState prgState : prgList) {
            try {
                repository.logPrgStateExec(prgState);
            } catch (GeneralException e) {
                throw new GeneralException(e.getMessage());
            }
        }

        List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>) (() -> {
                    return p.oneStep();
                }))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());                                                        ///eroare daca dau exceptii custom / Exception
                    }
                })
                .filter(p -> p != null).collect(Collectors.toList());

        prgList.addAll(newPrgList);

        for (PrgState prgState : prgList) {
            try {
                repository.logPrgStateExec(prgState);
            } catch (GeneralException e) {
                throw new GeneralException(e.getMessage());
            }
        }
        repository.setProgramList(prgList);
    }

    public String displayState(PrgState state)  {
        return state.toString();
    }

    public List<PrgState> removeCompletedProgram(List<PrgState> inProgramList){
        return inProgramList.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
    }

}

