package contract.commandexecutionresult;

import java.time.ZonedDateTime;

public class InfoCommandExecutionResultDTO implements CommandExecutionResultDTO{
    private String collectionType;
    private ZonedDateTime collectionInitializationDateTime;
    private int collectionSize;

    public InfoCommandExecutionResultDTO(String collectionType,
                                         ZonedDateTime collectionInitializationDateTime,
                                         int collectionSize)
    {
        this.collectionType = collectionType;
        this.collectionInitializationDateTime = collectionInitializationDateTime;
        this.collectionSize = collectionSize;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public ZonedDateTime getCollectionInitializationDateTime() {
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
