package client.commandpreparers;

import contract.command.CommandDTO;

public interface CommandPreparer
{
    CommandDTO prepareCommand(String[] arguments);
}
