package server.databaselayercommandexecutors.concrete;

import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.RemoveByIdCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RemoveByIdCommandExecutionResultDTO;
import server.CollectionManager;
import server.commandexecutors.CommandExecutor;
import server.databaselayercommandexecutors.DatabaseLayerCommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveByIdDatabaseLayerCommandExecutor  extends DatabaseLayerCommandExecutor {
    public RemoveByIdDatabaseLayerCommandExecutor(Connection connection)
    {
        super(connection);
    }

    @Override
    public CommandDTOAfterDatabaseWrapper execute(CommandDTO commandDTO)
    {
        RemoveByIdCommandDTO simpleDTO = (RemoveByIdCommandDTO) ((CommandDTOWrapper) commandDTO).getCommandDTO();
        Integer userId = this.authorize((CommandDTOWrapper) commandDTO);
        if (userId == null)
            return new CommandDTOAfterDatabaseWrapper(simpleDTO,  false,
                    "Пользователь не зарегистрирован в базе данных");

        boolean deletionDone;




        try {

            if (getLabWorkIdByIdAndCreatorId(simpleDTO.getId(),userId ) != null)
            {
                deleteLabWorkCoordinatesByLabWorkId(simpleDTO.getId());
                deletionDone =  deleteLabWorkByIdAndCreatorId(simpleDTO.getId(), userId);
            }else
                deletionDone = false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String message;

        if (deletionDone)
            message = "Удаление по id выполнено";
        else
            message = "Удаление по id не выполнено";

        return new CommandDTOAfterDatabaseWrapper(simpleDTO,
                deletionDone,message );
    }

 public boolean deleteLabWorkByIdAndCreatorId(int labWorkId, int creatorId) throws SQLException {
        String query = "DELETE FROM LabWork WHERE id = ? AND creator_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, labWorkId);
            preparedStatement.setInt(2, creatorId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
    }


    public Integer getLabWorkIdByIdAndCreatorId(int labWorkId, int creatorId) throws SQLException {
        String query = "SELECT id FROM LabWork WHERE id = ? AND creator_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, labWorkId);
        preparedStatement.setInt(2, creatorId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        }
        return null;
    }


    public boolean deleteLabWorkCoordinatesByLabWorkId(int labWorkId) throws SQLException {
        String query = "DELETE FROM LabWork_Coordinates WHERE labwork_id = ?";


             PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, labWorkId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
    }



}