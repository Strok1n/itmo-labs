package server.commandexecutors.concrete;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.HistoryCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RegisterCommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;

public class RegisterCommandExecutor extends CommandExecutor {
    @Override
    public CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO) {
        return new CommandExecutionResultDTOWrapper(
                new RegisterCommandExecutionResultDTO("Пользователь успешно зарегистрирован"), true);
    }
}