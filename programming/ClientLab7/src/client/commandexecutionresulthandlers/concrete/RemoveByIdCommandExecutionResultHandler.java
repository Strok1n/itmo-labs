package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RemoveByIdCommandExecutionResultDTO;

public class RemoveByIdCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        RemoveByIdCommandExecutionResultDTO removeByIdCommandExecutionResultDTO =
                (RemoveByIdCommandExecutionResultDTO) commandExecutionResultDTO;
        if (removeByIdCommandExecutionResultDTO.getCommandExecutionResult())
            return "Лабораторная работа с id = " + removeByIdCommandExecutionResultDTO.getId() + " успешно удалена из коллекции";
        else
            return "Удаление по id не выполнено. Лабораторная работа с id = "
                    + removeByIdCommandExecutionResultDTO.getId() + " не находилась и не находится в коллкции.";
    }
}
