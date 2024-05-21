package server;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.SaveCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;
import server.util.ServerInitializer;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

public class Server {
    public static Server server;// = new Server();

    public static Connection connection;
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
        Server.server = this;
    }

    public CommandExecutionResultDTOWrapper response(CommandDTO commandDTO, String usr, String pwd)
    {
        this.commandExecutors.get(commandDTO.getCommandName()).setUser(usr,pwd);
        return this.commandExecutors.get(commandDTO.getCommandName()).execute(commandDTO);
    }


    public void save()
    {
        this.commandExecutors.get("save").execute(new SaveCommandDTO());
    }

}
