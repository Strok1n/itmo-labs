package interpreter.commands.concrete;

        import interpreter.commands.Command;

        import java.util.Queue;

public class History extends Command {

    private Queue<String> commandHistory;




    public History()
    {
        this.help = "history: show 8 last executed commands";
    }

    public void setCommandHistory(Queue<String> commandHistory) {
        this.commandHistory = commandHistory;
    }




    @Override
    public void execute() {
        this.outputStream.println("Last 8 commands:");
        int i = commandHistory.size();
        if (i > 8)
            i = 8;
        for (String commandName : this.commandHistory)
        {
            this.outputStream.println(i + ": " + commandName);
            i--;
        }

    }
}
