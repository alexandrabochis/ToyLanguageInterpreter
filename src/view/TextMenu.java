package view;

import view.commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> commands;
    public TextMenu() {
        commands = new HashMap<>();
    }
    public void addCommand(Command c){
        commands.put(c.getKey(), c);
    }

    private void printMenu(){
        for(Command com : commands.values()){
            String line = String.format("%4s: %2s", com.getKey(), com.getDescription());
            System.out.println(line);
        }


    }

    public void show(){
        Scanner input = new Scanner(System.in);
        while(true){
            printMenu();
            System.out.print("> ");
            String key = input.nextLine();
            Command com = commands.get(key);
            if(com == null){
                System.out.println( "Invalid command");
                continue;
            }
            com.execute();
        }
    }
}
