import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import server.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {

        byte arr[] = new byte[2000];


        int len = arr.length;
        DatagramChannel dc;

        ByteBuffer buf;
        // ByteBuffer buf = ByteBuffer.allocate(2000);

        InetAddress host;
        int port = 80;
        SocketAddress addr;
        addr = new InetSocketAddress(port);



        dc = DatagramChannel.open();
        dc.configureBlocking(false);
        dc.bind(addr);

        Selector sel = Selector.open();

        dc.register(sel, SelectionKey.OP_READ);

        SelectionKey key = dc.register(sel, SelectionKey.OP_READ);


        while (true) {
            buf = ByteBuffer.wrap(arr);
            addr = dc.receive(buf);

//            for (int j = 0; j < len; j++) {
//                arr[j] *= 3;
//            }

            if (arr[0] != 0 && arr[1] != 0) {

                CommandDTO commandDTO = (CommandDTO) deserialize(arr);
                CommandExecutionResultDTO commandExecutionResultDTO = Server.server.response(commandDTO);
                System.out.println(commandExecutionResultDTO.getCommandName());

                byte[] arr2 = serialize(commandExecutionResultDTO);

                for (int j = 0; j < Math.min(len, arr2.length); j++) {
                    arr[j] = arr2[j];
              //      System.out.println(arr2[j]);
                }
                buf.flip();
                dc.send(buf, addr);

                Arrays.fill(arr, (byte) 0);

            }
        }



    }

    static byte[] serialize(final Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(obj);
            out.flush();
            return bos.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    static Object deserialize(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        try (ObjectInput in = new ObjectInputStream(bis)) {
            return in.readObject();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}