package server.business;

public class Coordinates {
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
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
