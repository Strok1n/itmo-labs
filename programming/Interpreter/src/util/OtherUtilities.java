package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OtherUtilities
{
    public static String objectToJson(Object object) throws IllegalAccessException
    {
        StringBuilder s = new StringBuilder("{\n");
        for (Field field : object.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            s.append("\t\"").append(field.getName()).append("\": ").append(field.get(object)).append(",\n");
        }
            return s + "}";
    }

    public static String objectToXml(Object object) throws IllegalAccessException
    {
        StringBuilder s = new StringBuilder("");
        s.append("<").append(object.getClass().getSimpleName()).append(">\n");

        for (Field field : object.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            if (TypeUtilities.isPrimitiveOrWrapperOrString(field.getType()) || field.getType().isEnum()
            || field.getType().equals(ZonedDateTime.class)) {
                s.append("\t<").append(field.getName()).append(">");
                s.append(field.get(object));
                s.append("</").append(field.getName()).append(">\n");
            }
            else {
                s.append(objectToXml(field.get(object)));
            }
        }
        s.append("</").append(object.getClass().getSimpleName()).append(">\n");
        return s.toString();
    }


    public static XmlAndString xmlToObject(String xml, String topLevelClassName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        //   System.out.println("START");
        //  System.out.println("START");
        //   System.out.println(xml);
        //   System.out.println("END");
        //   System.out.println("END");

        String className = xml.substring(xml.indexOf("<") + 1, xml.indexOf(">"));

        //  System.out.println("CLASSNAME");
        // System.out.println(className);

        Class<?> clazz = Class.class;
        if (Objects.equals(topLevelClassName, ""))
            clazz = Class.forName("business." + className);
        else {
            clazz = Class.forName("business." + topLevelClassName);
        }

        Constructor<?> constructor = clazz.getDeclaredConstructor();
        Object object = constructor.newInstance();

        xml = xml.substring(xml.indexOf(">") + 1);


        //  System.out.println("WIthout classname");
        //System.out.println(xml);





        while (xml.indexOf("</") > xml.indexOf(">") || !xml.substring(xml.indexOf("</") + 2, xml.indexOf(">")).toLowerCase().equals(className.toLowerCase())) {

            //  System.out.println("WHILE CLASSNAME");
            //   System.out.println(className);
            //  System.out.println("WHILE XML:");
            //  System.out.println(xml);


            if (  xml.indexOf("</") < xml.indexOf(">")  ){
                //     System.out.println("KOJSODKFJOSKJDFOSJODFSDF");
                //     System.out.println(xml.substring(xml.indexOf("</") + 2, xml.indexOf(">")).toLowerCase());
                //    System.out.println(className.toLowerCase());


            }



            String fieldName = xml.substring(xml.indexOf("<") + 1, xml.indexOf(">"));
            xml = xml.substring(xml.indexOf(">"));
            Object  fieldValue = xml.substring(xml.indexOf(">") + 1, xml.indexOf("<"));
            // System.out.println("FIELD: NAME");
            //  System.out.println(fieldName);
            // System.out.println("FIELD VALUE");
            //   System.out.println(fieldValue);
            //   System.out.println("NEW XML");





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


                // System.out.println(xml);

                //   object1 = xmlToObject("<"+fieldName + ">" + xml.substring(1), topLevelClassName + fieldName);

                XmlAndString xmlAndString = xmlToObject( "<"+fieldName + ">" +xml.substring(1),  fieldName);



                field.setAccessible(true);
                field.set(object, xmlAndString.obj);
                field.setAccessible(false);

                xml = xmlAndString.s1;
                topLevelClassName = xmlAndString.s2;

                xml = xml.substring(xml.indexOf(">") + 2);
            }
//                    xml = xml.substring(xml.indexOf(">") + 2);
//                      xml = xml.substring(xml.indexOf(">") + 2);
//                    System.out.println(xml);




            //    System.out.println("##################################");









//
//
//      xml = xml.substring(xml.indexOf(">") + 2);
//      xml = xml.substring(xml.indexOf(">") + 2);
//
//            System.out.println("NEW:");
//            System.out.println(xml);
//
//            if (  xml.indexOf("</") <  xml.indexOf(">")  && xml.substring(xml.indexOf("</") + 2 , xml.indexOf(">")).equals(className))
//            {
//                System.out.println("TRUE");
//                System.out.println("TRUE");
//                System.out.println(xml.substring(xml.indexOf("</") + 2 , xml.indexOf(">")));
//                break;
//            }
//
//
//        }
//
//      //  Class<?> clazz = Class.forName("business." + className).getConstructor().newInstance();
//
//
//        System.out.println("REALLY END");
//        System.out.println(object);

            //  return object;



        }

//        System.out.println("EXIT");
//        System.out.println("EXIT");
//        System.out.println("EXIT");
//        System.out.println("EXIT");
//        System.out.println("EXIT");
//        System.out.println("EXIT");
//        System.out.println("EXIT");

        return new XmlAndString(xml, topLevelClassName, object);
    }



    public static List<Object>  xmlToObjectArray(String xml, String topLevelClassName) throws NoSuchFieldException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {



        xml = xml.substring(0, xml.lastIndexOf("</LabWorks>"));



        xml = xml.substring(xml.indexOf("<InitializationDate>") +"<InitializationDate>".length() );

        ZonedDateTime dateTime = ZonedDateTime.parse(xml.substring(0, xml.indexOf("</InitializationDate>")));



        xml = xml.substring(xml.indexOf("<LabWork>"));
        xml = xml.substring(xml.indexOf("<LabWork>\n"));


        List<Object> list = new ArrayList<>();

        XmlAndString xmlAndString  = xmlToObject(xml, topLevelClassName);
        list.add(xmlAndString.obj);

        xmlAndString.s1 =  xmlAndString.s1.substring(xmlAndString.s1.indexOf('>') + 1);

        xmlAndString.obj = null;

        while ( (xmlAndString.s1.length() > 5) && !xmlAndString.s1.substring(xmlAndString.s1.indexOf('>') + 1).equals("")){

            // System.out.println("SDFSDFSDFSFD");
            //  System.out.println(xmlAndString.s1.replaceAll("\n", "1"));
            // System.out.println("SDFSDFSDFSFD");


            xmlAndString = xmlToObject(xmlAndString.s1, "");

            //  System.out.println(xmlAndString.obj);
            list.add(xmlAndString.obj);
            // System.out.println(xmlAndString.obj);

            //    xmlAndString.s1 = xmlAndString.s1.substring(xmlAndString.s1.indexOf('>') + 1);

            xmlAndString.s1 =  xmlAndString.s1.substring(xmlAndString.s1.indexOf('>') + 1);

        }

        return list;
    }





        //        System.out.println("START");
//        System.out.println(xml);
//        System.out.println("END");
//
//        String className = xml.substring(xml.indexOf("<") + 1, xml.indexOf(">"));
//
//
//        System.out.println("CLAAASONA<E");
//        System.out.println(className);
//
//        Class<?> clazz = Class.class;
//
//        if (Objects.equals(topLevelClassName, ""))
//            clazz = Class.forName("business."  + className);
//            else {
//            clazz = Class.forName("business." + topLevelClassName);
//        }
//
//        Constructor<?> constructor = clazz.getDeclaredConstructor();
//
//        Object object = constructor.newInstance();
//
//        xml = xml.substring(xml.indexOf(">") + 1);
//        System.out.println(xml);
//
//
//
//        while (xml.indexOf("</") >  xml.indexOf(">") || !xml.substring(xml.indexOf("</") + 1, xml.indexOf(">")).equals(className))
//        {
//
//            String fieldName = xml.substring(xml.indexOf("<") + 1, xml.indexOf(">"));
//            xml = xml.substring(xml.indexOf(">"));
//            Object  fieldValue = xml.substring(xml.indexOf(">") + 1, xml.indexOf("<"));
//            System.out.println("FIELD: NAME");
//            System.out.println(fieldName);
//         System.out.println("FIELD VALUE");
//            System.out.println(fieldValue);
//
//
//           Field field =  object.getClass().getDeclaredField(fieldName.toLowerCase());
//
//
//
//           if (TypeUtilities.isPrimitiveOrWrapperOrString(field.getType()) || field.getType().isEnum() || field.getType().equals(ZonedDateTime.class))
//           {
//               field.setAccessible(true);
//               fieldValue = TypeUtilities.StringToSpecifiedFieldType((String) fieldValue, field);
//               field.set(object, fieldValue);
//               field.setAccessible(false);
//           }
//           else
//           {
//               Class<?> clazzz = Class.forName(field.getDeclaringClass().getName());
//
//               Constructor<?> constructor1 = clazzz.getDeclaredConstructor();
//
//               Object object1 = constructor1.newInstance();
//               System.out.println(xml);
//
//            //   object1 = xmlToObject("<"+fieldName + ">" + xml.substring(1), topLevelClassName + fieldName);
//
//               XmlAndString xmlAndString = xmlToObject("<"+fieldName + ">" + xml.substring(1), topLevelClassName + fieldName);
//
//
//               field.setAccessible(true);
//               field.set(object, xmlAndString.obj);
//               field.setAccessible(false);
//
//               xml = xmlAndString.s1;
//               topLevelClassName = xmlAndString.s2;
//           }
//
//
//      xml = xml.substring(xml.indexOf(">") + 2);
//      xml = xml.substring(xml.indexOf(">") + 2);
//
//            System.out.println("NEW:");
//            System.out.println(xml);
//
//            if (  xml.indexOf("</") <  xml.indexOf(">")  && xml.substring(xml.indexOf("</") + 2 , xml.indexOf(">")).equals(className))
//            {
//                System.out.println("TRUE");
//                System.out.println("TRUE");
//                System.out.println(xml.substring(xml.indexOf("</") + 2 , xml.indexOf(">")));
//                break;
//            }
//
//
//        }
//
//      //  Class<?> clazz = Class.forName("business." + className).getConstructor().newInstance();
//
//
//        System.out.println("REALLY END");
//        System.out.println(object);
//
//      //  return object;
//        return new XmlAndString(xml, topLevelClassName, object);


}

