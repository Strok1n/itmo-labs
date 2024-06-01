package server.databaselayercommandexecutors.concrete;

import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.RemoveGreaterCommandDTO;
import contract.dto.commanddto.concrete.RemoveLowerCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RemoveGreaterCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.Coordinates;
import server.business.Difficulty;
import server.business.Discipline;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;
import server.databaselayercommandexecutors.DatabaseLayerCommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RemoveGreaterDatabaseLayerCommandExecutor extends DatabaseLayerCommandExecutor {

    public RemoveGreaterDatabaseLayerCommandExecutor(Connection connection)
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

        //CommandDTOWrapper wrapper = (CommandDTOWrapper) commandDTO;
        RemoveLowerCommandDTO removeLowerCommandDTO = (RemoveLowerCommandDTO)simpleDTO;

        try {
            List<Integer> ids = getLabWorkIdsByNameAndCreatorLessThan(removeLowerCommandDTO.getLabWorkName(), userId);

            if (!ids.isEmpty()) {
                deleteLabWorkCoordinatesByLabWorkIds(ids.toArray(Integer[]::new));

                deleteLabWorksByIds(ids.toArray(Integer[]::new));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new CommandDTOAfterDatabaseWrapper(simpleDTO, true);
    }



    public List<Integer> getLabWorkIdsByNameAndCreatorLessThan(String name, int creatorId) throws SQLException {
        List<Integer> labWorkIds = new ArrayList<>();
        String query = "SELECT id FROM LabWork WHERE name > ? AND creator_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, creatorId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            labWorkIds.add(resultSet.getInt("id"));
        }
        return labWorkIds;
    }

    public boolean deleteLabWorkCoordinatesByLabWorkIds(Integer[] labWorkIds) throws SQLException {
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

    public void deleteLabWorksByIds(Integer[] labWorkIds) throws SQLException {
        String query = "DELETE FROM LabWork WHERE id IN (";
        for (int i = 0; i < labWorkIds.length; i++) {
            query += "?";
            if (i < labWorkIds.length - 1) {
                query += ",";
            }
        }
        query += ")";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < labWorkIds.length; i++) {
            preparedStatement.setInt(i + 1, labWorkIds[i]);
        }
        preparedStatement.executeUpdate();
    }







}