package server.business;

public class Discipline {
    private int id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", labsCount=" + labsCount +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabsCount(int labsCount) {
        this.labsCount = labsCount;
    }
    public Discipline(){}
}