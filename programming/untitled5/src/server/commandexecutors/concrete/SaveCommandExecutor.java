package server.commandexecutors.concrete;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import server.CollectionManager;
import server.LocalDateTypeAdapter;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SaveCommandExecutor implements CommandExecutor {
    final private CollectionManager collectionManager;
    public SaveCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO)
    {
        Set<LabWork> labWorks = this.collectionManager.getCollectionCopy();

        String jsonOutput = "{\"collection\": [ ";

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();

        for (LabWork labWork : labWorks)
            jsonOutput = jsonOutput.concat(gson.toJson(labWork)).concat(", ");

        jsonOutput = jsonOutput.substring(0, jsonOutput.length() - 2);
        jsonOutput = jsonOutput.concat(" ] }");
        System.out.println(jsonOutput);










        return null;
    }
}
