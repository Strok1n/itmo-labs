package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;

public class TypeUtilities
{
    public static boolean isWrapper(Type type)
    {
        return type.equals(Integer.class) ||
                type.equals(Float.class) ||
                type.equals(Double.class) ||
                type.equals(Byte.class) ||
                type.equals(Short.class) ||
                type.equals(Long.class) ||
                type.equals(Boolean.class) ||
                type.equals(Character.class);
    }

    public static boolean isString(Type type)
    {
        return type.equals(String.class);
    }

    public static boolean isPrimitiveOrWrapperOrString(Type type)
    {
        return type.equals(int.class) ||
                type.equals(float.class) ||
                type.equals(double.class) ||
                type.equals(byte.class) ||
                type.equals(short.class) ||
                type.equals(long.class) ||
                type.equals(boolean.class) ||
                type.equals(char.class) ||
                isWrapper(type) ||
                isString(type);
    }

    public static Object StringToSpecifiedFieldType(String string, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        if (field.getType().isEnum())
        {
            Method valueOf = field.getType().getMethod("valueOf", String.class);
            return valueOf.invoke(field.getType(), string);
        }
        return switch (field.getType().getTypeName())
                {
                    case "java.lang.Integer", "int" -> Integer.parseInt(string);
                    case "java.lang.Long", "long" -> Long.parseLong(string);
                    case "java.lang.Float", "float" -> Float.parseFloat(string);
                    case "java.lang.Double", "double" -> Double.parseDouble(string);
                    case "java.lang.String" -> string;
                    case "java.time.ZonedDateTime" -> ZonedDateTime.parse(string);
                    default -> null;
                };
    }


}
