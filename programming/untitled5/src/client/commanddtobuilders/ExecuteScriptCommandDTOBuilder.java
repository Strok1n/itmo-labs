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
                     new FileInputStream("C:\\file.txt"));
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

                System.out.println(fileStrings[
                        stringIterator.getI()
                        ]);

            commandDTOList.add(this.commandDTOBuilders.get(fileStrings[
                    stringIterator.getI()
                    ].trim()).buildCommandDTOFromScript(fileStrings, stringIterator));

            }

        }catch (InvalidCommandArgumentsInScriptFileException invalidCommandArgumentsInScriptFileException)
        {
            return null;
        }

        return new ExecuteScriptCommandDTO(commandDTOList);
    }

    @Override
    public CommandDTO buildCommandDTOFromScript(String[] fileStrings, StringIterator stringIterator)
    {

        Scanner fileScanner;
        BufferedInputStream fileInputStream;
        try {
            fileInputStream = new BufferedInputStream(
                    new FileInputStream("C:\\file.txt"));
            String file = new String(fileInputStream.readAllBytes(), StandardCharsets.UTF_8);
            fileStrings = file.split("\\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        List<CommandDTO> commandDTOList = new ArrayList<>();

        try {

            for ( ;
                 stringIterator.getI() < fileStrings.length ; stringIterator.increment()) {

                System.out.println(fileStrings[
                        stringIterator.getI()
                        ]);

                commandDTOList.add(this.commandDTOBuilders.get(fileStrings[
                        stringIterator.getI()
                        ].trim()).buildCommandDTOFromScript(fileStrings, stringIterator));

            }
        }catch (InvalidCommandArgumentsInScriptFileException invalidCommandArgumentsInScriptFileException)
        {
            return null;
        }

        return new ExecuteScriptCommandDTO(commandDTOList);
    }
}