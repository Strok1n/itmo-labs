package interpreter.commands.concrete;

import business.LabWork;
import interpreter.commands.Command;

import java.util.Comparator;

public class PrintAscending extends Command {


    public PrintAscending()
    {
        this.help = "print_ascending: display all collection elements in ascending order";
    }

    @Override
    public void execute()
    {

        Object[] objects = this.baseCollection.getContainer().stream().sorted(new Comparator<LabWork>() {
            @Override
            public int compare(LabWork o1, LabWork o2) {
                return o1.compareTo(o2);
            }
        }).toArray();

        for (Object o : objects)
            this.outputStream.println(o);

    }
}
