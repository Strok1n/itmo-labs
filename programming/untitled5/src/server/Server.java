package server;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import server.commandexecutors.*;
import server.commandexecutors.concrete.*;
import server.util.ServerInitializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Server {
    public static Server serverEntryPoint = new Server();

    final private CollectionManager collectionManager;
    final private Map<String, CommandExecutor> commandExecutors;

    public Server()
    {
        try {
            this.collectionManager = ServerInitializer.initializerCollectionManager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.commandExecutors = ServerInitializer.initializeCommandExecutors(this.collectionManager);
    }

    public CommandExecutionResultDTO response(CommandDTO commandDTO)
    {
        return this.commandExecutors.get(commandDTO.getCommandName()).execute(commandDTO);
    }



}
