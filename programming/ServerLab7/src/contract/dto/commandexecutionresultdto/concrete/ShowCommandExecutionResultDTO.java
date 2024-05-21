package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

import java.util.List;

public class ShowCommandExecutionResultDTO implements CommandExecutionResultDTO
{
    final private List<String> collectionElements;

    public ShowCommandExecutionResultDTO(List<String> collectionElements) {
        this.collectionElements = collectionElements;
    }
    @Override
    public String getCommandName() {
        return "show";
    }

    public List<String> getCollectionElements() {
        return collectionElements;
    }
}
