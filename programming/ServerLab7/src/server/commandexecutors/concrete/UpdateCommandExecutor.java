package server.commandexecutors.concrete;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.UpdateCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.UpdateCommandExecutionResultDTO;
import server.CollectionManager;
import server.business.*;
import server.commandexecutors.CommandExecutor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static server.Server.connection;

public class UpdateCommandExecutor extends CommandExecutor {

    final private CollectionManager collectionManager;


    public UpdateCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO){
        UpdateCommandDTO updateCommandDTO = (UpdateCommandDTO) commandDTO;

        int labId  = 0;
        int coordId  = 0;
        int disciplId = 0;





        try {
            PreparedStatement ps1 = connection.prepareStatement( "SELECT id\n" +
                    "FROM Coordinates\n" +
                    "WHERE Coordinates.x = ? AND Coordinates.y = ?;");
            ps1.setInt(1, updateCommandDTO.getLabWorkCoordinatesX());
            ps1.setInt(2, updateCommandDTO.getLabWorkCoordinatesY());
            ResultSet set1 = ps1.executeQuery();
            int coordinatesID = -1;
            if (!set1.isBeforeFirst())
            {
                PreparedStatement ps2 = connection.prepareStatement("INSERT INTO Coordinates (x, y) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
                ps2.setInt(1, updateCommandDTO.getLabWorkCoordinatesX());
                ps2.setInt(2, updateCommandDTO.getLabWorkCoordinatesY());
                ps2.execute();
                ResultSet set2 = ps2.getGeneratedKeys();
                set2.next();
                coordinatesID = set2.getInt(1);
            }else {
                set1.next();
                coordinatesID = set1.getInt(1);
            }


            PreparedStatement ps3 = connection.prepareStatement( "SELECT id\n" +
                    "FROM Discipline\n" +
                    "WHERE Discipline.name = ? AND Discipline.labsCount = ?;");
            ps3.setString(1, updateCommandDTO.getDisciplineName());
            ps3.setInt(2, updateCommandDTO.getDisciplineLabsCount());
            ResultSet set3 = ps3.executeQuery();
            int disciplineID = -1;
            if (!set1.isBeforeFirst())
            {
                PreparedStatement ps4 = connection.prepareStatement("INSERT INTO Discipline (name, labsCount) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
                ps4.setString(1, updateCommandDTO.getDisciplineName());
                ps4.setInt(2, updateCommandDTO.getDisciplineLabsCount());
                ps4.execute();
                ResultSet set4 = ps4.getGeneratedKeys();
                set4.next();
                disciplineID = set4.getInt(1);
            }else {
                set3.next();
                disciplineID = set3.getInt(1);
            }

            PreparedStatement ps6 = connection.prepareStatement( "SELECT id\n" +
                    "FROM User\n" +
                    "WHERE User.name = ? AND User.password = ?;");
            ps6.setString(1, this.userName);
            ps6.setString(2, this.pwd);

            ResultSet set6 = ps6.executeQuery();
            set6.next();




            int userId = set6.getInt(1);

            if (userId != getUserIdOfLabWorkByLabworkId(updateCommandDTO.getId())) {



                return new CommandExecutionResultDTOWrapper(
                        new UpdateCommandExecutionResultDTO(false, updateCommandDTO.getId()), false);
            }

            PreparedStatement ps5 = connection.prepareStatement(
                    "UPDATE LabWork SET name = ?, coordinates_id = ?, minimalPoint = ?, tunedInWorks = ?, difficulty = ?, discipline_id = ?, creator_id = ? WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            ps5.setString(1, updateCommandDTO.getLabWorkName());
            ps5.setInt(2, coordinatesID);
            ps5.setDouble(3, updateCommandDTO.getMinimalPoint());
            ps5.setLong(4, updateCommandDTO.getTunedInWorks());
            ps5.setString(5, updateCommandDTO.getDifficulty());

            ps5.setInt(6, disciplineID);
            ps5.setInt(7, userId);
            ps5.setInt(8, updateCommandDTO.getId());
            ps5.execute();
            ResultSet set5 = ps5.getGeneratedKeys();
            set5.next();

            //System.out.println("lab id");
          //  System.out.println(set5.getInt(1));

          //  labId = set5.getInt(1);
            coordId = coordinatesID;
            disciplId = disciplineID;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (this.collectionManager.removeById(updateCommandDTO.getId()))
        {
            LabWork labWork = new LabWork(
                    updateCommandDTO.getId(),
                    updateCommandDTO.getLabWorkName(),
                    new Coordinates(updateCommandDTO.getLabWorkCoordinatesX(),
                            updateCommandDTO.getLabWorkCoordinatesY()),
                    LocalDate.now(),
                    updateCommandDTO.getMinimalPoint(),
                    updateCommandDTO.getTunedInWorks(),
                    Difficulty.valueOf(updateCommandDTO.getDifficulty()),
                    new Discipline(updateCommandDTO.getDisciplineName(),
                            updateCommandDTO.getDisciplineLabsCount())
            );
            labWork.setCoordinatesId(coordId);
            labWork.setDisciplineId(disciplId);
            labWork.setUser(new User(
                    userName,
                    pwd)
            );

            return new CommandExecutionResultDTOWrapper(
                    new UpdateCommandExecutionResultDTO(
                            this.collectionManager.addLabWorkToTheCollection(labWork), updateCommandDTO.getId()), true);}
        else
            return new CommandExecutionResultDTOWrapper(
                    new UpdateCommandExecutionResultDTO(false, updateCommandDTO.getId()), false);
    }












    private static int getUserIdOfLabWorkByLabworkId(int id) throws SQLException {
        LabWork labWork = null;
        String query = "SELECT * FROM LabWork WHERE id = ?";
        int id1 = 0;
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                id1 = resultSet.getInt("creator_id");
            }
        }
        return id1;
    }











}
