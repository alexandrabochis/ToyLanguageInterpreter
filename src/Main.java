import controller.Controller;
import exceptions.FileException;
import exceptions.GeneralException;
import model.PrgState;
import model.adt.*;
import model.statements.IStmt;
import model.types.Type;
import repository.IRepository;
import repository.Repository;
import view.Examples;
import view.TextMenu;
import view.UI;
import view.commands.ExitCommand;
import view.commands.RunExample;

public class Main {
    public void main(){

        IStmt[] ex = new Examples().getExamples();

        try {
            TextMenu menu = new TextMenu();

            menu.addCommand(new ExitCommand("0", "exit"));

            try{
                MyIDictionary<String, Type> t1 = ex[0].typecheck(new MyDictionary<>());
                String filePath1 = "log1.txt";
                IRepository repo = new Repository(filePath1);
                PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[0], new MyDictionary<>(), new MyHeap<>(), 1);
                repo.addPrgState(prg1);
                Controller controller = new Controller(repo);
                menu.addCommand(new RunExample("1",ex[0].toString(), controller));
            }
            catch(GeneralException e) {
                System.out.println(e.getMessage());
            }

            try{
                MyIDictionary<String, Type> t2 = ex[1].typecheck(new MyDictionary<>());
                String filePath2 = "log2.txt";
                IRepository repo2 = new Repository(filePath2);
                PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[1], new MyDictionary<>(), new MyHeap<>(), 1);
                repo2.addPrgState(prg2);
                Controller controller2 = new Controller(repo2);
                menu.addCommand(new RunExample("2",ex[1].toString(), controller2));
            }
            catch(GeneralException e) {
                System.out.println(e.getMessage());
            }

            try{
                MyIDictionary<String, Type> t3 = ex[2].typecheck(new MyDictionary<>());
                String filePath3 = "log3.txt";
                IRepository repo3 = new Repository(filePath3);
                PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[2], new MyDictionary<>(), new MyHeap<>(), 1);
                repo3.addPrgState(prg3);
                Controller controller3 = new Controller(repo3);
                menu.addCommand(new RunExample("3",ex[2].toString(), controller3));
            }
            catch(GeneralException e) {
                System.out.println(e.getMessage());
            }

            try{
                MyIDictionary<String, Type> t4 = ex[3].typecheck(new MyDictionary<>());
                String filePath4 = "log4.txt";
                IRepository repo4 = new Repository(filePath4);
                PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[3], new MyDictionary<>(), new MyHeap<>(), 1);
                repo4.addPrgState(prg4);
                Controller controller4 = new Controller(repo4);
                menu.addCommand(new RunExample("4",ex[3].toString(), controller4));
            }
            catch(GeneralException e) {
                System.out.println(e.getMessage());
            }

            try{
                MyIDictionary<String, Type> t5 = ex[4].typecheck(new MyDictionary<>());
                String filePath5 = "log5.txt";
                IRepository repo5 = new Repository(filePath5);
                PrgState prg5 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[4], new MyDictionary<>(), new MyHeap<>(), 1);
                repo5.addPrgState(prg5);
                Controller controller5 = new Controller(repo5);
                menu.addCommand(new RunExample("5",ex[4].toString(), controller5));
            }
            catch(GeneralException e) {
                System.out.println(e.getMessage());
            }

            try{
                MyIDictionary<String, Type> t6 = ex[5].typecheck(new MyDictionary<>());
                String filePath6 = "log6.txt";
                IRepository repo6 = new Repository(filePath6);
                PrgState prg6 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[5], new MyDictionary<>(), new MyHeap<>(), 1);
                repo6.addPrgState(prg6);
                Controller controller6 = new Controller(repo6);
                menu.addCommand(new RunExample("6",ex[5].toString(), controller6));
            }
            catch(GeneralException e) {
                System.out.println(e.getMessage());
            }

            try{
                MyIDictionary<String, Type> t7 = ex[6].typecheck(new MyDictionary<>());
                String filePath7 = "log7.txt";
                IRepository repo7 = new Repository(filePath7);
                PrgState prg7 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[6], new MyDictionary<>(), new MyHeap<>(), 1);
                repo7.addPrgState(prg7);
                Controller controller7 = new Controller(repo7);
                menu.addCommand(new RunExample("7",ex[6].toString(), controller7));
            }
            catch(GeneralException e) {
                System.out.println(e.getMessage());
            }

            try{
                MyIDictionary<String, Type> t8 = ex[7].typecheck(new MyDictionary<>());
                String filePath8 = "log8.txt";
                IRepository repo8 = new Repository(filePath8);
                PrgState prg8 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[7], new MyDictionary<>(), new MyHeap<>(), 1);
                repo8.addPrgState(prg8);
                Controller controller8 = new Controller(repo8);
                menu.addCommand(new RunExample("8",ex[7].toString(), controller8));
            }
            catch(GeneralException e) {
                System.out.println(e.getMessage());
            }

            try{
                MyIDictionary<String, Type> t9 = ex[8].typecheck(new MyDictionary<>());
                String filePath9 = "log9.txt";
                IRepository repo9 = new Repository(filePath9);
                PrgState prg9 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[8], new MyDictionary<>(), new MyHeap<>(), 1);
                repo9.addPrgState(prg9);
                Controller controller9 = new Controller(repo9);
                menu.addCommand(new RunExample("9",ex[8].toString(), controller9));
            }
            catch(GeneralException e) {
                System.out.println(e.getMessage()+ e.fillInStackTrace().toString());
            }

            try{
                MyIDictionary<String, Type> t10 = ex[9].typecheck(new MyDictionary<>());
                String filePath10 = "log10.txt";
                IRepository repo10 = new Repository(filePath10);
                PrgState prg10 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[9], new MyDictionary<>(), new MyHeap<>(), 1);
                repo10.addPrgState(prg10);
                Controller controller10 = new Controller(repo10);
                menu.addCommand(new RunExample("10",ex[9].toString(), controller10));
            }
            catch(GeneralException e) {
                System.out.println(e.getMessage());
            }

            menu.show();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}