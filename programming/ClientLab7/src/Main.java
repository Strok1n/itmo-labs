import client.Client;
import client.util.ClientInitializer;
import org.junit.jupiter.api.extension.TestInstanceFactory;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import tests.CommandPreparerForSendingToTheServerTest;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Client client = ClientInitializer.initializeClient();
       // client.start();

        while (true){
            client.handle("info");
        }

    }

}
