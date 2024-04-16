package client.commanddtobuilders;

import client.exceptions.InvalidCommandArgumentsInScriptFileException;
import client.util.StringIterator;
import contract.command.CommandDTO;
import contract.command.ExecuteScriptCommandDTO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ExecuteScriptCommandDTOBuilder implements CommandDTOBuilder {

    private final Map<String, CommandDTOBuilder> commandDTOBuilders;

    public ExecuteScriptCommandDTOBuilder(Map<String, CommandDTOBuilder> commandDTOBuilders){
        this.commandDTOBuilders = commandDTOBuilders;
    }


    @Override
    public CommandDTO buildCommandDTO(String[] commandArguments)
    {

        Scanner fileScanner;
        BufferedInputStream fileInputStream;
        String[] fileStrings;
        try {
             fileInputStream = new BufferedInputStream(
                     new FileInputStream(commandArguments[0]));
             String file = new String(fileInputStream.readAllBytes(), StandardCharsets.UTF_8);
             fileStrings = file.split("\\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        List<CommandDTO> commandDTOList = new ArrayList<>();

        try {

            for (StringIterator stringIterator = new StringIterator();
             stringIterator.getI() < fileStrings.length ; stringIterator.increment()) {



                String[] tokens = fileStrings[stringIterator.getI()].split(" ");
                String commandName = tokens[0];
                String[] arguments = Arrays.copyOfRange(tokens, 1, tokens.length);

                if (arguments.length == 0)
                {
                    arguments = new String[1];
                    arguments[0] = "dsf";
                }

            commandDTOList.add(this.commandDTOBuilders.get(
                    commandName.trim()).buildCommandDTOFromScript(fileStrings, stringIterator, arguments[0] ));

            }

        }catch (InvalidCommandArgumentsInScriptFileException invalidCommandArgumentsInScriptFileException)
        {
            return null;
        }

        return new ExecuteScriptCommandDTO(commandDTOList);
    }

    @Override
    public CommandDTO buildCommandDTOFromScript(String[] fileStrings, StringIterator stringIterator, String commandArgument)
    {

        Scanner fileScanner;
        BufferedInputStream fileInputStream;
        try {


            fileInputStream = new BufferedInputStream(
                    new FileInputStream(commandArgument.trim()));
            String file = new String(fileInputStream.readAllBytes(), StandardCharsets.UTF_8);
            fileStrings = file.split("\\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        List<CommandDTO> commandDTOList = new ArrayList<>();

        try {

            for (StringIterator stringIterator1 = new StringIterator();
                 stringIterator1.getI() < fileStrings.length ; stringIterator1.increment()) {


                String[] tokens = fileStrings[stringIterator1.getI()].split(" ");
                String commandName = tokens[0];
                String[] arguments = Arrays.copyOfRange(tokens, 1, tokens.length);


                if (arguments.length == 0)
                {
                    arguments = new String[1];
                    arguments[0] = "dsf";
                }


                commandDTOList.add(this.commandDTOBuilders.get(
                        commandName.trim()
                ).buildCommandDTOFromScript(fileStrings, stringIterator1, arguments[0] ));

            }
        }catch (InvalidCommandArgumentsInScriptFileException invalidCommandArgumentsInScriptFileException)
        {
            return null;
        }

        return new ExecuteScriptCommandDTO(commandDTOList);
    }
}