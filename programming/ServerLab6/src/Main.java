import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import server.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Main {
    public static void main(String[] args) throws IOException {

        byte arr[] = new byte[2000];


        int len = arr.length;
        DatagramChannel dc;
        ByteBuffer buf;
        InetAddress host;
        int port = 80;
        SocketAddress addr;

        addr = new InetSocketAddress(port);
        dc = DatagramChannel.open();
        dc.bind(addr);


        while (true) {
            buf = ByteBuffer.wrap(arr);
            addr = dc.receive(buf);

//            for (int j = 0; j < len; j++) {
//                arr[j] *= 3;
//            }


            CommandDTO commandDTO = (CommandDTO) deserialize(arr);
//
            CommandExecutionResultDTO commandExecutionResultDTO = Server.server.response(commandDTO);
//
            System.out.println(commandExecutionResultDTO.getCommandName());

            byte[] arr2 = serialize(commandExecutionResultDTO);
            System.out.println(arr.length);
            System.out.println(arr2.length);

            for (int j = 0; j < Math.min(len, arr2.length); j++) {
                arr[j] = arr2[j];
                System.out.println(arr2[j]);
            }

            buf.flip();

            dc.send(buf, addr);
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