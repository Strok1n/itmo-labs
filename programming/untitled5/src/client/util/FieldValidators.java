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
    static public boolean validateLabWorkCoordinatesY(Integer y)
    {
        return y != null && y > -161;
    }

    static public boolean validateLabWorkMinimalPoint(Double minimalPoint)
    {
        return minimalPoint != null && minimalPoint > 0;
    }

    static public boolean validateLabWorkTunedInWorks(Long tunedInWorks)
    {
        return true;
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
}
