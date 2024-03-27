package interpreter;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import exceptions.InsertException;
import exceptions.OutputTextException;
import exceptions.WrongFieldValueDuringScriptExecutionException;
import interpreter.collection.MainCollection;
import interpreter.commands.*;
import java.io.*;
import business.*;
import interpreter.commands.concrete.*;
import trash.CommandExecutionMode;
import util.InputValidator;
import util.TypeUtilities;

import static java.util.List.of;

//import static java.lang.System.*;



public class Interpreter
{
    private final Map<String, Command> availableCommands;
    private Scanner consoleScanner;
    private PrintStream outputStream;

    private final Queue<String> scriptsFileNames;
    private Queue<String> commandHistory;
    public Interpreter() throws NoSuchFieldException
    {
        this.availableCommands = new HashMap<String, Command>();

        this.consoleScanner = new Scanner(System.in);
        this.outputStream = System.out;

        this.commandHistory = new ArrayDeque<>();

        this.scriptsFileNames = new ArrayDeque<>();

        Help help = new Help();
        Insert insert = new Insert();
        ExecuteScript executeScript = new ExecuteScript();
        History history = new History();

        history.setCommandHistory(this.commandHistory);

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

        help.setAvailableCommands(Set.copyOf(collection));



        MainCollection baseCollection = new MainCollection<LabWork>();
        baseCollection.setContainer( new HashSet<LabWork>() );
        baseCollection.setCollectionInitializationDate(java.time.ZonedDateTime.now());

        for (Command command: this.availableCommands.values()) {
            command.setIOStreams(this.consoleScanner, this.outputStream);
            command.setBaseCollection(baseCollection);
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

    public void handleInputString(String input) throws IllegalAccessException, FileNotFoundException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        String[] tokens = input.split(" ");
        String commandName = tokens[0];

        if (this.availableCommands.containsKey(commandName))
        {
            if (tokens.length > 1)
            this.availableCommands.get(commandName).setArguments(
                        Arrays.copyOfRange(tokens, 1, tokens.length));

            this.outputStream.println("Executing command " + commandName + ":");


            try {
                this.availableCommands.get(commandName).execute();
            }
            catch (OutputTextException outputTextException)
            {
                this.outputStream.println(outputTextException.getText());
            }
            catch (InsertException insertException){

                Object object = this.createObject(insertException.getObjectClass(), insertException.getInputValidator(), insertException.getObjectCreationMode());

                AddLabWorkToTheCollection addLabWorkToTheCollection = insertException.getAddLabWorkToTheCollection();


                addLabWorkToTheCollection.setLabWork((LabWork) object);

                try {
                    addLabWorkToTheCollection.execute();
                }catch (OutputTextException outputTextException)
                {
                    this.outputStream.println(outputTextException.getText());
                }
            }

            this.outputStream.println("Execution of the command " + commandName + " has ended");
            this.commandHistory.add(commandName);
            if (this.commandHistory.size() > 8)
                this.commandHistory.poll();
        }
        else
        {
            this.outputStream.println("Unknown command: " + commandName);
        }
    }

    public void start() throws IllegalAccessException, IOException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        this.handleInputString("restore_from_file");
        while (true)
        {
            this.outputStream.println("Enter the command:");
            String inputString = this.consoleScanner.nextLine();
            this.handleInputString(inputString);
        }
    }
    public Queue<String> getScriptsFileNames() {
        return scriptsFileNames;
    }

    public void addScriptFileName(String fileName)
    {
        this.scriptsFileNames.add(fileName);
    }


















    private void handleWrongFieldValue(Field field, InputValidator inputValidator, CommandExecutionMode objectCreationMode)
    {
        if (objectCreationMode == CommandExecutionMode.FROM_FILE)
            throw new WrongFieldValueDuringScriptExecutionException();
        this.outputStream.println(inputValidator.getInvalidInputMessage(field));
    }





    private void setFieldValueToTheObject(Object object, Field field, InputValidator inputValidator, CommandExecutionMode objectCreationMode) throws IllegalAccessException
    {
        if(inputValidator.isFieldAutogenerated(field))
            return;
        String input;
        while (true)
        {
            if (objectCreationMode == CommandExecutionMode.MANUAL_INPUT)
                this.outputStream.println("Enter the " + field.getName() + " of the " + field.getDeclaringClass().getSimpleName() + ":" );
//            if (objectCreationMode == CommandExecutionMode.MANUAL_INPUT)
//                throw new OutputTextException("Enter the " + field.getName() + " of the " + field.getDeclaringClass().getSimpleName() + ":");


            if (TypeUtilities.isPrimitiveOrWrapperOrString(field.getType()) || field.getType().isEnum() )
            {
                input = this.consoleScanner.nextLine();
                System.out.println(input);
                Object value;
                try
                {
                    value = TypeUtilities.StringToSpecifiedFieldType(input, field);
                }
                catch (Exception e)
                {
                    handleWrongFieldValue(field, inputValidator, objectCreationMode);
                    continue;
                }
                if (!inputValidator.validateInput(field, input))
                {
                    handleWrongFieldValue(field, inputValidator, objectCreationMode);
                    continue;
                }
                field.setAccessible(true);
                field.set(object, value);
                field.setAccessible(false);
                return;
            }
            else
            {
                for (Field f : field.getType().getDeclaredFields())
                {
                    field.setAccessible(true);

                    this.setFieldValueToTheObject(field.get(object), f, inputValidator, objectCreationMode);

                    field.setAccessible(false);
                }
                return;
            }
        }


    }



    public Object createObject(Class<?> objectClass, InputValidator inputValidator, CommandExecutionMode objectCreationMode) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {
        Object object = objectClass.getDeclaredConstructor().newInstance();
        for (Field field : objectClass.getDeclaredFields())
        {
            try
            {
                this.setFieldValueToTheObject(object, field, inputValidator, objectCreationMode);
            }
            catch (WrongFieldValueDuringScriptExecutionException wrongFieldValueDuringScriptExecutionException)
            {
                this.outputStream.println("Wrong field value during script execution");
                return null;
            }
        }
        return object;
    }










}