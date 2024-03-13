package interpreter.commands.concrete;

import business.LabWork;
import interpreter.commands.Command;

public class PrintFieldDescendingDifficulty extends Command
{

    public PrintFieldDescendingDifficulty()
    {
        this.help = "print_field_descending_difficulty: " +
                "display the difficulty field values of all collection elements in descending order";
    }


    @Override
    public void execute()
    {

//        Object[] objects = this.baseCollection.getBaseCollection().stream().sorted(new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                return ((LabWork)o1).getDifficulty().compareTo(((LabWork)o2).getDifficulty());
//            }
//        }).toArray();

        Object[] objects = this.baseCollection.getContainer().stream().sorted().toArray();

        for (Object o : objects)
            this.outputStream.println(((LabWork)o).getDifficulty());
    }
}
