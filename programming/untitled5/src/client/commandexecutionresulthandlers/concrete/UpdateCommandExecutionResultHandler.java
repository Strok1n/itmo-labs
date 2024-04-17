package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.UpdateCommandExecutionResultDTO;

public class UpdateCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        UpdateCommandExecutionResultDTO updateCommandExecutionResultDTO =
                (UpdateCommandExecutionResultDTO) commandExecutionResultDTO;

        if (updateCommandExecutionResultDTO.getCommandExecutionResult())
            return "Лабораторная работа с id = " + updateCommandExecutionResultDTO.getId() + " успешно обновлена в коллекции";
        else
            return "Обновление по id не выполнено. Лабораторная работа с id = "
                    + updateCommandExecutionResultDTO.getId() + " не обновлена";
    }
}
