package contract.dto.commandexecutionresultdto.concrete;

import contract.CommandName;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

import java.util.Map;

public class HelpCommandExecutionResultDTO implements CommandExecutionResultDTO {

    private final Map<CommandName, String> helpMap;

    public HelpCommandExecutionResultDTO(Map<CommandName, String> helpMap) {
        this.helpMap = helpMap;
    }

    @Override
    public String getCommandName() {
        return "help";
    }

    public Map<CommandName, String> getHelpMap() {
        return helpMap;
    }
}
