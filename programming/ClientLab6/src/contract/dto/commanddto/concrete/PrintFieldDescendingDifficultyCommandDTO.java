package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class PrintFieldDescendingDifficultyCommandDTO implements CommandDTO {
    @Override
    public String getCommandName() {
        return "print_field_descending_difficulty";
    }
}
