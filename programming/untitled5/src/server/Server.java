package server;

import contract.command.CommandDTO;
import contract.commandexecutionresult.CommandExecutionResultDTO;
import server.commandexecutors.AddCommandExecutor;
import server.commandexecutors.CommandExecutor;
import server.commandexecutors.InfoCommandExecutor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Server {
    public static Server serverEntryPoint = new Server();

    final private CollectionManager collectionManager;
    final private Map<String, CommandExecutor> commandExecutorMap;

    public Server()
    {
        this.collectionManager = new CollectionManager(new HashSet<>());
        this.commandExecutorMap = new HashMap<>();
        this.commandExecutorMap.put("add", new AddCommandExecutor(this.collectionManager));
        this.commandExecutorMap.put("info", new InfoCommandExecutor(this.collectionManager));
    }

    public CommandExecutionResultDTO response(CommandDTO commandDTO)
    {
        return this.commandExecutorMap.get(commandDTO.getCommandName())
                .execute(commandDTO);
    }



}
