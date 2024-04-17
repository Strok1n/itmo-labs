package client;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import server.Server;

public class CommandSenderToTheServer
{
    public CommandExecutionResultDTO sendCommandDTOToTheServer(CommandDTO commandDTO)
    {
        return Server.serverEntryPoint.response(commandDTO);
    }
}
