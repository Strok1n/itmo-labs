package server.databaselayercommandexecutors;

import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import server.util.CommandDTOAfterDatabaseWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseLayerCommandExecutor {

    protected final Connection connection;

    public DatabaseLayerCommandExecutor(Connection connection)
    {
        this.connection = connection;
    }
    abstract public CommandDTOAfterDatabaseWrapper execute(CommandDTO commandDTO);

    protected Integer authorize(CommandDTOWrapper commandDTOWrapper)
    {
        try
        {
            return getUserIdByUsernameAndPassword(commandDTOWrapper.getUsername(), commandDTOWrapper.getPwd());
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private Integer getUserIdByUsernameAndPassword(String username, String password) throws SQLException {
        String query = "SELECT id FROM Users WHERE name = ? AND password = ?";
             PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        return null; // Возвращаем null, если пользователь не найден
    }

}
