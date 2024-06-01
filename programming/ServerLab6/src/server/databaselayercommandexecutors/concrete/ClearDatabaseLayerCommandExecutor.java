package server.databaselayercommandexecutors.concrete;

import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.AddCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.ClearCommandExecutionResultDTO;
import server.CollectionManager;
import server.commandexecutors.CommandExecutor;
import server.databaselayercommandexecutors.DatabaseLayerCommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;
import server.util.wrappers.ClearCommandDTOAfterDatabaseWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClearDatabaseLayerCommandExecutor extends DatabaseLayerCommandExecutor {


    public ClearDatabaseLayerCommandExecutor(Connection connection)
    {
        super(connection);
    }

    @Override
    public CommandDTOAfterDatabaseWrapper execute(CommandDTO commandDTO) {
        CommandDTO simpleDTO = ((CommandDTOWrapper) commandDTO).getCommandDTO();
        Integer userId = this.authorize((CommandDTOWrapper) commandDTO);
        if (userId == null)
            return new CommandDTOAfterDatabaseWrapper(simpleDTO,  false,
                    "Пользователь не зарегистрирован в базе данных");


        try {

            int[]  ids =   this.getLabWorkIdsByCreatorId(userId);

            deleteLabWorkCoordinatesByLabWorkIds(ids);
            deleteLabWorksByCreatorId(userId);
          return new ClearCommandDTOAfterDatabaseWrapper(
                  simpleDTO,true, "" +
                  "удалены все лабораторные работы пользователя. чей id = " + userId,
                ids
          );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Метод для получения массива идентификаторов LabWork по creator_id
    public int[] getLabWorkIdsByCreatorId(int creatorId) throws SQLException {
        String query = "SELECT id FROM LabWork WHERE creator_id = ?";
        List<Integer> labWorkIds = new ArrayList<>();

             PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, creatorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    labWorkIds.add(resultSet.getInt("id"));
                }
            }

        // Преобразование списка в массив int
        return labWorkIds.stream().mapToInt(Integer::intValue).toArray();
    }


    public boolean deleteLabWorksByCreatorId(int creatorId) throws SQLException {
        String query = "DELETE FROM LabWork WHERE creator_id = ?";

             PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, creatorId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
    }



    public boolean deleteLabWorkCoordinatesByLabWorkIds(int[] labWorkIds) throws SQLException {
        if (labWorkIds == null || labWorkIds.length == 0) {
            return false; // Если массив пустой, возвращаем false
        }

        StringBuilder queryBuilder = new StringBuilder("DELETE FROM LabWork_Coordinates WHERE labwork_id IN (");
        for (int i = 0; i < labWorkIds.length; i++) {
            queryBuilder.append("?");
            if (i < labWorkIds.length - 1) {
                queryBuilder.append(",");
            }
        }
        queryBuilder.append(")");

             PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());

            for (int i = 0; i < labWorkIds.length; i++) {
                preparedStatement.setInt(i + 1, labWorkIds[i]);
            }

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
    }








}

