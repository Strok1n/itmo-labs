package client.serverresponsehandler;

import contract.commandexecutionresult.CommandExecutionResultDTO;
import contract.commandexecutionresult.InfoCommandExecutionResultDTO;

public class InfoCommandServerResponseHandler implements ServerResponseHandler{

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
