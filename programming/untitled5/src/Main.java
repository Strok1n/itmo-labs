import client.InputHandler;
import client.MainClientProgram;


public class Main
{
    public static void main(String[] args)
    {

        MainClientProgram mainClientProgram = new MainClientProgram(
                System.out,
                System.in
        );

        mainClientProgram.start();

    }
}