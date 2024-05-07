package contract.dto.commandexecutionresultdto.concrete;

import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

import java.time.LocalDate;

public class InfoCommandExecutionResultDTO implements CommandExecutionResultDTO {
    private String collectionType;
    private LocalDate collectionInitializationDateTime;
    private int collectionSize;

    public InfoCommandExecutionResultDTO(String collectionType,
                                         LocalDate collectionInitializationDateTime,
                                         int collectionSize)
    {
        this.collectionType = collectionType;
        this.collectionInitializationDateTime = collectionInitializationDateTime;
        this.collectionSize = collectionSize;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public LocalDate getCollectionInitializationDateTime() {
        return collectionInitializationDateTime;
    }

    public int getCollectionSize() {
        return collectionSize;
    }

    @Override
    public String getCommandName() {
        return "info";
    }
}
