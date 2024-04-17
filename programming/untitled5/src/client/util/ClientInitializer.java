package client.util;

import client.*;
import client.commanddtobuilders.*;
import client.commanddtobuilders.concrete.*;
import client.commandexecutionresulthandlers.*;
import client.commandexecutionresulthandlers.concrete.*;
import client.io.ConsoleReader;
import client.io.ConsoleWriter;
import contract.dto.commandexecutionresultdto.concrete.SaveCommandExecutionResultDTO;

import java.util.HashMap;
import java.util.Map;

public class ClientInitializer {

    static public Client initializeClient()
    {
        return new Client();
    }
    static public ConsoleReader initializeConsoleReader()
    {
        return new ConsoleReader(System.in);
    }

    static public ConsoleWriter initializeConsoleWriter()
    {
        return new ConsoleWriter(System.out);
    }

    static public Map<String, CommandDTOBuilder>  initializeCommandDTOBuilders(ConsoleReader consoleReader, ConsoleWriter consoleWriter)
    {
        Map<String, CommandDTOBuilder> commandDTOBuilders = new HashMap<>();
        commandDTOBuilders.put("help", new HelpCommandDTOBuilder());
        commandDTOBuilders.put("add", new AddCommandDTOBuilder(consoleReader, consoleWriter));
        commandDTOBuilders.put("show", new ShowCommandDTOBuilder());
        commandDTOBuilders.put("info", new InfoCommandDTOBuilder());
        commandDTOBuilders.put("clear", new ClearCommandDTOBuilder());
        commandDTOBuilders.put("save", new SaveCommandDTOBuilder());
        commandDTOBuilders.put("remove_by_id", new RemoveByIdCommandDTOBuilder(consoleReader, consoleWriter));
        commandDTOBuilders.put("update", new UpdateCommandDTOBuilder(consoleReader, consoleWriter));
        commandDTOBuilders.put("execute_script", new ExecuteScriptCommandDTOBuilder(commandDTOBuilders));
        return commandDTOBuilders;
    }

    static public  Map<String, CommandExecutionResultHandler> initializeCommandExecutionResultDTOHandlers()
    {
        Map<String, CommandExecutionResultHandler> commandExecutionResultDTOHandlers = new HashMap<>();
        commandExecutionResultDTOHandlers.put("help", new HelpCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("info", new InfoCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("show", new ShowCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("clear", new ClearCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("add", new AddCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("remove_by_id", new RemoveByIdCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("update", new UpdateCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("execute_script", new ExecuteScriptCommandExecutionResultHandler(commandExecutionResultDTOHandlers));
        return commandExecutionResultDTOHandlers;
    }

    static public CommandPreparerForSendingToTheServer initializeCommandPreparerForSendingToTheServer(ConsoleReader consoleReader, ConsoleWriter consoleWriter)
    {
        return new CommandPreparerForSendingToTheServer(consoleReader, consoleWriter);
    }

    static public CommandSenderToTheServer initializeCommandSenderToTheServer()
    {
        return new CommandSenderToTheServer();
    }

    static public OutputStringBuilder initializeOutputStringBuilder()
    {
        return new OutputStringBuilder();
    }

}
