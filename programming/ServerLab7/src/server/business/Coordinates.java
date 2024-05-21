package server.business;

public class Coordinates {
    private int id;
    private int x;
    private Integer y; //Значение поля должно быть больше -161, Поле не может быть null

    public Coordinates(int x, Integer y)
    {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return "Coordinates{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Coordinates(){}
}
