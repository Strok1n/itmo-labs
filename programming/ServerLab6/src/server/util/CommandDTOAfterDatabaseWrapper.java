package server.util;

import contract.dto.commanddto.CommandDTO;

public class CommandDTOAfterDatabaseWrapper implements CommandDTO{

    private CommandDTO commandDTO;
    private boolean isDatabaseOperationDone;
    private String message;

    public CommandDTOAfterDatabaseWrapper(CommandDTO commandDTO, boolean isDatabaseOperationDone, String message) {
        this.commandDTO = commandDTO;
        this.isDatabaseOperationDone = isDatabaseOperationDone;
        this.message = message;
    }

    public CommandDTOAfterDatabaseWrapper(CommandDTO commandDTO, boolean isDatabaseOperationDone) {
        this.commandDTO = commandDTO;
        this.isDatabaseOperationDone = isDatabaseOperationDone;
    }

    public CommandDTOAfterDatabaseWrapper(CommandDTO commandDTO) {
        this.commandDTO = commandDTO;
    }

    public boolean isDatabaseOperationDone() {
        return isDatabaseOperationDone;
    }

    public void setDatabaseOperationDone(boolean databaseOperationDone) {
        isDatabaseOperationDone = databaseOperationDone;
    }

    public CommandDTO getCommandDTO() {
        return commandDTO;
    }

    public void setCommandDTO(CommandDTO commandDTO) {
        this.commandDTO = commandDTO;
    }

    @Override
    public String getCommandName() {
        return this.commandDTO.getCommandName();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
