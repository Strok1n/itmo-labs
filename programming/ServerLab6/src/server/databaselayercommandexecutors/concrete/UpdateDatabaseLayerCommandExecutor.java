package server.databaselayercommandexecutors.concrete;

import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.AddCommandDTO;
import contract.dto.commanddto.concrete.UpdateCommandDTO;
import server.databaselayercommandexecutors.DatabaseLayerCommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;
import server.util.wrappers.AddCommandDTOAfterDatabaseWrapper;

import java.sql.*;

public class UpdateDatabaseLayerCommandExecutor extends DatabaseLayerCommandExecutor {


    public UpdateDatabaseLayerCommandExecutor(Connection connection)
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


        UpdateCommandDTO updateCommandDTO = (UpdateCommandDTO) simpleDTO;




        try {
            if (getLabWorkIdByIdAndCreatorId(updateCommandDTO.getId(), userId) ==null){
                return new CommandDTOAfterDatabaseWrapper(simpleDTO, false,
                        "Пользователь не имеет доступа к данной лабораторной работе");
            }

             updateLabWork(
                    updateCommandDTO.getId(),
                    updateCommandDTO.getLabWorkName(),
                    updateCommandDTO.getLabWorkCoordinatesX(),
                    updateCommandDTO.getLabWorkCoordinatesY(),
                    updateCommandDTO.getMinimalPoint(),
                    updateCommandDTO.getTunedInWorks(),
                    updateCommandDTO.getDifficulty(),
                    updateCommandDTO.getDisciplineName(),
                    updateCommandDTO.getDisciplineLabsCount()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new CommandDTOAfterDatabaseWrapper(simpleDTO, true,
                "Лабораторная работа обновлена");
    }

    public void updateLabWork(int id, String labWorkName, int labWorkCoordinatesX, Integer labWorkCoordinatesY,
                              Double minimalPoint, long tunedInWorks, String difficulty,
                              String disciplineName, int disciplineLabsCount) throws SQLException {

            int coordinatesId = getOrCreateCoordinatesId( labWorkCoordinatesX, labWorkCoordinatesY);
            int disciplineId = getOrCreateDisciplineId( disciplineName, disciplineLabsCount);

            String query = "UPDATE LabWork SET name = ?, minimalPoint = ?, tunedInWorks = ?, "
                    + "difficulty = ?, discipline_id = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, labWorkName);
                preparedStatement.setDouble(2, minimalPoint);
                preparedStatement.setLong(3, tunedInWorks);
                preparedStatement.setString(4, difficulty);
                preparedStatement.setInt(5, disciplineId);
                preparedStatement.setInt(6, id);
                preparedStatement.executeUpdate();
            }

            updateLabWorkCoordinates( id, coordinatesId);

    }

    private int getOrCreateCoordinatesId( int x, Integer y) throws SQLException {
        String query = "SELECT id FROM Coordinates WHERE x = ? AND y = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, x);
            preparedStatement.setInt(2, y);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return createCoordinates( x, y);
            }
        }
    }

    private int createCoordinates( int x, Integer y) throws SQLException {
        String query = "INSERT INTO Coordinates (x, y) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, x);
            preparedStatement.setInt(2, y);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating Coordinates failed, no ID obtained.");
            }
        }
    }

    private int getOrCreateDisciplineId( String name, int labsCount) throws SQLException {
        String query = "SELECT id FROM Discipline WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return createDiscipline( name, labsCount);
            }
        }
    }

    private int createDiscipline( String name, int labsCount) throws SQLException {
        String query = "INSERT INTO Discipline (name, labsCount) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, labsCount);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating Discipline failed, no ID obtained.");
            }
        }
    }

    private void updateLabWorkCoordinates( int labWorkId, int coordinatesId) throws SQLException {
        // Удаляем старые записи
        String deleteQuery = "DELETE FROM Labwork_Coordinates WHERE labwork_id = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
            deleteStatement.setInt(1, labWorkId);
            deleteStatement.executeUpdate();
        }

        // Добавляем новую запись
        String insertQuery = "INSERT INTO Labwork_Coordinates (labwork_id, coordinates_id) VALUES (?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setInt(1, labWorkId);
            insertStatement.setInt(2, coordinatesId);
            insertStatement.executeUpdate();
        }
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






}