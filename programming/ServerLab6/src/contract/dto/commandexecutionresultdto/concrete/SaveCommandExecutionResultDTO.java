package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class SaveCommandExecutionResultDTO implements CommandExecutionResultDTO {
    private String msg;

    public SaveCommandExecutionResultDTO(String msg) {
        this.msg = msg;
    }

    @Override
    public String getCommandName() {
        return "save";
    }

    public String getMsg() {
        return msg;
    }
}
