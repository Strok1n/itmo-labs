package client.util;

public class FieldValidators
{


    static public boolean validateLabWorkName(String labWorkName)
    {
        return labWorkName != null && !labWorkName.equals("");
    }

    static public boolean validateLabWorkCoordinatesX(int x)
    {
        return true;
    }

    static public boolean validateLabWorkCoordinatesX(String xString)
    {
        int x;
        try {
            x = Integer.parseInt(xString);
        }
        catch (NumberFormatException numberFormatException){
            return false;
        }
        return validateLabWorkCoordinatesX(x);
    }


    static public boolean validateLabWorkCoordinatesY(Integer y)
    {
        return y != null && y > -161;
    }

    static public boolean validateLabWorkCoordinatesY(String yString)
    {
        Integer y;
        try {
            y = Integer.parseInt(yString);
        }
        catch (NumberFormatException numberFormatException){
            return false;
        }
        return validateLabWorkCoordinatesY(y);
    }


    static public boolean validateLabWorkMinimalPoint(Double minimalPoint)
    {
        return minimalPoint != null && minimalPoint > 0;
    }

    static public boolean validateLabWorkMinimalPoint(String minimalPointString)
    {
        Double minimalPoint;
        try {
            minimalPoint = Double.parseDouble(minimalPointString);
        }
        catch (NumberFormatException numberFormatException){
            return false;
        }
        return validateLabWorkMinimalPoint(minimalPoint);
    }


    static public boolean validateLabWorkTunedInWorks(Long tunedInWorks)
    {
        return true;
    }

    static public boolean validateLabWorkTunedInWorks(String tunedInWorksString)
    {
        Long tunedInWorks;
        try {
            tunedInWorks = Long.parseLong(tunedInWorksString);
        }
        catch (NumberFormatException numberFormatException){
            return false;
        }
        return validateLabWorkTunedInWorks(tunedInWorks);
    }


    static public boolean validateLabWorkDifficulty(String difficulty)
    {
        return difficulty.equals("VERY_EASY") ||
                difficulty.equals("EASY") ||
                difficulty.equals("VERY_HARD") ||
                difficulty.equals("INSANE");
    }

    static public boolean validateLabWorkDisciplineName(String disciplineName)
    {
        return disciplineName != null && !disciplineName.equals("");
    }

    static public boolean validateLabWorkDisciplineLabsCount(int disciplineLabsCount)
    {
        return true;
    }

    static public boolean validateLabWorkDisciplineLabsCount(String disciplineLabsCountString)
    {
        int disciplineLabsCount;
        try {
            disciplineLabsCount = Integer.parseInt(disciplineLabsCountString);
        }
        catch (NumberFormatException numberFormatException){
            return false;
        }
        return validateLabWorkDisciplineLabsCount(disciplineLabsCount);
    }

}
