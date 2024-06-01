package server.databaselayercommandexecutors.concrete;

import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.AddCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.Coordinates;
import server.business.Difficulty;
import server.business.Discipline;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;
import server.databaselayercommandexecutors.DatabaseLayerCommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;
import server.util.wrappers.AddCommandDTOAfterDatabaseWrapper;

import java.sql.*;
import java.time.LocalDate;

public class AddDatabaseLayerCommandExecutor extends DatabaseLayerCommandExecutor {

    public AddDatabaseLayerCommandExecutor(Connection connection)
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


        AddCommandDTO addCommandDTO = (AddCommandDTO) simpleDTO;

        int id = addLabWork(
                addCommandDTO.getLabWorkName(),
                addCommandDTO.getLabWorkCoordinatesX(),
                addCommandDTO.getLabWorkCoordinatesY(),
                addCommandDTO.getMinimalPoint(),
                addCommandDTO.getTunedInWorks(),
                addCommandDTO.getDifficulty(),
                addCommandDTO.getDisciplineName(),
                addCommandDTO.getDisciplineLabsCount(),
                userId
        );
        return new AddCommandDTOAfterDatabaseWrapper(simpleDTO, true,
                "Лабораторная работа добавлена в базу данных", id);
    }

    public Integer addLabWork(String name, int x, int y, double minimalPoint, long tunedInWorks, String difficultyValue, String disciplineName, int labsCount, int creatorId) {
        String checkCoordinatesQuery = "SELECT id FROM Coordinates WHERE x = ? AND y = ?";
        String insertCoordinatesQuery = "INSERT INTO Coordinates (x, y) VALUES (?, ?)";
        String checkDisciplineQuery = "SELECT id FROM Discipline WHERE name = ?";
        String insertDisciplineQuery = "INSERT INTO Discipline (name, labsCount) VALUES (?, ?)";
        String insertLabWorkQuery = "INSERT INTO LabWork (name, creationDate, minimalPoint, tunedInWorks, difficulty, discipline_id, creator_id) VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";


        try {
            connection.setAutoCommit(false);
            // Начало транзакции
            // Проверка наличия координат
            int coordinatesId = getOrInsertCoordinates(checkCoordinatesQuery, insertCoordinatesQuery, x, y);
            // Проверка наличия дисциплины
            int disciplineId = getOrInsertDiscipline(checkDisciplineQuery, insertDisciplineQuery, disciplineName, labsCount);


            PreparedStatement preparedStatement = connection.prepareStatement(insertLabWorkQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, minimalPoint);
            preparedStatement.setLong(3, tunedInWorks);
            preparedStatement.setString(4, difficultyValue);
            preparedStatement.setInt(5, disciplineId);
            preparedStatement.setInt(6, creatorId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int labWorkId = generatedKeys.getInt(1);

                        addLabWorkCoordinates(labWorkId, coordinatesId);
                        connection.commit(); // Завершение транзакции


                        return labWorkId;
                    }
                }
            } else {
                connection.rollback(); // Откат транзакции в случае неудачи
            }
            return null; // Возвращаем null, если вставка не удалась

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private int getOrInsertCoordinates(String checkQuery, String insertQuery, int x, int y) throws SQLException, SQLException {
        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, x);
            checkStatement.setInt(2, y);
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
        }
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStatement.setInt(1, x);
            insertStatement.setInt(2, y);
            insertStatement.executeUpdate();
            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        throw new SQLException("Failed to insert or retrieve coordinates.");
    }

    private int getOrInsertDiscipline(String checkQuery, String insertQuery, String name, int labsCount) throws SQLException {
        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
            checkStatement.setString(1, name);
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        }
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStatement.setString(1, name);
            insertStatement.setInt(2, labsCount);
            insertStatement.executeUpdate();
            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        throw new SQLException("Failed to insert or retrieve discipline.");
    }


    private boolean addLabWorkCoordinates(int labWorkId, int coordinatesId) throws SQLException {
        String query = "INSERT INTO LabWork_Coordinates (labwork_id, coordinates_id) VALUES (?, ?)";

             PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, labWorkId);
            preparedStatement.setInt(2, coordinatesId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
    }


}
