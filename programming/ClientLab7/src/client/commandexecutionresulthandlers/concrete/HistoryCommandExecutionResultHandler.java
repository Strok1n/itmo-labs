package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.CommandName;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

import java.util.ArrayDeque;

public class HistoryCommandExecutionResultHandler implements CommandExecutionResultHandler {

    private final ArrayDeque<CommandName> history;
    public HistoryCommandExecutionResultHandler(ArrayDeque<CommandName> history)
    {
        this.history = history;
    }

    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        String out = "";
        for (CommandName cmdName: history)
            out = out.concat(cmdName.toString()).concat("\n");
        return out;
    }

}
