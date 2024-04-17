package contract;

public enum CommandIdentifier
{
    HELP("help"),
    INFO("info"),
    SHOW("show"),
    ADD("add"),
    UPDATE("update"),
    REMOVE_BY_ID("remove_by_id"),
    CLEAR("clear"),
    SAVE("save"),
    EXECUTE_SCRIPT("execute_script"),
    EXIT("exit"),
    REMOVE_GREATER("remove_greater"),
    REMOVE_LOWER("remove_lower"),
    HISTORY("history"),
    SUM_OF_TUNED_IN_WORKS("sum_of_tuned_in_works"),
    PRINT_ASCENDING("print_ascending"),
    PRINT_FIELD_DESCENDING_DIFFICULTY("print_field_descending_difficulty");
    private final String commandName;
    CommandIdentifier(String commandName)
    {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
