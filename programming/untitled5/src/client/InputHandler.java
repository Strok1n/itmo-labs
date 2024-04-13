package client;

import client.commandpreparers.AddCommandPreparer;
import client.commandpreparers.CommandPreparer;
import client.commandpreparers.HelpCommandPreparer;
import client.commandpreparers.InfoCommandPreparer;
import client.serverresponsehandler.AddCommandServerResponseHandler;
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
    private final Map<String, CommandPreparer> map;

    private final Map<String, ServerResponseHandler> serverResponseHandlerMap;


    public InputHandler(ConsoleReader consoleReader, ConsoleWriter consoleWriter)
    {
        map = new HashMap<>();
        map.put("help", new HelpCommandPreparer());
        map.put("add", new AddCommandPreparer(consoleReader, consoleWriter));
        map.put("info", new InfoCommandPreparer());


        serverResponseHandlerMap = new HashMap<>();
        serverResponseHandlerMap.put("info", new InfoCommandServerResponseHandler());
        serverResponseHandlerMap.put("add", new AddCommandServerResponseHandler());

    }


    public String handleInput(String input)
    {
        String[] tokens = input.split(" ");
        String commandName = tokens[0];
        String[] arguments = Arrays.copyOfRange(tokens, 1, tokens.length);

        CommandDTO commandDTO = map.get(commandName).prepareCommand(arguments);

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
