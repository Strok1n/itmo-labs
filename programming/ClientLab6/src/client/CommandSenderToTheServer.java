package client;

import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class CommandSenderToTheServer
{
    public CommandExecutionResultDTO sendCommandDTOToTheServer(CommandDTO commandDTO) throws IOException {





        // byte arr[] = {0,1,2,3,4,5,6,7,8,9};

        byte[] arr = new byte[2000];
        byte[] arr3 = serialize(commandDTO);

        for (int j = 0; j < Math.min(arr.length, arr3.length); j++)
        {
            arr[j] = arr3[j];
            System.out.println(arr3[j]);
        }


        int len = arr.length; DatagramChannel dc;
        ByteBuffer buf;
        InetAddress host = InetAddress.getLocalHost(); int port;
        SocketAddress addr;


        addr = new InetSocketAddress(host,80);
        dc = DatagramChannel.open();


        //dc.configureBlocking(true);


        buf = ByteBuffer.wrap(arr);



        dc.send(buf, addr);

        buf.clear();
        addr = dc.receive(buf);
        for (byte j : arr) {    System.out.println(j); }
        Object received = deserialize(arr);
        System.out.println(received);

      //  return Server.serverEntryPoint.response(commandDTO);
        return (CommandExecutionResultDTO) received;
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























