package server.util.wrappers;

import contract.dto.commanddto.CommandDTO;
import server.util.CommandDTOAfterDatabaseWrapper;

public class AddCommandDTOAfterDatabaseWrapper extends CommandDTOAfterDatabaseWrapper {

    private Integer labWorkGeneratedId;

    public AddCommandDTOAfterDatabaseWrapper(CommandDTO commandDTO, boolean isDatabaseOperationDone, String message) {
        super(commandDTO, isDatabaseOperationDone, message);
    }

    public AddCommandDTOAfterDatabaseWrapper(CommandDTO commandDTO, boolean isDatabaseOperationDone) {
        super(commandDTO, isDatabaseOperationDone);
    }

    public AddCommandDTOAfterDatabaseWrapper(CommandDTO commandDTO) {
        super(commandDTO);
    }

    public AddCommandDTOAfterDatabaseWrapper(CommandDTO commandDTO, boolean isDatabaseOperationDone, String message, int id) {
        super(commandDTO, isDatabaseOperationDone, message);
        this.labWorkGeneratedId = id;
    }

    public Integer getLabWorkGeneratedId() {
        return labWorkGeneratedId;
    }

    public void setLabWorkGeneratedId(Integer labWorkGeneratedId) {
        this.labWorkGeneratedId = labWorkGeneratedId;
    }
}
