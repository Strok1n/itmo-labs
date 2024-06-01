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

        client.start();


//        Thread thread = new Thread(()->{
//                client.handle("help");
//        client.handle("help");
//        client.handle("help");
//
//      ;});
//
//        while (true) {
//            client.handle("help");
//        }
    }

}
