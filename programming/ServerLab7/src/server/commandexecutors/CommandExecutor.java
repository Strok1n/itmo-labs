package server.commandexecutors;

import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public abstract class CommandExecutor {

    protected String userName;
    protected String pwd;

    public abstract CommandExecutionResultDTOWrapper execute(CommandDTO commandDTO);

    public void setUser(String userName, String pwd){
        this.userName = userName;
        this.pwd = pwd;
    }
}
