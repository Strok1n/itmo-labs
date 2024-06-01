package server.util;

import org.json.*;
import server.CollectionManager;
import server.business.Coordinates;
import server.business.Difficulty;
import server.business.Discipline;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;
import server.commandexecutors.concrete.*;
import server.databaselayercommandexecutors.DatabaseLayerCommandExecutor;
import server.databaselayercommandexecutors.concrete.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class ServerInitializer {


    public static CollectionManager initializerCollectionManager(Connection connection) throws IOException {

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
//
//    //    String json = new String(bufferedInputStream.readAllBytes(), StandardCharsets.UTF_8);
//
//        String xml = "";
//
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
//
//       // String initDate = json.substring(json.indexOf(": ")+3, json.indexOf(",") - 1);
//
//
//
//
////        String jsonCollection = json.substring(json.indexOf("[ ") ) ;
////        String  jsonColection2 = jsonCollection.substring(0, jsonCollection.lastIndexOf("}") - 1);
////        jsonColection2 = jsonColection2.trim();
////
////        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
////        Type listType = new TypeToken<HashSet<LabWork>>(){}.getType();
////        HashSet<LabWork> labWorks = gson.fromJson(jsonColection2, listType);
//
//      //  return new CollectionManager(labWorks2, LocalDate.parse(initDate2),initFileName2);
        try {
            System.out.println(getAllLabWorks(connection));
            return new CollectionManager(

                    getAllLabWorks(
                    connection
            ), LocalDate.now(), "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Метод для получения всех лабораторных работ
    static private Set<LabWork> getAllLabWorks(Connection connection) throws SQLException {
        String query = "SELECT lw.id, lw.name, lw.creationDate, lw.minimalPoint, lw.tunedInWorks, lw.difficulty, " +
                "c.x, c.y, d.name as discipline_name, d.labsCount " +
                "FROM LabWork lw " +
                "JOIN LabWork_Coordinates lwc ON lw.id = lwc.labwork_id " +
                "JOIN Coordinates c ON lwc.coordinates_id = c.id " +
                "LEFT JOIN Discipline d ON lw.discipline_id = d.id";

        Set<LabWork> labWorks = Collections.synchronizedSet( new HashSet<>());
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("#$%#$#%#$1");
            while (resultSet.next()) {
                System.out.println("#$%#$#%#$2");
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate creationDate = resultSet.getTimestamp("creationDate").toLocalDateTime().toLocalDate();
                Double minimalPoint = resultSet.getDouble("minimalPoint");
                long tunedInWorks = resultSet.getLong("tunedInWorks");
                Difficulty difficulty = resultSet.getString("difficulty") != null ? Difficulty.valueOf(resultSet.getString("difficulty")) : null;
                Coordinates coordinates = new Coordinates(resultSet.getInt("x"), resultSet.getInt("y"));
                Discipline discipline = resultSet.getString("discipline_name") != null ? new Discipline(resultSet.getString("discipline_name"), resultSet.getInt("labsCount")) : null;
                LabWork labWork = new LabWork(id, name, coordinates, creationDate, minimalPoint, tunedInWorks, difficulty, discipline);
                labWorks.add(labWork);
            }
        return labWorks;
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



    public static Map<String, DatabaseLayerCommandExecutor> initializeDatabaseLayerCommandExecutors( Connection connection)
    {

        Map<String, DatabaseLayerCommandExecutor> commandExecutors = new HashMap<>();
        commandExecutors.put("add", new AddDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("info", new InfoDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("show", new ShowDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("clear", new ClearDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("remove_by_id", new RemoveByIdDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("update", new UpdateDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("save", new SaveDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("execute_script", new ExecuteScriptDatabaseLayerCommandExecutor(
                connection
        ));
        commandExecutors.put("help", new HelpDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("remove_greater", new RemoveGreaterDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("remove_lower", new RemoveLowerDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("exit", new ExitDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("history", new HistoryDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("sum_of_tuned_in_works", new SumOfTunedInWorksDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("print_ascending", new PrintAscendingDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("print_field_descending_difficulty", new PrintFieldDescendingDifficultyDatabaseLayerCommandExecutor(connection));
        commandExecutors.put("register", new RegisterDatabaseLayerCommandExecutor(connection));

        return commandExecutors;

    }



}
