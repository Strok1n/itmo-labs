package interpreter.commands.concrete;

import business.LabWork;
import interpreter.commands.Command;

public class SumOfTunedInWorks extends Command
{

    public SumOfTunedInWorks()
    {
        this.help = "sum_of_tuned_in_works: display sum of the \"tunedInWorks\" field value for all collection elements";
    }

    @Override
    public void execute()
    {
        if (this.baseCollection.getContainer().isEmpty())
        {
            this.outputStream.println("The collection is empty");
            return;
        }
        long sum = 0;
        for (Object object : this.baseCollection.getContainer())
            sum += ((LabWork) object).getTunedInWorks();

        this.outputStream.println("Sum of the field \"tunedInWorks\"" +
                "for all collection elements is :" + sum);
    }
}
