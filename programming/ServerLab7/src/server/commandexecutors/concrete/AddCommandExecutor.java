package server.commandexecutors.concrete;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.AddCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import server.CollectionManager;
import server.Server;
import server.business.*;
import server.commandexecutors.CommandExecutor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static server.Server.connection;

public class AddCommandExecutor extends CommandExecutor {

    final private CollectionManager collectionManager;

    public AddCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }


    @Override
    public CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO){
        AddCommandDTO addCommandDTO = (AddCommandDTO) commandDTO;

        int labId  = 0;
        int coordId  = 0;
        int disciplId = 0;

        try {
            PreparedStatement ps1 = connection.prepareStatement( "SELECT id\n" +
                    "FROM Coordinates\n" +
                    "WHERE Coordinates.x = ? AND Coordinates.y = ?;");
            ps1.setInt(1, addCommandDTO.getLabWorkCoordinatesX());
            ps1.setInt(2, addCommandDTO.getLabWorkCoordinatesY());
            ResultSet set1 = ps1.executeQuery();
            int coordinatesID = -1;
            if (!set1.isBeforeFirst())
            {
                PreparedStatement ps2 = connection.prepareStatement("INSERT INTO Coordinates (x, y) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
                ps2.setInt(1, addCommandDTO.getLabWorkCoordinatesX());
                ps2.setInt(2, addCommandDTO.getLabWorkCoordinatesY());
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
            ps3.setString(1, addCommandDTO.getDisciplineName());
            ps3.setInt(2, addCommandDTO.getDisciplineLabsCount());
            ResultSet set3 = ps3.executeQuery();
            int disciplineID = -1;
            if (!set1.isBeforeFirst())
            {
                PreparedStatement ps4 = connection.prepareStatement("INSERT INTO Discipline (name, labsCount) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
                ps4.setString(1, addCommandDTO.getDisciplineName());
                ps4.setInt(2, addCommandDTO.getDisciplineLabsCount());
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


            PreparedStatement ps5 = connection.prepareStatement(
                    "INSERT INTO LabWork (name, coordinates_id, minimalPoint, tunedInWorks, difficulty, discipline_id, creator_id) \n" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            ps5.setString(1, addCommandDTO.getLabWorkName());
            ps5.setInt(2, coordinatesID);
            ps5.setDouble(3, addCommandDTO.getMinimalPoint());
            ps5.setLong(4, addCommandDTO.getTunedInWorks());
            ps5.setString(5, addCommandDTO.getDifficulty());

            ps5.setString(5, addCommandDTO.getDifficulty());
            ps5.setInt(6, disciplineID);
            ps5.setInt(7, userId);

            ps5.execute();
            ResultSet set5 = ps5.getGeneratedKeys();
            set5.next();
            System.out.println("lab id");
            System.out.println(set5.getInt(1));
             labId = set5.getInt(1);
             coordId = coordinatesID;
             disciplId = disciplineID;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        LabWork labWork = new LabWork(
                labId,
                addCommandDTO.getLabWorkName(),
                new Coordinates(addCommandDTO.getLabWorkCoordinatesX(),
                        addCommandDTO.getLabWorkCoordinatesY()),
                LocalDate.now(),
                addCommandDTO.getMinimalPoint(),
                addCommandDTO.getTunedInWorks(),
                Difficulty.valueOf(addCommandDTO.getDifficulty()),
                new Discipline(addCommandDTO.getDisciplineName(),
                        addCommandDTO.getDisciplineLabsCount())
        );

        labWork.setUser(new User(userName, pwd));

        boolean flag = this.collectionManager.addLabWorkToTheCollection(labWork);
        String commandExecutionResultMessage;
        if (flag)
            commandExecutionResultMessage = "Лабораторная работа успешно добавлена в коллекцию";
        else
            commandExecutionResultMessage = "Лабораторная работа не добавлена в коллекцию";
        return new CommandExecutionResultDTOWrapper(new AddCommandExecutionResultDTO(commandExecutionResultMessage), true);
    }
}
