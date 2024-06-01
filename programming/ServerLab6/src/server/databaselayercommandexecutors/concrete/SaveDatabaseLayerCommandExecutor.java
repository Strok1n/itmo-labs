package server.databaselayercommandexecutors.concrete;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.SaveCommandExecutionResultDTO;
import org.json.JSONObject;
import org.json.XML;
import server.CollectionManager;
import server.business.LabWork;
import server.commandexecutors.CommandExecutor;
import server.databaselayercommandexecutors.DatabaseLayerCommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;
import server.util.LocalDateTypeAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Set;

public class SaveDatabaseLayerCommandExecutor extends DatabaseLayerCommandExecutor {



    public SaveDatabaseLayerCommandExecutor(Connection connection)
    {
        super(connection);
    }

    @Override
    public CommandDTOAfterDatabaseWrapper execute(CommandDTO commandDTO) {
        return new CommandDTOAfterDatabaseWrapper(commandDTO);
    }


























}