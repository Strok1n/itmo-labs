package server.commandexecutors.concrete;


import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RegisterCommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;

public class RegisterCommandExecutor implements CommandExecutor {
    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {

        if (((CommandDTOAfterDatabaseWrapper)commandDTO).isDatabaseOperationDone())
            return(new RegisterCommandExecutionResultDTO("Пользователь успешно зарегистрирован"));
        else
            return(new RegisterCommandExecutionResultDTO("Пользователь не зарегистрирован"));


    }
}