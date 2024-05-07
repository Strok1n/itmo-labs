package client.util;

import client.io.ConsoleReader;
import client.io.ConsoleWriter;

public class LabWorkFieldValuesGetter
{
    private final ConsoleReader consoleReader;
    private final ConsoleWriter consoleWriter;

    public LabWorkFieldValuesGetter(ConsoleReader consoleReader, ConsoleWriter consoleWriter)
    {
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
    }


    public int getLabWorkId()
    {
        while (true)
        {
            consoleWriter.printlnToTheOutputStream("Введите id лабораторной работы:");
            String idString = consoleReader.getNextLine();
            int id = -1;
            try
            {
                id = Integer.parseInt(idString);
            }
            catch (NumberFormatException numberFormatException)
            {
                consoleWriter.printlnToTheOutputStream("id должно быть целым числом");
                continue;
            }
            if (FieldValidators.validateLabWorkCoordinatesX(id))
                return id;
        }
    }

    public String getLabWorkName()
    {
        while (true)
        {
            consoleWriter.printlnToTheOutputStream("Введите название лабораторной работы:");
            String name = consoleReader.getNextLine();
            if (FieldValidators.validateLabWorkName(name))
                return name;
            else
                consoleWriter.printlnToTheOutputStream("Название лабораторной работы не может быть null, и не может быть пустым");
        }
    }

    public int getLabWorkCoordinatesX()
    {
        while (true)
        {
            consoleWriter.printlnToTheOutputStream("Введите координату x лабораторной работы:");
            String xString = consoleReader.getNextLine();
            int x = 0;
            try
            {
                x = Integer.parseInt(xString);
            }
            catch (NumberFormatException numberFormatException)
            {
                consoleWriter.printlnToTheOutputStream("x должно быть целым числом");
                continue;
            }
            if (FieldValidators.validateLabWorkCoordinatesX(x))
                return x;
        }
    }

    public Integer getLabWorkCoordinatesY()
    {
        while (true)
        {
            consoleWriter.printlnToTheOutputStream("Введите координату y лабораторной работы:");
            String yString = consoleReader.getNextLine();
            Integer y = 0;
            try
            {
                y = Integer.parseInt(yString);
            }
            catch (NumberFormatException numberFormatException)
            {
                consoleWriter.printlnToTheOutputStream("y должно быть целым числом");
                continue;
            }
            if (FieldValidators.validateLabWorkCoordinatesY(y))
                return y;
            else
                consoleWriter.printlnToTheOutputStream("Значение y должно быть больше -161, y не может быть null");
        }
    }


    public Double getLabWorkMinimalPoint()
    {
        while (true)
        {
            consoleWriter.printlnToTheOutputStream("Введите минимальную точку лабораторной работы:");
            String minimalPointString = consoleReader.getNextLine();
            Double minimalPoint = 0.0;
            try
            {
                minimalPoint = Double.parseDouble(minimalPointString);
            }
            catch (NumberFormatException numberFormatException)
            {
                consoleWriter.printlnToTheOutputStream("Минимальная точка должна быть числом с плавающей точкой");
                continue;
            }
            if (FieldValidators.validateLabWorkMinimalPoint(minimalPoint))
                return minimalPoint;
            else
                consoleWriter.printlnToTheOutputStream("Минимальная точка должна быть больше 0, минимальная точка не может быть null");
        }
    }


    public long getLabWorkTunedInWorks()
    {
        while (true)
        {
            consoleWriter.printlnToTheOutputStream("Введите число настроенных на работу лабораторной работы:");
            String tunedInWorksString = consoleReader.getNextLine();
            long tunedInWorks = 0;
            try
            {
                tunedInWorks = Long.parseLong(tunedInWorksString);
            }
            catch (NumberFormatException numberFormatException)
            {
                consoleWriter.printlnToTheOutputStream("Число настроенных на работу должно быть целым числом");
                continue;
            }
            if (FieldValidators.validateLabWorkTunedInWorks(tunedInWorks))
                return tunedInWorks;
        }
    }

    public String getLabWorkDifficulty()
    {
        while (true)
        {
            consoleWriter.printlnToTheOutputStream("Введите сложность лабораторной работы:");
            String difficulty = consoleReader.getNextLine();
            if (FieldValidators.validateLabWorkDifficulty(difficulty))
                return difficulty;
            else
                consoleWriter.printlnToTheOutputStream("Сложность лабораторной работы должна быть одним из значений: VERY_EASY, EASY, VERY_HARD, INSANE");
        }
    }

    public String getLabWorkDisciplineName()
    {
        while (true)
        {
            consoleWriter.printlnToTheOutputStream("Введите название дисциплины лабораторной работы:");
            String disciplineName = consoleReader.getNextLine();
            if (FieldValidators.validateLabWorkDisciplineName(disciplineName))
                return disciplineName;
            else
                consoleWriter.printlnToTheOutputStream("Название дисциплины лабораторной работы не может быть null, и не может быть пустым");
        }
    }

    public int getLabWorkDisciplineLabsCount()
    {
        while (true)
        {
            consoleWriter.printlnToTheOutputStream("Введите число лабораторных работ дисциплины:");
            String disciplineLabsCountString = consoleReader.getNextLine();
            int disciplineLabsCount = 0;
            try
            {
                disciplineLabsCount = Integer.parseInt(disciplineLabsCountString);
            }
            catch (NumberFormatException numberFormatException)
            {
                consoleWriter.printlnToTheOutputStream("Число лабораторных работ должно быть целым числом");
                continue;
            }
            if (FieldValidators.validateLabWorkDisciplineLabsCount(disciplineLabsCount))
                return disciplineLabsCount;
        }
    }

}
