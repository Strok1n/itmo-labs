package contract.dto.commanddto.concrete;

import contract.dto.commanddto.CommandDTO;

public class RemoveByIdCommandDTO implements CommandDTO {

    private int id;

    public RemoveByIdCommandDTO(int id)
    {
        this.id = id;
    }

    @Override
    public String getCommandName() {
        return "remove_by_id";
    }

    public int getId() {
        return id;
    }
}
