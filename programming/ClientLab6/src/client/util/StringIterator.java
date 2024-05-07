package client.util;

public class StringIterator{
    private int i;

    public StringIterator(){
        i = 0;
    }
    public int getI() {return i;}
    public void setI(int i) {this.i = i;}

    public int increment()
    {
        return ++i;
    }
}