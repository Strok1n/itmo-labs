package client.util;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.ExecuteScriptCommandDTO;

import java.util.ArrayList;
import java.util.List;

public class CommandDTOExtractor {
    private final List<CommandDTO> commandDTOList;

    public CommandDTOExtractor(CommandDTO commandDTO)
    {
        this.commandDTOList = new ArrayList<>();
        this.extractCommandDTO(commandDTO);
    }
    private void extractCommandDTO(CommandDTO commandDTO)
    {
        if (!(commandDTO instanceof ExecuteScriptCommandDTO))
            this.commandDTOList.add(commandDTO);
        else
        {
            ExecuteScriptCommandDTO executeScriptCommandDTO = (ExecuteScriptCommandDTO) commandDTO;
            for (CommandDTO cmdDTO : executeScriptCommandDTO.getCommandDTOList() )
                this.extractCommandDTO(cmdDTO);
        }
    }

    public List<CommandDTO> getCommandDTOList() {
        return commandDTOList;
    }
}
