package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class HistoryCommandDTO implements CommandDTO {
    @Override
    public String getCommandName() {
        return "history";
    }
}
