package server.business;

public class Discipline {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int labsCount;

    public Discipline(String name, int labsCount)
    {
        this.name = name;
        this.labsCount = labsCount;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "name='" + name + '\'' +
                ", labsCount=" + labsCount +
                '}';
    }
}