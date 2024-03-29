package util;

import exceptions.OutputTextException;
import exceptions.WrongFieldValueDuringScriptExecutionException;
import trash.CommandExecutionMode;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class ObjectCreator
{
    private final Class<?> objectClass;
    private PrintStream outputStream;
    private Scanner scanner;
    private CommandExecutionMode objectCreationMode;
    private InputValidator inputValidator;

    public void setObjectCreationMode(CommandExecutionMode objectCreationMode) {
        this.objectCreationMode = objectCreationMode;
    }

    public void setIOStreams(Scanner scanner, PrintStream printStream)
    {
        this.scanner = scanner;
        this.outputStream = printStream;
    }

    public ObjectCreator(Class<?> objectClass, InputValidator inputValidator, PrintStream outputStream, Scanner scanner, CommandExecutionMode objectCreationMode)
    {
        this.objectClass = objectClass;
        this.inputValidator = inputValidator;
        this.outputStream = outputStream;
        this.scanner = scanner;
        this.objectCreationMode = objectCreationMode;
    }


    private void handleWrongFieldValue(Field field)
    {
        if (this.objectCreationMode == CommandExecutionMode.FROM_FILE)
            throw new WrongFieldValueDuringScriptExecutionException();
        this.outputStream.println(this.inputValidator.getInvalidInputMessage(field));
    }

    private void setFieldValueToTheObject(Object object, Field field) throws IllegalAccessException, OutputTextException {
        if(this.inputValidator.isFieldAutogenerated(field))
            return;
        String input;
        while (true)
        {
//            if (this.objectCreationMode == CommandExecutionMode.MANUAL_INPUT)
//                this.outputStream.println("Enter the " + field.getName() + " of the " + field.getDeclaringClass().getSimpleName() + ":" );
            if (this.objectCreationMode == CommandExecutionMode.MANUAL_INPUT)
                throw new OutputTextException("Enter the " + field.getName() + " of the " + field.getDeclaringClass().getSimpleName() + ":");


            if (TypeUtilities.isPrimitiveOrWrapperOrString(field.getType()) || field.getType().isEnum() )
            {
                input = this.scanner.nextLine();
                System.out.println(input);
                Object value;
                try
                {
                    value = TypeUtilities.StringToSpecifiedFieldType(input, field);
                }
                catch (Exception e)
                {
                    handleWrongFieldValue(field);
                    continue;
                }
                if (!this.inputValidator.validateInput(field, input))
                {
                    handleWrongFieldValue(field);
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
                    this.setFieldValueToTheObject(field.get(object), f);
                    field.setAccessible(false);
                }
                return;
            }
        }


    }



    public Object createObject() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, OutputTextException {
        Object object = objectClass.getDeclaredConstructor().newInstance();
        for (Field field : objectClass.getDeclaredFields())
        {
            try
            {
                this.setFieldValueToTheObject(object, field);
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