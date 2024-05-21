package client.util;

import client.Client;
import client.CommandPreparerForSendingToTheServer;
import client.CommandSenderToTheServer;
import client.OutputStringBuilder;
import client.commanddtobuilders.CommandDTOBuilder;
import client.commanddtobuilders.concrete.*;
import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import client.commandexecutionresulthandlers.concrete.*;
import client.io.ConsoleReader;
import client.io.ConsoleWriter;
import contract.CommandName;

import java.util.ArrayDeque;
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

    static public Map<CommandName, CommandDTOBuilder>  initializeCommandDTOBuilders(ConsoleReader consoleReader, ConsoleWriter consoleWriter)
    {
        Map<CommandName, CommandDTOBuilder> commandDTOBuilders = new HashMap<>();
        commandDTOBuilders.put(CommandName.help, new HelpCommandDTOBuilder());
        commandDTOBuilders.put(CommandName.add, new AddCommandDTOBuilder(consoleReader, consoleWriter));
        commandDTOBuilders.put(CommandName.show, new ShowCommandDTOBuilder());
        commandDTOBuilders.put(CommandName.info, new InfoCommandDTOBuilder());
        commandDTOBuilders.put(CommandName.clear, new ClearCommandDTOBuilder());

        // убрать команду save
        //commandDTOBuilders.put(CommandName.save, new SaveCommandDTOBuilder());

        commandDTOBuilders.put(CommandName.remove_by_id, new RemoveByIdCommandDTOBuilder(consoleReader, consoleWriter));
        commandDTOBuilders.put(CommandName.update, new UpdateCommandDTOBuilder(consoleReader, consoleWriter));
        commandDTOBuilders.put(CommandName.execute_script, new ExecuteScriptCommandDTOBuilder(commandDTOBuilders));
        commandDTOBuilders.put(CommandName.remove_greater, new RemoveGreaterCommandDTOBuilder(consoleReader, consoleWriter));
        commandDTOBuilders.put(CommandName.remove_lower, new RemoveLowerCommandDTOBuilder(consoleReader, consoleWriter));
        commandDTOBuilders.put(CommandName.exit, new ExitCommandDTOBuilder());
        commandDTOBuilders.put(CommandName.history, new HistoryCommandDTOBuilder());
        commandDTOBuilders.put(CommandName.sum_of_tuned_in_works, new SumOfTunedInWorksCommandDTOBuilder());
        commandDTOBuilders.put(CommandName.print_ascending, new PrintAscendingCommandDTOBuilder());
        commandDTOBuilders.put(CommandName.print_field_descending_difficulty, new PrintFieldDescendingDifficultyCommandDTOBuilder());

        return commandDTOBuilders;
    }

    static public  Map<String, CommandExecutionResultHandler> initializeCommandExecutionResultDTOHandlers(ArrayDeque<CommandName> history)
    {
        Map<String, CommandExecutionResultHandler> commandExecutionResultDTOHandlers = new HashMap<>();
        commandExecutionResultDTOHandlers.put("help", new HelpCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("info", new InfoCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("show", new ShowCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("save", new SaveCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("clear", new ClearCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("add", new AddCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("remove_by_id", new RemoveByIdCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("update", new UpdateCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("history", new HistoryCommandExecutionResultHandler(history));
        commandExecutionResultDTOHandlers.put("execute_script", new ExecuteScriptCommandExecutionResultHandler(commandExecutionResultDTOHandlers));

        commandExecutionResultDTOHandlers.put("remove_greater", new RemoveGreaterCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("remove_lower", new RemoveLowerCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("exit", new ExitCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("history", new HistoryCommandExecutionResultHandler(history));
        commandExecutionResultDTOHandlers.put("sum_of_tuned_in_works", new SumOfTunedInWorksCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("print_ascending", new PrintAscendingCommandExecutionResultHandler());
        commandExecutionResultDTOHandlers.put("print_field_descending_difficulty", new PrintFieldDescendingDifficultyCommandExecutionResultHandler());


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

    static public OutputStringBuilder initializeOutputStringBuilder(ArrayDeque<CommandName> history)
    {
        return new OutputStringBuilder(history);
    }

}
