package business;

import util.OtherUtilities;

public class Discipline
{
    private String name; // != null, != ""
    private Integer labsCount; //!= null

    public Discipline(){}

    public Discipline(String name, Integer labsCount)
    {
        this.name = name;
        this.labsCount = labsCount;
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
