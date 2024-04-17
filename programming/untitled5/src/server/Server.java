package server;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import server.commandexecutors.*;
import server.commandexecutors.concrete.*;

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
        this.commandExecutors.put("show", new ShowCommandExecutor(this.collectionManager));
        this.commandExecutors.put("clear", new ClearCommandExecutor(this.collectionManager));
        this.commandExecutors.put("remove_by_id", new RemoveByIdCommandExecutor(this.collectionManager));
        this.commandExecutors.put("update", new UpdateCommandExecutor(this.collectionManager));
        this.commandExecutors.put("save", new SaveCommandExecutor(this.collectionManager));

        this.commandExecutors.put("execute_script", new ExecuteScriptCommandExecutor(
                this.commandExecutors
        ));


        this.commandExecutors.put("help", new HelpCommandExecutor());
    }

    public CommandExecutionResultDTO response(CommandDTO commandDTO)
    {
        return this.commandExecutors.get(commandDTO.getCommandName()).execute(commandDTO);
    }



}
