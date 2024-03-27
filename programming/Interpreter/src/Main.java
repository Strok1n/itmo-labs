
import interpreter.Interpreter;

import java.io.FileWriter;
import java.io.PrintWriter;


public class Main {
    public static void main(String[] args) throws Exception {

        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<Collection>"+
                        "<InitializationDate>2024-03-03T01:18:44.422732900+03:00[Europe/Moscow]</InitializationDate>\n<LabWorks>\n" +
                        "<LabWork>\n" +
                        "\t<id>5</id>\n" +
                        "\t<name>1</name>\n" +
                        "<Coordinates>\n" +
                        "\t<x>65.0</x>\n" +
                        "\t<y>567</y>\n" +
                        "</Coordinates>\n" +
                        "\t<creationDate>2024-03-03T01:18:44.422732900+03:00[Europe/Moscow]</creationDate>\n" +
                        "\t<minimalPoint>456</minimalPoint>\n" +
                        "\t<tunedInWorks>456</tunedInWorks>\n" +
                        "\t<difficulty>EASY</difficulty>\n" +
                        "<Discipline>\n" +
                        "\t<name>756</name>\n" +
                        "\t<labsCount>876</labsCount>\n" +
                        "</Discipline>\n" +
                        "</LabWork>\n" +



                        "<LabWork>\n" +
                        "\t<id>87</id>\n" +
                        "\t<name>1</name>\n" +
                        "<Coordinates>\n" +
                        "\t<x>65.0</x>\n" +
                        "\t<y>567</y>\n" +
                        "</Coordinates>\n" +
                        "\t<creationDate>2024-03-03T01:18:44.422732900+03:00[Europe/Moscow]</creationDate>\n" +
                        "\t<minimalPoint>456</minimalPoint>\n" +
                        "\t<tunedInWorks>456</tunedInWorks>\n" +
                        "\t<difficulty>EASY</difficulty>\n" +
                        "<Discipline>\n" +
                        "\t<name>756</name>\n" +
                        "\t<labsCount>876</labsCount>\n" +
                        "</Discipline>\n" +
                        "</LabWork>\n" +

                        "<LabWork>\n" +
                        "\t<id>5765</id>\n" +
                        "\t<name>1</name>\n" +
                        "<Coordinates>\n" +
                        "\t<x>65.0</x>\n" +
                        "\t<y>567</y>\n" +
                        "</Coordinates>\n" +
                        "\t<creationDate>2024-03-03T01:18:44.422732900+03:00[Europe/Moscow]</creationDate>\n" +
                        "\t<minimalPoint>456</minimalPoint>\n" +
                        "\t<tunedInWorks>456</tunedInWorks>\n" +
                        "\t<difficulty>EASY</difficulty>\n" +
                        "<Discipline>\n" +
                        "\t<name>756</name>\n" +
                        "\t<labsCount>876</labsCount>\n" +
                        "</Discipline>\n" +
                        "</LabWork>\n" +
                        "</LabWorks>\n" +
                        "</Collection>\n"



                ;

        FileWriter fileWriter = new FileWriter("C:\\Users\\1\\Desktop\\file3.txt");

        fileWriter.write(xml);

        fileWriter.close();

        Interpreter interpreter = new Interpreter();

        interpreter.start();

    }
}