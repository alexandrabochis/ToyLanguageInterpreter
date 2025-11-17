package view;

import com.sun.tools.jconsole.JConsoleContext;
import controller.Controller;
import model.PrgState;
import model.adt.MyDictionary;
import model.adt.MyIList;
import model.adt.MyList;
import model.adt.MyStack;
import model.statements.IStmt;
import model.values.Value;

import java.util.Objects;
import java.util.Scanner;

public class UI {
    private Controller controller;
    private IStmt[] examples;

    public UI(Controller controller) {
        this.controller = controller;
        examples = new Examples().getExamples();
    }

    public void printMenu(){
        System.out.println("""
                1. See all programs
                2. Chose a program to execute
                0. Exit
                """);
    }

    public void seePrograms(){
        System.out.println("""
                1. int v; v=2;Print(v)
                2. int a;int b; a=2+3*5;b=a+1;Print(b)
                3. bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
                """);
    }

    public void loadProgram(IStmt statement){
        PrgState program = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), statement, new MyDictionary<>());
        this.controller.add(program);
    }

    private void executeProgram() {
        System.out.println("Which program would you like to execute?");
        Scanner sc = new Scanner(System.in);
        System.out.print(">>");
        int program = sc.nextInt();

        System.out.println("How would you like to execute the program?");
        System.out.println("""
                1. Show intermediate steps
                2. Do not show intermediate steps
                """);
        System.out.println(">>");
        int option = sc.nextInt();
        controller.setDisplayFlag(Objects.equals(option, 1));

        try{
            this.loadProgram(examples[program-1]);
            controller.allSteps();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void run(){
        printMenu();
        Scanner sc = new Scanner(System.in);
        System.out.println(">>");
        String choice = sc.nextLine();
        while(!choice.equals("0")){
            switch (choice){
                case "1" -> seePrograms();
                case "2" -> executeProgram();
                default -> System.out.println("Invalid choice");
            }
            printMenu();
            System.out.println(">>");
            choice = sc.nextLine();
        }
    }



}
