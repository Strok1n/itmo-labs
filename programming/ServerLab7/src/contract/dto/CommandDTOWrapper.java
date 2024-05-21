package contract.dto;

import contract.dto.commanddto.CommandDTO;

public class CommandDTOWrapper implements CommandDTO{
    private String user;
    private String pwd;
    private CommandDTO commandDTO;
    private boolean userExists;

    public CommandDTOWrapper(String user,
                             String pwd,
                             CommandDTO commandDTO, boolean userExists){
        this.user = user;
        this.pwd = pwd;
        this.commandDTO = commandDTO;
        this.userExists = userExists;
    }


    public String getUser() {
        return user;
    }

    public String getPwd() {
        return pwd;
    }

    public CommandDTO getCommandDTO() {
        return commandDTO;
    }

    @Override
    public String getCommandName() {
        return null;
    }

    public boolean isUserExists() {
        return userExists;
    }

    public void setUserExists(boolean userExists) {
        this.userExists = userExists;
    }
}
