package client;

import client.commanddtobuilders.*;
import client.serverresponsehandler.AddCommandServerResponseHandler;
import client.serverresponsehandler.ExecuteScriptCommandServerResponseHandler;
import client.serverresponsehandler.InfoCommandServerResponseHandler;
import client.serverresponsehandler.ServerResponseHandler;
import contract.command.CommandDTO;
import contract.commandexecutionresult.CommandExecutionResultDTO;
import server.Server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InputHandler
{
    private final Map<String, CommandDTOBuilder> commandDTOBuilders;

    private final Map<String, ServerResponseHandler> serverResponseHandlerMap;


    public InputHandler(ConsoleReader consoleReader, ConsoleWriter consoleWriter)
    {
        commandDTOBuilders = new HashMap<>();
        commandDTOBuilders.put("help", new HelpCommandDTOBuilder());
        commandDTOBuilders.put("add", new AddCommandDTOBuilder(consoleReader, consoleWriter));
        commandDTOBuilders.put("info", new InfoCommandDTOBuilder());

        commandDTOBuilders.put("execute_script", new ExecuteScriptCommandDTOBuilder(
                this.commandDTOBuilders
        ));



        serverResponseHandlerMap = new HashMap<>();
        serverResponseHandlerMap.put("info", new InfoCommandServerResponseHandler());
        serverResponseHandlerMap.put("add", new AddCommandServerResponseHandler());
        serverResponseHandlerMap.put("execute_script",
                new ExecuteScriptCommandServerResponseHandler(this.serverResponseHandlerMap));


    }


    public String handleInput(String input)
    {
        String[] tokens = input.split(" ");
        String commandName = tokens[0];
        String[] arguments = Arrays.copyOfRange(tokens, 1, tokens.length);

        CommandDTO commandDTO = commandDTOBuilders.get(commandName).buildCommandDTO(arguments);




        CommandExecutionResultDTO commandExecutionResultDTO =
                Server.serverEntryPoint.response(commandDTO);


        return this.handleServerResponse(commandExecutionResultDTO);
    }

    private String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO)
    {
        return this.serverResponseHandlerMap.get(commandExecutionResultDTO.getCommandName())
                .handleServerResponse(commandExecutionResultDTO);
    }


}
