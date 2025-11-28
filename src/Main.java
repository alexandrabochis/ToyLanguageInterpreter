import controller.Controller;
import exceptions.FileException;
import model.PrgState;
import model.adt.MyDictionary;
import model.adt.MyHeap;
import model.adt.MyList;
import model.adt.MyStack;
import model.statements.IStmt;
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
            String filePath1 = "log1.txt";
            IRepository repo = new Repository(filePath1);
            PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[0], new MyDictionary<>(), new MyHeap<>());
            repo.addPrgState(prg1);
            Controller controller = new Controller(repo);

            String filePath2 = "log2.txt";
            IRepository repo2 = new Repository(filePath2);
            PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[1], new MyDictionary<>(), new MyHeap<>());
            repo2.addPrgState(prg2);
            Controller controller2 = new Controller(repo2);

            String filePath3 = "log3.txt";
            IRepository repo3 = new Repository(filePath3);
            PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[2], new MyDictionary<>(), new MyHeap<>());
            repo3.addPrgState(prg3);
            Controller controller3 = new Controller(repo3);

            String filePath4 = "log4.txt";
            IRepository repo4 = new Repository(filePath4);
            PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[3], new MyDictionary<>(), new MyHeap<>());
            repo4.addPrgState(prg4);
            Controller controller4 = new Controller(repo4);

            String filePath5 = "log5.txt";
            IRepository repo5 = new Repository(filePath5);
            PrgState prg5 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[4], new MyDictionary<>(), new MyHeap<>());
            repo5.addPrgState(prg5);
            Controller controller5 = new Controller(repo5);

            String filePath6 = "log6.txt";
            IRepository repo6 = new Repository(filePath6);
            PrgState prg6 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[5], new MyDictionary<>(), new MyHeap<>());
            repo6.addPrgState(prg6);
            Controller controller6 = new Controller(repo6);

            String filePath7 = "log7.txt";
            IRepository repo7 = new Repository(filePath7);
            PrgState prg7 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[6], new MyDictionary<>(), new MyHeap<>());
            repo7.addPrgState(prg7);
            Controller controller7 = new Controller(repo7);

            String filePath8 = "log8.txt";
            IRepository repo8 = new Repository(filePath8);
            PrgState prg8 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[7], new MyDictionary<>(), new MyHeap<>());
            repo8.addPrgState(prg8);
            Controller controller8 = new Controller(repo8);

            String filePath9 = "log9.txt";
            IRepository repo9 = new Repository(filePath9);
            PrgState prg9 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[8], new MyDictionary<>(), new MyHeap<>());
            repo9.addPrgState(prg9);
            Controller controller9 = new Controller(repo9);

            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));
            menu.addCommand(new RunExample("1",ex[0].toString(), controller));
            menu.addCommand(new RunExample("2",ex[1].toString(), controller2));
            menu.addCommand(new RunExample("3",ex[2].toString(), controller3));
            menu.addCommand(new RunExample("4",ex[3].toString(), controller4));
            menu.addCommand(new RunExample("5",ex[4].toString(), controller5));
            menu.addCommand(new RunExample("6",ex[5].toString(), controller6));
            menu.addCommand(new RunExample("7",ex[6].toString(), controller7));
            menu.addCommand(new RunExample("8",ex[7].toString(), controller8));
            menu.addCommand(new RunExample("9",ex[8].toString(), controller9));

            menu.show();
        }
        catch(FileException e){
            System.out.println(e.getMessage());
        }
    }
}
