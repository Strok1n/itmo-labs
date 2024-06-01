package contract.dto;

import contract.dto.commanddto.CommandDTO;

public class CommandDTOWrapper implements CommandDTO{
    private String username;
    private String password;
    private CommandDTO commandDTO;
    private boolean userExists;

    public CommandDTOWrapper(String username,
                             String pwd,
                             CommandDTO commandDTO,
                             boolean userExists){
        this.username = username;
        this.password = pwd;
        this.commandDTO = commandDTO;
        this.userExists = userExists;
    }


    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return password;
    }

    public CommandDTO getCommandDTO() {
        return commandDTO;
    }

    @Override
    public String getCommandName() {
        return this.commandDTO.getCommandName();
    }

    public boolean isUserExists() {
        return userExists;
    }

    public void setUserExists(boolean userExists) {
        this.userExists = userExists;
    }
}
