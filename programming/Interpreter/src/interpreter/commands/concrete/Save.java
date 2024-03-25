package interpreter.commands.concrete;

import interpreter.commands.Command;
import constants.Constants;

import java.io.FileWriter;
import java.io.IOException;

public class Save extends Command
{
    public Save()
    {
        this.help = "save: save the collection into the file";
    }

    @Override
    public void execute() {
        try {
            FileWriter fileWriter = new FileWriter(Constants.INITIAL_FILE_NAME);
            fileWriter.write(this.baseCollection.toXml());
            fileWriter.close();
        } catch (IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
