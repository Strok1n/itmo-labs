package server;

import contract.command.CommandDTO;
import contract.commandexecutionresult.CommandExecutionResultDTO;
import server.commandexecutors.AddCommandExecutor;
import server.commandexecutors.CommandExecutor;
import server.commandexecutors.ExecuteScriptCommandExecutor;
import server.commandexecutors.InfoCommandExecutor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Server {
    public static Server serverEntryPoint = new Server();

    final private CollectionManager collectionManager;
    final private Map<String, CommandExecutor> commandExecutors;

    public Server()
    {
        this.collectionManager = new CollectionManager(new HashSet<>());

        this.commandExecutors = new HashMap<>();
        this.commandExecutors.put("add", new AddCommandExecutor(this.collectionManager));
        this.commandExecutors.put("info", new InfoCommandExecutor(this.collectionManager));

        this.commandExecutors.put("execute_script", new ExecuteScriptCommandExecutor(
                this.commandExecutors
        ));
    }

    public CommandExecutionResultDTO response(CommandDTO commandDTO)
    {
        return this.commandExecutors.get(commandDTO.getCommandName())
                .execute(commandDTO);
    }



}
