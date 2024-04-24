package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.HelpCommandExecutionResultDTO;

public class HelpCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        HelpCommandExecutionResultDTO helpCommandExecutionResultDTO = (HelpCommandExecutionResultDTO) commandExecutionResultDTO;
        String output = "";
        for(String help : helpCommandExecutionResultDTO.getHelpMap().values())
            output = output.concat(help).concat("\n");
        return output;
    }
}
