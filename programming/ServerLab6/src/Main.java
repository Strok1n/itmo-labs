import com.sun.jdi.VoidType;
import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.*;

public class Main {

    static private LinkedTransferQueue<CommandDTO> commandDTOLinkedTransferQueue = new LinkedTransferQueue<>();
    static private LinkedTransferQueue<CommandExecutionResultDTO> commandExecutionResultDTOLinkedTransferQueue = new LinkedTransferQueue<>();

    static private ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    static private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    static private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);


    public static void main(String[] args) throws IOException {




        byte arr[] = new byte[2000];
        int len = arr.length;
        DatagramChannel dc;
        ByteBuffer buf;
        // ByteBuffer buf = ByteBuffer.allocate(2000);
        InetAddress host;
        int port = 7341;
        SocketAddress addr;
        addr = new InetSocketAddress(port);
        dc = DatagramChannel.open();
        dc.configureBlocking(false);
        dc.bind(addr);

        Selector sel = Selector.open();

        dc.register(sel, SelectionKey.OP_READ);

        SelectionKey key = dc.register(sel, SelectionKey.OP_READ);

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true)
                {
                    Scanner scanner=
                            new Scanner(System.in);
                    String st = scanner.next();
                    if (Objects.equals(st, "exit"))
                    {
                        Server.server.save();
                        System.exit(0);
                    }
//                    if (Objects.equals(st, "save"))
//                    {
//                        Server.server.save();
//                    }
                }
            }
        };
        thread.start();
//
//        LinkedTransferQueue<CommandDTO> commandDTOLinkedTransferQueue = new LinkedTransferQueue<>();
//        LinkedTransferQueue<CommandExecutionResultDTO> commandExecutionResultDTOLinkedTransferQueue = new LinkedTransferQueue<>();
//



        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();












        while (true) {
            buf = ByteBuffer.wrap(arr);
            addr = dc.receive(buf);

//            for (int j = 0; j < len; j++) {
//                arr[j] *= 3;
//            }

            if (arr[0] != 0 && arr[1] != 0) {

                // CommandDTO commandDTO = (CommandDTO) deserialize(arr);

//
//                Thread requestProducer = new Thread(
//                        ()->
//                        {
//                                if (arr[0] != 0 && arr[1] != 0) {
//                                    CommandDTOWrapper commandDTO = (CommandDTOWrapper) deserialize(arr);
//                                    boolean added = commandDTOLinkedTransferQueue.tryTransfer(commandDTO);
//                                    if(added)
//                                        System.out.println("" + " transferred 2");
//                                    }
//                        }
//                );
//
//                requestProducer.start();
//
//                Thread requestHandler = new Thread(
//                        ()->
//                        {
//                                try {
//                                    CommandDTO commandDTO = commandDTOLinkedTransferQueue.take();
////                                    if (commandDTO != null)
////                                    {
//                                        System.out.println("OK");
//                                        CommandExecutionResultDTO commandExecutionResultDTO = Server.server.response(commandDTO);
//                                    boolean added = commandExecutionResultDTOLinkedTransferQueue.tryTransfer(commandExecutionResultDTO);
//                                    if(added)
//                                        System.out.println("" + " transferred 2");
//                                   // }
//                                } catch (InterruptedException e) {
//                                    throw new RuntimeException(e);
//                                }
//                        }
//                );
//
//                ByteBuffer finalBuf = buf;
//                SocketAddress finalAddr = addr;
//                Thread responseSender = new Thread(
//                        ()->
//                        {
//                            try {
//                                CommandExecutionResultDTO commandExecutionResultDTO = commandExecutionResultDTOLinkedTransferQueue.take();
//
//
//                                Logger logger = LoggerFactory.getLogger(Server.server.getClass());
//                                logger.info("Команда {} выполнена", commandExecutionResultDTO.getCommandName());
//
//                                byte[] arr2 = serialize(commandExecutionResultDTO);
//                                for (int j = 0; j < Math.min(len, arr2.length); j++) {
//                                    arr[j] = arr2[j];
//                                }
//                                finalBuf.flip();
//                                dc.send(finalBuf, finalAddr);
//                                Arrays.fill(arr, (byte) 0);
//
//
//
//                            } catch (InterruptedException | IOException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                );
//
//                requestHandler.start();
//                responseSender.start();
//





                byte[] arr2= new byte[2000];
                System.arraycopy(arr, 0, arr2, 0, arr.length);
                ByteBuffer buf2 = ByteBuffer.wrap(arr2);
                buf2.position(2000);
                SocketAddress addr2 = addr;
                handleRequest(arr2, len, buf2, dc, addr2);
                Arrays.fill(arr, (byte) 0);






//                CommandDTOWrapper commandDTO = (CommandDTOWrapper) deserialize(arr);
//                CommandExecutionResultDTO commandExecutionResultDTO = Server.server.response(commandDTO);
//                Logger logger = LoggerFactory.getLogger(Server.server.getClass());
//                logger.info("Команда {} выполнена", commandExecutionResultDTO.getCommandName());
//                byte[] arr2 = serialize(commandExecutionResultDTO);
//                for (int j = 0; j < Math.min(len, arr2.length); j++) {
//                    arr[j] = arr2[j];
//                }
//                buf.flip();
//                dc.send(buf, addr);
//                Arrays.fill(arr, (byte) 0);
            }
        }


    }

    static void handleRequest(byte[] arr, int len, ByteBuffer buf, DatagramChannel dc, SocketAddress addr)
    {
//        Thread requestProducer = new Thread(
//                ()->
//                {
//                        if (arr[0] != 0 && arr[1] != 0) {
//                            CommandDTOWrapper commandDTO = (CommandDTOWrapper) deserialize(arr);
//                            boolean added = commandDTOLinkedTransferQueue.tryTransfer(commandDTO);
//                            if(added) {
//                                System.out.println("" + " transferred 2");
//                                Arrays.fill(arr, (byte) 0);
//                            }
//                    }
//                }
//        );
//
//        Thread requestHandler = new Thread(
//                ()->
//                        {
//                            try {
//                                CommandDTO commandDTO = commandDTOLinkedTransferQueue.take();
////                                    if (commandDTO != null)
////                                    {
//                                        System.out.println("OK");
//                                        CommandExecutionResultDTO commandExecutionResultDTO = Server.server.response(commandDTO);
//                                    boolean added = commandExecutionResultDTOLinkedTransferQueue.tryTransfer(commandExecutionResultDTO);
//                                    if(added)
//                                        System.out.println("" + " transferred 2");
//                                   // }
//                                } catch (InterruptedException e) {
//                                    throw new RuntimeException(e);
//                                }
//                        }
//                );
//
//        Thread responseSender = new Thread(
//                        ()->
//                        {
//                            try {
//                                CommandExecutionResultDTO commandExecutionResultDTO = commandExecutionResultDTOLinkedTransferQueue.take();
//                                Logger logger = LoggerFactory.getLogger(Server.server.getClass());
//                                logger.info("Команда {} выполнена", commandExecutionResultDTO.getCommandName());
//
//                                byte[] arr2 = serialize(commandExecutionResultDTO);
//                                for (int j = 0; j < Math.min(len, arr2.length); j++) {
//                                    arr[j] = arr2[j];
//                                }
//                                buf.flip();
//                                dc.send(buf, addr);
//                               // Arrays.fill(arr, (byte) 0);
//                            } catch (InterruptedException | IOException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                );

        Callable<CommandDTOWrapper> requestProducer =
                ()->
                {
                    CommandDTOWrapper commandDTO = (CommandDTOWrapper)deserialize(arr);
                    return commandDTO;
                };

        Callable<CommandExecutionResultDTO> requestHandler =
                ()->
                {
                    Future<CommandDTOWrapper> request = forkJoinPool.submit(requestProducer);

                    CommandDTOWrapper commandDTO = request.get();
                    return  Server.server.response(commandDTO);
                };

        Callable<Integer> responseSender =
                ()->
                {
                    Future<CommandExecutionResultDTO> request = cachedThreadPool.submit(requestHandler);

                    CommandExecutionResultDTO commandExecutionResultDTO = request.get();
                    Logger logger = LoggerFactory.getLogger(Server.server.getClass());
                    logger.info("Команда {} выполнена", commandExecutionResultDTO.getCommandName());
                    byte[] arr2 = serialize(commandExecutionResultDTO);
                    for (int j = 0; j < Math.min(len, arr2.length); j++) {
                        arr[j] = arr2[j];
                    }
                    buf.flip();
                    dc.send(buf, addr);
                    Arrays.fill(arr, (byte) 0);
                    return 0;
                };


//                CommandDTOWrapper commandDTO = (CommandDTOWrapper) deserialize(arr);
//                CommandExecutionResultDTO commandExecutionResultDTO = Server.server.response(commandDTO);
//                Logger logger = LoggerFactory.getLogger(Server.server.getClass());
//                logger.info("Команда {} выполнена", commandExecutionResultDTO.getCommandName());
//                byte[] arr2 = serialize(commandExecutionResultDTO);
//                for (int j = 0; j < Math.min(len, arr2.length); j++) {
//                    arr[j] = arr2[j];
//                }
//                buf.flip();
//                dc.send(buf, addr);
//                Arrays.fill(arr, (byte) 0);
//


//        forkJoinPool.execute(requestProducer);
//        cachedThreadPool.execute(requestHandler);
        fixedThreadPool.submit(responseSender);

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