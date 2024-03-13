package business;

import util.OtherUtilities;

public class Coordinates
{
    private float x;
    private long y; // > -161

    public Coordinates(){}

    public Coordinates(float x, long y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString()
    {
        try {
            return OtherUtilities.objectToJson(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//        String s = "{\n";
//            for (Field field : this.getClass().getDeclaredFields()) {
//                try {
//                    s += "\t\"" + field.getName() + "\": " + field.get(this) + ",\n";
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//        return s + "}";
        return null;
    }
}
