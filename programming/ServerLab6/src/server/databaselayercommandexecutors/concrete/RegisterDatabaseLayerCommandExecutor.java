package server.databaselayercommandexecutors.concrete;


import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RegisterCommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;
import server.databaselayercommandexecutors.DatabaseLayerCommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDatabaseLayerCommandExecutor  extends DatabaseLayerCommandExecutor {

    public RegisterDatabaseLayerCommandExecutor(Connection connection)
    {
        super(connection);
    }

    @Override
    public CommandDTOAfterDatabaseWrapper execute(CommandDTO commandDTO) {
        CommandDTOWrapper wrapper =(CommandDTOWrapper) commandDTO;

        boolean registered = true;

        try{
            addUser(wrapper.getUsername(), wrapper.getPwd());
        }catch (SQLException sqlException)
        {
            registered = false;
        }
        return new CommandDTOAfterDatabaseWrapper(commandDTO, registered);
    }

    private boolean addUser(String username, String password) throws SQLException {
        String query = "INSERT INTO Users (name, password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
    }

}