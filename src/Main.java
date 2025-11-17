import controller.Controller;
import exceptions.FileException;
import model.PrgState;
import model.adt.MyDictionary;
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
            PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[0], new MyDictionary<>());
            repo.addPrgState(prg1);
            Controller controller = new Controller(repo);

            String filePath2 = "log2.txt";
            IRepository repo2 = new Repository(filePath2);
            PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[1], new MyDictionary<>());
            repo2.addPrgState(prg2);
            Controller controller2 = new Controller(repo2);

            String filePath3 = "log3.txt";
            IRepository repo3 = new Repository(filePath3);
            PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[2], new MyDictionary<>());
            repo3.addPrgState(prg3);
            Controller controller3 = new Controller(repo3);

            String filePath4 = "log4.txt";
            IRepository repo4 = new Repository(filePath4);
            PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex[3], new MyDictionary<>());
            repo4.addPrgState(prg4);
            Controller controller4 = new Controller(repo4);


            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));
            menu.addCommand(new RunExample("1",ex[0].toString(), controller));
            menu.addCommand(new RunExample("2",ex[1].toString(), controller2));
            menu.addCommand(new RunExample("3",ex[2].toString(), controller3));
            menu.addCommand(new RunExample("4",ex[3].toString(), controller4));

            menu.show();
        }
        catch(FileException e){
            System.out.println(e.getMessage());
        }
    }
}
