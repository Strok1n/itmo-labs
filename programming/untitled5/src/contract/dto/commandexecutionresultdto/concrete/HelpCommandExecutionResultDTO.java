package contract.dto.commandexecutionresultdto.concrete;

import contract.CommandIdentifier;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

import java.util.Map;

public class HelpCommandExecutionResultDTO implements CommandExecutionResultDTO {

    private final Map<CommandIdentifier, String> helpMap;

    public HelpCommandExecutionResultDTO(Map<CommandIdentifier, String> helpMap) {
        this.helpMap = helpMap;
    }

    @Override
    public String getCommandName() {
        return "help";
    }

    public Map<CommandIdentifier, String> getHelpMap() {
        return helpMap;
    }
}
