package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.InfoCommandExecutionResultDTO;

public class InfoCommandExecutionResultHandler implements CommandExecutionResultHandler {

    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        InfoCommandExecutionResultDTO infoCommandExecutionResultDTO =
                (InfoCommandExecutionResultDTO) commandExecutionResultDTO;

        return "Тип коллекции: " + infoCommandExecutionResultDTO.getCollectionType() + "\n" +
                "Дата инициализации коллекции: " +
                infoCommandExecutionResultDTO.getCollectionInitializationDateTime() + "\n" +
                "Количество элементов в коллекции: " +
                infoCommandExecutionResultDTO.getCollectionSize();
    }
}
