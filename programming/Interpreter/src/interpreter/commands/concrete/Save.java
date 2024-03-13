package interpreter.commands.concrete;

import interpreter.commands.Command;
import util.Constants;

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
            System.out.println(this.baseCollection.toXml());
            FileWriter fileWriter = new FileWriter(Constants.FILENAME);
            fileWriter.write(this.baseCollection.toXml());
            fileWriter.close();
        } catch (IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
