package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.ShowCommandExecutionResultDTO;

public class ShowCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO)
    {
        ShowCommandExecutionResultDTO showCommandExecutionResultDTO
                = (ShowCommandExecutionResultDTO) commandExecutionResultDTO;
        String output = "";
        for (String element: showCommandExecutionResultDTO.getCollectionElements())
            output = output.concat(element.toString()).concat("\n");
        return output;
    }
}
