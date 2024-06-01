package server;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.SaveCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;
import server.databaselayercommandexecutors.DatabaseLayerCommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;
import server.util.ServerInitializer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class Server {
    public static Server server = new Server();

    final private CollectionManager collectionManager;
    final private Map<String, CommandExecutor> commandExecutors;
    final private Map<String, DatabaseLayerCommandExecutor> databaseLayerCommandExecutorMap;

    public Server()
    {
        try {
            Connection connection;
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/mydb",
                        "1234"
                        ,"1234"
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("################");
            this.collectionManager = ServerInitializer.initializerCollectionManager(connection);
            System.out.println("################");
            this.databaseLayerCommandExecutorMap = ServerInitializer.initializeDatabaseLayerCommandExecutors(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.commandExecutors = ServerInitializer.initializeCommandExecutors(this.collectionManager);
    }

    public CommandExecutionResultDTO response(CommandDTO commandDTO)
    {
        CommandDTOAfterDatabaseWrapper commandDTOAfterDatabaseWrapper =
                this.databaseLayerCommandExecutorMap
                        .get(commandDTO.getCommandName()).execute(commandDTO);

      //  commandDTO = commandDTOAfterDatabaseWrapper.getCommandDTO();

        System.out.println(commandDTO.getCommandName());
        System.out.println(this.commandExecutors.get(commandDTO.getCommandName()));
        return this.commandExecutors.get(commandDTO.getCommandName()).execute(
                commandDTOAfterDatabaseWrapper);
    }


    public void save()
    {
        this.commandExecutors.get("save").execute(new SaveCommandDTO());
    }

}
