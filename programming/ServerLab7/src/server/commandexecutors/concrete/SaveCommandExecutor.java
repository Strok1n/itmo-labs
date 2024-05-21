package server.commandexecutors.concrete;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.SaveCommandExecutionResultDTO;
import org.json.JSONObject;
import org.json.XML;
import server.CollectionManager;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;
import server.util.LocalDateTypeAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Set;

public class SaveCommandExecutor extends CommandExecutor {
    final private CollectionManager collectionManager;
    public SaveCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO)
    {
        Set<LabWork> labWorks = this.collectionManager.getCollectionCopy();

        String jsonOutput = "{    \"initDate\": \""
                +collectionManager.getCollectionInitializationDateTime() +
                "\",     \"collection\": [ ";

        String xmlOutput = "<Collection>\n<initDate>" +collectionManager.getCollectionInitializationDateTime()
                +"</initDate>\n<LabWorks>\n"  ;


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();



        for (LabWork labWork : labWorks) {
            jsonOutput = jsonOutput.concat(gson.toJson(labWork)).concat(", ");

            JSONObject jsonObject = new JSONObject(gson.toJson(labWork));
            String xml = XML.toString(jsonObject);
            xmlOutput = xmlOutput.concat(xml).concat("\n");
        }



        if (!labWorks.isEmpty()) {
            jsonOutput = jsonOutput.substring(0, jsonOutput.length() - 2);
        }


        jsonOutput = jsonOutput.concat(" ] }");
        xmlOutput = xmlOutput.concat("</LabWorks>\n</Collection>");


        String result = "Коллекция успешно сохранена в файл";
        try {

            FileWriter fileWriter = new FileWriter(Paths.get("").toAbsolutePath().toString()
                    + File.separator    + "collection.xml");

            fileWriter.write(xmlOutput);
            fileWriter.close();

          //  BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(

                 //   new FileOutputStream(collectionManager.getCollectionFileName())
         //   );

           // bufferedOutputStream.write(jsonOutput.getBytes());
          //  bufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new CommandExecutionResultDTOWrapper(new SaveCommandExecutionResultDTO(result), true);
    }
}
