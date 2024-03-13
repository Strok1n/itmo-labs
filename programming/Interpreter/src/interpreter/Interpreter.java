package interpreter;


import java.lang.reflect.InvocationTargetException;
import java.util.*;

import interpreter.collection.MainCollection;
import interpreter.commands.*;
import java.io.*;
import business.*;
import interpreter.commands.concrete.*;

import static java.util.List.of;

//import static java.lang.System.*;



public class Interpreter
{
    private final Map<String, Command> availableCommands;
    private final Scanner consoleScanner;
    private final PrintStream outputStream;

    private final Queue<String> scriptsFileNames;
    private final MainCollection baseCollection;


    private Queue<String> commandHistory;
    public Interpreter() throws NoSuchFieldException
    {
        this.availableCommands = new HashMap<String, Command>();

        this.consoleScanner = new Scanner(System.in);
        this.outputStream = System.out;

        this.commandHistory = new ArrayDeque<>();


        this.baseCollection = new MainCollection<LabWork>();
        this.baseCollection.setContainer( new HashSet<LabWork>() );
        this.baseCollection.setCollectionInitializationDate(java.time.ZonedDateTime.now());

        this.scriptsFileNames = new ArrayDeque<>();

        Help help = new Help();
        Insert insert = new Insert();
        ExecuteScript executeScript = new ExecuteScript();
        History history = new History();

        history.setCommandHistory(this.commandHistory);


       // update.setInsert(insert);



        this.availableCommands.put("help", help);
        this.availableCommands.put("info", new Info());
        this.availableCommands.put("show", new Show());
        this.availableCommands.put("insert", insert);
        this.availableCommands.put("add", insert);
        this.availableCommands.put("update", new Update());
        this.availableCommands.put("remove_by_id", new RemoveById());
        this.availableCommands.put("clear", new Clear());
        this.availableCommands.put("save", new Save());
        this.availableCommands.put("execute_script", executeScript);
        this.availableCommands.put("exit", new Exit());
        this.availableCommands.put("remove_greater", new RemoveGreater());
        this.availableCommands.put("remove_lower", new RemoveLower());
        this.availableCommands.put("history", history);
        this.availableCommands.put("sum_of_tuned_in_works", new SumOfTunedInWorks());
        this.availableCommands.put("print_ascending", new PrintAscending());
        this.availableCommands.put("print_field_descending_difficulty", new PrintFieldDescendingDifficulty());
        this.availableCommands.put("restore_from_file", new RestoreFromFile());




        executeScript.setInterpreter(this);

        Collection<Command> collection = this.availableCommands.values();
        collection.remove(null);

//        help.setAvailableCommands( Arrays.copyOf(
//                ( Set.copyOf(collection ) )  .toArray(),
//                this.availableCommands.size(), Command[].class ) );

        help.setAvailableCommands(Set.copyOf(collection));

        for (Command command: this.availableCommands.values()) {
            command.setIOStreams(this.consoleScanner, this.outputStream);
            command.setBaseCollection(this.baseCollection);
        }



    }

    public Command putCommand(String commandName, Command command)
    {
        return this.availableCommands.put(commandName, command);
    }

    public Command getCommand(String commandName)
    {
        return this.availableCommands.get(commandName);
    }

    public void set(Scanner scanner)
    {
        this.availableCommands.get("execute_script").setIOStreams(
                scanner, this.outputStream
        );
    }

    public void handleOneInputString(String input) throws IllegalAccessException, FileNotFoundException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        String[] tokens = input.split(" ");
        String commandName = tokens[0];

        if (this.availableCommands.containsKey(commandName))
        {
           // this.availableCommands.get(commandName).setBaseMap(baseMap);
           // this.availableCommands.get(commandName).setBaseCollection(this.baseCollection);
            if (tokens.length > 1)
                this.availableCommands.get(commandName).setArguments(
                        Arrays.copyOfRange(tokens, 1, tokens.length));
          //  this.availableCommands.get(commandName)
             //       .setIOStreams(this.consoleScanner, this.outputStream);
            this.outputStream.println("Executing command " + commandName + ":");
            this.availableCommands.get(commandName).execute();
            this.commandHistory.add(commandName);
            if (this.commandHistory.size() > 8)
                this.commandHistory.poll();
            //if(commandName == "insert" && this.baseMap.size() == 0)
            //	this.collectionInitializationDate = java.time.ZonedDateTime.now();
        }
        else
        {
            this.outputStream.println("Unknown command: " + commandName);
        }
    }



    public void start() throws IllegalAccessException, FileNotFoundException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        while (true)
        {
            this.outputStream.println("Enter the command:");
            String inputString = this.consoleScanner.nextLine();
            this.handleOneInputString(inputString);
        }
    }

    public Queue<String> getScriptsFileNames() {
        return scriptsFileNames;
    }

    public void addScriptFileName(String fileName)
    {
        this.scriptsFileNames.add(fileName);
    }






}