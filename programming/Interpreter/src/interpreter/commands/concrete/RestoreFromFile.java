package interpreter.commands.concrete;

import business.LabWork;
import constants.Constants;
import interpreter.commands.Command;
import trash.XmlAndString;
import util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.ZonedDateTime;
import java.util.*;

public class RestoreFromFile extends Command
{
    public RestoreFromFile()
    {
        this.help = "restore_from_file: restore the collection from the file";
    }

    @Override
    public void execute()
    {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File(Constants.INITIAL_FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String  xml ="";

        List<Object> objects;
        while (fileScanner.hasNext()) {
            xml = xml + (fileScanner.next());
            xml = xml + "\n";
        }

        try {
            objects = xmlToObjectArray(xml, "");
        } catch (NoSuchFieldException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

       Object[] ts = Arrays.copyOfRange(objects.toArray(), 0, objects.size(), LabWork[].class);

        this.baseCollection.setContainer(new HashSet<>(List.of(ts)));


    }




    public XmlAndString xmlToObject(String xml, String topLevelClassName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {



        String className = xml.substring(xml.indexOf("<") + 1, xml.indexOf(">"));


        Class<?> clazz = Class.class;
        if (Objects.equals(topLevelClassName, ""))
            clazz = Class.forName("business." + className);
        else {
            clazz = Class.forName("business." + topLevelClassName);
        }

        Constructor<?> constructor = clazz.getDeclaredConstructor();
        Object object = constructor.newInstance();

        xml = xml.substring(xml.indexOf(">") + 1);





        while (xml.indexOf("</") > xml.indexOf(">") || !xml.substring(xml.indexOf("</") + 2, xml.indexOf(">")).toLowerCase().equals(className.toLowerCase())) {




            String fieldName = xml.substring(xml.indexOf("<") + 1, xml.indexOf(">"));
            xml = xml.substring(xml.indexOf(">"));
            Object  fieldValue = xml.substring(xml.indexOf(">") + 1, xml.indexOf("<"));



            Field field =  object.getClass().getDeclaredField(fieldName.substring(0,1).toLowerCase()  + fieldName.substring(1));


            if (TypeUtilities.isPrimitiveOrWrapperOrString(field.getType()) || field.getType().isEnum() || field.getType().equals(ZonedDateTime.class))
            {
                field.setAccessible(true);
                fieldValue = TypeUtilities.StringToSpecifiedFieldType((String) fieldValue, field);
                field.set(object, fieldValue);
                field.setAccessible(false);





                xml = xml.substring(xml.indexOf(">") + 2);
                xml = xml.substring(xml.indexOf(">") + 2);
                //System.out.println(xml);

            }
            else
            {
                Class<?> clazzz = Class.forName(field.getDeclaringClass().getName());

                Constructor<?> constructor1 = clazzz.getDeclaredConstructor();

                Object object1 = constructor1.newInstance();

                XmlAndString xmlAndString = xmlToObject( "<"+fieldName + ">" +xml.substring(1),  fieldName);


                field.setAccessible(true);
                field.set(object, xmlAndString.obj);
                field.setAccessible(false);

                xml = xmlAndString.s1;
                topLevelClassName = xmlAndString.s2;

                xml = xml.substring(xml.indexOf(">") + 2);
            }
        }
        return new XmlAndString(xml, topLevelClassName, object);
    }



    public List<Object> xmlToObjectArray(String xml, String topLevelClassName) throws NoSuchFieldException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {



        xml = xml.substring(0, xml.lastIndexOf("</LabWorks>"));



        xml = xml.substring(xml.indexOf("<InitializationDate>") +"<InitializationDate>".length() );

        ZonedDateTime dateTime = ZonedDateTime.parse(xml.substring(0, xml.indexOf("</InitializationDate>")));

        xml = xml.substring(xml.indexOf("<LabWork>"));
        List<Object> list = new ArrayList<>();

        XmlAndString xmlAndString  = this.xmlToObject(xml, topLevelClassName);
        list.add(xmlAndString.obj);

        xmlAndString.s1 =  xmlAndString.s1.substring(xmlAndString.s1.indexOf('>') + 1);

        xmlAndString.obj = null;

        while ( (xmlAndString.s1.length() > 5) && !xmlAndString.s1.substring(xmlAndString.s1.indexOf('>') + 1).equals("")){


            xmlAndString = xmlToObject(xmlAndString.s1, "");

            list.add(xmlAndString.obj);


            xmlAndString.s1 =  xmlAndString.s1.substring(xmlAndString.s1.indexOf('>') + 1);

        }

        return list;
    }


















}
