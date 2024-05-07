import client.Client;
import client.util.ClientInitializer;

public class Main {
    public static void main(String[] args) {

        Client client = ClientInitializer.initializeClient();
        client.start();

    }
}