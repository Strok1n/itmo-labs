package client.commandexecutionresulthandlers.concrete;

import client.commandexecutionresulthandlers.CommandExecutionResultHandler;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.SumOfTunedInWorksCommandExecutionResultDTO;

public class SumOfTunedInWorksCommandExecutionResultHandler implements CommandExecutionResultHandler {
    @Override
    public String handleServerResponse(CommandExecutionResultDTO commandExecutionResultDTO) {
        SumOfTunedInWorksCommandExecutionResultDTO dto = (SumOfTunedInWorksCommandExecutionResultDTO) commandExecutionResultDTO;

        return "Сумма значений поля tunedInWorks для всех элементов коллекции: " +
                dto.getSum();
    }
}
