package exceptions;

import interpreter.commands.Command;
import interpreter.commands.concrete.AddLabWorkToTheCollection;
import trash.CommandExecutionMode;
import util.InputValidator;

public class InsertException extends Exception {

    private Class<?> objectClass;
    private InputValidator inputValidator;
    private CommandExecutionMode objectCreationMode;

    private AddLabWorkToTheCollection addLabWorkToTheCollection;


    public InsertException(Class<?> objectClass, InputValidator inputValidator, CommandExecutionMode objectCreationMode, AddLabWorkToTheCollection addLabWorkToTheCollection)
    {
        this.objectClass = objectClass;
        this.inputValidator = inputValidator;
        this.objectCreationMode = objectCreationMode;
        this.addLabWorkToTheCollection = addLabWorkToTheCollection;
    }


    public CommandExecutionMode getObjectCreationMode() {
        return objectCreationMode;
    }

    public Class<?> getObjectClass() {
        return objectClass;
    }

    public InputValidator getInputValidator() {
        return inputValidator;
    }

    public AddLabWorkToTheCollection getAddLabWorkToTheCollection() {
        return addLabWorkToTheCollection;
    }
}
