package server.util;

import org.json.*;
import server.CollectionManager;
import server.business.*;
import server.commandexecutors.CommandExecutor;
import server.commandexecutors.concrete.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import static server.Server.connection;

public class ServerInitializer {


    public static CollectionManager initializerCollectionManager() throws IOException {

//        //String initFileName = System.getenv("file_name");
//      //  String initFileName = "C:\\Users\\1\\Desktop\\scripts\\collection.xml";
//        //String initFileName2 = "C:\\Users\\1\\Desktop\\scripts\\collection.xml";
//        String initFileName2 =
//              Paths.get("").toAbsolutePath().toString()
//        +File.separator    + "collection.xml";
//
////        BufferedInputStream bufferedInputStream = new BufferedInputStream(
////                new FileInputStream(initFileName)
////        );
//        Scanner scanner  = new Scanner(new File(initFileName2));
//    //    String json = new String(bufferedInputStream.readAllBytes(), StandardCharsets.UTF_8);
//        String xml = "";
//        while (scanner.hasNext())
//        {
//            xml = xml.concat( scanner.nextLine());
//        }
//
//        if (xml.isEmpty())
//            return new CollectionManager(new HashSet<>(), LocalDate.now(),
//                    initFileName2);
//
//
//
//
//
//        String initDate2 =  xml.substring(xml.indexOf("<initDate>" )+ "<initDate>".length(), xml
//                .indexOf("</initDate>"));
//
//
//
//
//        xml = xml.substring(xml.indexOf("<LabWorks>") + "<LabWorks>".length());
//
//        xml = xml.substring(0, xml.indexOf("</LabWorks>"));
//
//
//
//
//        if (xml.isEmpty())
//            return new CollectionManager(new HashSet<>(), LocalDate.now(),
//                    initFileName2);
//
//
//        JSONObject xmlJSONObj = XML.toJSONObject(  xml,true);
//
//        JSONArray jsonArray = new JSONArray();
//
//
//
//
//        boolean flag = false;
//        try {
//            JSONArray idArray = xmlJSONObj.getJSONArray("id");
//        }catch (JSONException jsonException)
//        {
//            flag = true;
//        }
//
//        HashSet<LabWork> labWorks2 = new HashSet<>();
//
//
//        if (flag)
//        {
//            labWorks2.add(new LabWork(
//                    Integer.parseInt((String) xmlJSONObj.get("id")),
//                    (String) xmlJSONObj.get("name"),
//                    new Coordinates(
//                            Integer.parseInt((String) xmlJSONObj.getJSONObject("coordinates")
//                                    .get("x")),
//                            Integer.parseInt((String) xmlJSONObj.getJSONObject("coordinates")
//                                    .get("y")))
//                    ,
//                    LocalDate.parse((CharSequence) xmlJSONObj.get("creationDate")),
//                    Double.parseDouble((String) xmlJSONObj.get("minimalPoint")),
//                    Long.parseLong((String) xmlJSONObj.get("tunedInWorks")),
//                    Difficulty.valueOf(xmlJSONObj.get("difficulty").toString()),
//                    new Discipline((String) xmlJSONObj.getJSONObject("discipline")
//                            .get("name"),
//                            Integer.parseInt((String) xmlJSONObj.getJSONObject("discipline")
//                                    .get("labsCount")))));
//
//
//            return new CollectionManager(labWorks2, LocalDate.now(),initFileName2);
//           // if
//           // (initDate2.equals("null"))
//
//        }
//
//
//        JSONArray idArray = xmlJSONObj.getJSONArray("id");
//        JSONArray nameArray = xmlJSONObj.getJSONArray("name");
//        JSONArray coordinatesArray = xmlJSONObj.getJSONArray("coordinates");
//        JSONArray creationDateArray = xmlJSONObj.getJSONArray("creationDate");
//        JSONArray minimalPointArray = xmlJSONObj.getJSONArray("minimalPoint");
//        JSONArray tunedInWorksArray = xmlJSONObj.getJSONArray("tunedInWorks");
//        JSONArray difficultyArray = xmlJSONObj.getJSONArray("difficulty");
//        JSONArray disciplineArray = xmlJSONObj.getJSONArray("discipline");
//
//        for ( int i = 0; i < idArray.length(); i++)
//        {
//            labWorks2.add(new LabWork(
//                   Integer.parseInt((String) idArray.get(i)),
//                    (String) nameArray.get(i),
//                   new Coordinates(
//                           Integer.parseInt((String) coordinatesArray.getJSONObject(i)
//                            .get("x")),
//                           Integer.parseInt((String) coordinatesArray.getJSONObject(i)
//                                   .get("y")))
//                    ,
//                    LocalDate.parse((CharSequence) creationDateArray.get(i)),
//                   Double.parseDouble((String) minimalPointArray.get(i)),
//                    Long.parseLong((String) tunedInWorksArray.get(i)),
//                    Difficulty.valueOf(difficultyArray.get(i).toString()),
//                   new Discipline((String) disciplineArray.getJSONObject(i)
//                           .get("name"),
//                           Integer.parseInt((String) disciplineArray.getJSONObject(i)
//                                   .get("labsCount")))));
//        }
//
//
//

       // String initDate = json.substring(json.indexOf(": ")+3, json.indexOf(",") - 1);




//        String jsonCollection = json.substring(json.indexOf("[ ") ) ;
//        String  jsonColection2 = jsonCollection.substring(0, jsonCollection.lastIndexOf("}") - 1);
//        jsonColection2 = jsonColection2.trim();
//
//        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
//        Type listType = new TypeToken<HashSet<LabWork>>(){}.getType();
//        HashSet<LabWork> labWorks = gson.fromJson(jsonColection2, listType);



        //return new CollectionManager(labWorks2, LocalDate.parse(initDate2),initFileName2);
        return new CollectionManager(getAllLabWorks(), LocalDate.now(),"");
    }


    public static Map<String, CommandExecutor> initializeCommandExecutors(CollectionManager collectionManager)
    {
        Map<String, CommandExecutor> commandExecutors = new HashMap<>();
        commandExecutors.put("add", new AddCommandExecutor(collectionManager));
        commandExecutors.put("info", new InfoCommandExecutor(collectionManager));
        commandExecutors.put("show", new ShowCommandExecutor(collectionManager));
        commandExecutors.put("clear", new ClearCommandExecutor(collectionManager));
        commandExecutors.put("remove_by_id", new RemoveByIdCommandExecutor(collectionManager));
        commandExecutors.put("update", new UpdateCommandExecutor(collectionManager));
        commandExecutors.put("save", new SaveCommandExecutor(collectionManager));
        commandExecutors.put("execute_script", new ExecuteScriptCommandExecutor(
                commandExecutors
        ));
        commandExecutors.put("help", new HelpCommandExecutor());
        commandExecutors.put("remove_greater", new RemoveGreaterCommandExecutor(collectionManager));
        commandExecutors.put("remove_lower", new RemoveLowerCommandExecutor(collectionManager));
        commandExecutors.put("exit", new ExitCommandExecutor());
        commandExecutors.put("history", new HistoryCommandExecutor());
        commandExecutors.put("sum_of_tuned_in_works", new SumOfTunedInWorksCommandExecutor(collectionManager));
        commandExecutors.put("print_ascending", new PrintAscendingCommandExecutor(collectionManager));
        commandExecutors.put("print_field_descending_difficulty", new PrintFieldDescendingDifficultyCommandExecutor(collectionManager));
        commandExecutors.put("register", new RegisterCommandExecutor());


        return commandExecutors;

    }







    public static Set<LabWork> getAllLabWorks() {
        Set<LabWork> labWorks = new HashSet<>();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM LabWork")) {

            while (resultSet.next()) {
                LabWork labWork = new LabWork();
                labWork.setId(resultSet.getInt("id"));
                labWork.setName(resultSet.getString("name"));

                // Получение координат
                Coordinates coordinates = getCoordinatesById(resultSet.getInt("coordinates_id"));
                labWork.setCoordinates(coordinates);

                // Получение даты создания
                labWork.setCreationDate(resultSet.getTimestamp("creationDate").toLocalDateTime().toLocalDate());

                // Получение остальных полей
                labWork.setMinimalPoint(resultSet.getDouble("minimalPoint"));
                labWork.setTunedInWorks(resultSet.getLong("tunedInWorks"));
                labWork.setDifficulty(Difficulty.valueOf(resultSet.getString("difficulty")));

                // Получение дисциплины
                Discipline discipline = getDisciplineById(resultSet.getInt("discipline_id"));
                labWork.setDiscipline(discipline);

                // Получение создателя
                User creator = getUserById(resultSet.getInt("creator_id"));
                labWork.setUser(creator);

                labWorks.add(labWork);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return labWorks;
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





    private static User getUserById(int id) throws SQLException {
        User user = null;
        String query = "SELECT * FROM User WHERE id = ?";
             PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPwd(resultSet.getString("password"));
                }
            }
        return user;
    }


    private static Discipline getDisciplineById(int id) throws SQLException {
        Discipline discipline = null;

        String query = "SELECT * FROM Discipline WHERE id = ?";

             PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    discipline = new Discipline();
                    discipline.setId(resultSet.getInt("id"));
                    discipline.setName(resultSet.getString("name"));
                    discipline.setLabsCount(resultSet.getInt("labsCount"));
                }
            }


        return discipline;
    }



    private static Coordinates getCoordinatesById(int id) throws SQLException {
        Coordinates coordinates = null;

        String query = "SELECT * FROM Coordinates WHERE id = ?";

             PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    coordinates = new Coordinates();
                    coordinates.setX(resultSet.getInt("x"));
                    coordinates.setY(resultSet.getInt("y"));
                }
            }


        return coordinates;
    }


}
