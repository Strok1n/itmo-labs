
import interpreter.Interpreter;

import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {


//        ObjectCreator objectCreator = new ObjectCreator(
//                LabWork.class,
//                new InputValidator(),
//                System.out,
//                new Scanner(System.in),
//                CommandExecutionMode.MANUAL_INPUT
//        );
//
//        LabWork labWork =(LabWork) objectCreator.createObject();
//
//        System.out.println(labWork);


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

     //   System.out.println(xml);
    //   XmlAndString xmlAndString =  OtherUtilities.xmlToObject(xml, "");


      //  System.out.println(xmlAndString.obj);
     //   System.out.println(xmlAndString.s1.substring(xmlAndString.s1.indexOf('>') + 1));
     //  System.out.println(xmlAndString.s2);


    //    List<Object> objects = OtherUtilities.xmlToObjectArray(xml, "");

      // System.out.println(objects);




        Interpreter interpreter = new Interpreter();




        Set<String > set = new HashSet();



       // System.out.println(xml);
       // System.out.println(System.getenv("FILE_NAME"));
        interpreter.start();






    }
}