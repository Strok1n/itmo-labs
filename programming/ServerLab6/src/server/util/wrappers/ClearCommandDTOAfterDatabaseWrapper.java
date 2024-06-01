package server.util.wrappers;

import contract.dto.commanddto.CommandDTO;
import server.util.CommandDTOAfterDatabaseWrapper;

public class ClearCommandDTOAfterDatabaseWrapper extends CommandDTOAfterDatabaseWrapper {

    private int[] ids;

    public ClearCommandDTOAfterDatabaseWrapper(CommandDTO commandDTO, boolean isDatabaseOperationDone, String message, int[] ids) {
        super(commandDTO, isDatabaseOperationDone, message);
        this.ids = ids;
    }

    public ClearCommandDTOAfterDatabaseWrapper(CommandDTO commandDTO, boolean isDatabaseOperationDone) {
        super(commandDTO, isDatabaseOperationDone);
    }

    public ClearCommandDTOAfterDatabaseWrapper(CommandDTO commandDTO) {
        super(commandDTO);
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }
}
