package contract.dto;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

public class CommandExecutionResultDTOWrapper implements CommandExecutionResultDTO {

    private CommandExecutionResultDTO commandExecutionResultDTO;
    private boolean isDataMutationLegal;

    public CommandExecutionResultDTOWrapper(CommandExecutionResultDTO commandExecutionResultDTO, boolean isDataMutationLegal) {
        this.commandExecutionResultDTO = commandExecutionResultDTO;
        this.isDataMutationLegal = isDataMutationLegal;
    }

    @Override
    public String getCommandName() {
        return commandExecutionResultDTO.getCommandName();
    }

    public CommandExecutionResultDTO getCommandExecutionResultDTO() {
        return commandExecutionResultDTO;
    }

    public void setCommandExecutionResultDTO(CommandExecutionResultDTO commandExecutionResultDTO) {
        this.commandExecutionResultDTO = commandExecutionResultDTO;
    }

    public boolean isDataMutationLegal() {
        return isDataMutationLegal;
    }

    public void setDataMutationLegal(boolean dataMutationLegal) {
        this.isDataMutationLegal = dataMutationLegal;
    }
}
