import contract.dto.CommandDTOWrapper;
import contract.dto.CommandExecutionResultDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.RegisterCommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.RegisterCommandExecutionResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.Params1;
import server.Server;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;

public class Main {

    static public Connection connection;

    public static void main(String[] args) throws IOException {


        try {
             connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studs",
                    "root"
                    ,"1234"
            );


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Server.connection = connection;
        new Server();


        String sql = "select id from User";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            int nId=0;

            if(rs.next())
                nId = rs.getInt(1);
            System.out.println(nId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




        byte arr[] = new byte[13000];
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



        Executors.newCachedThreadPool();

      //  Selector sel = Selector.open();

       // dc.register(sel, SelectionKey.OP_READ);

       // SelectionKey key = dc.register(sel, SelectionKey.OP_READ);

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
                    if (Objects.equals(st, "save"))
                    {
                        Server.server.save();
                    }
                }
            }
        };
        thread.start();

        ExecutorService exService = Executors.newFixedThreadPool(100);
        ExecutorService exService2 = Executors.newFixedThreadPool(100);

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();


        TransferQueue<String> transferQueue = new LinkedTransferQueue<>();





        while (true) {

            buf = ByteBuffer.wrap(arr);
            addr = dc.receive(buf);

//            for (int j = 0; j < len; j++) {
//                arr[j] *= 3;
//            }

            byte[] arr1 = new byte[13000];
            for (int i = 0; i < arr.length; i++) {
                arr1[i] = arr[i];
            }
            ByteBuffer buf1 = ByteBuffer.wrap(arr1);
           // buf1.flip();
            buf1.position(13000);
            SocketAddress addr1 = addr;




            if (arr[0] != 0 && arr[1] != 0) {

//                Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        // CommandDTO commandDTO = (CommandDTO) deserialize(arr1);
//
//                        //1
//                        CommandDTOWrapper wrapper = (CommandDTOWrapper) deserialize(arr1);
//                        CommandDTO commandDTO = wrapper.getCommandDTO();
//
//                        //2
//                        if (commandDTO instanceof RegisterCommandDTO)
//                        {
//                            try {
//                                PreparedStatement ps = connection.prepareStatement("" +
//                                        "INSERT INTO User (name, password) VALUES (?, ?);");
//                                ps.setString(1, wrapper.getUser());
//                                ps.setString(2, wrapper.getPwd());
//                                ps.execute();
//                            } catch (SQLException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                        try {
//                            PreparedStatement ps = connection.prepareStatement( "SELECT id\n" +
//                                    "FROM User\n" +
//                                    "WHERE EXISTS (SELECT id FROM User WHERE User.name = ? AND User.password = ?);");
//                            ps.setString(1, wrapper.getUser());
//                            ps.setString(2, wrapper.getPwd());
//                           ResultSet set = ps.executeQuery();
//                            System.out.println(set.isBeforeFirst());
//                            if (!set.isBeforeFirst())
//                            {
//                                wrapper.setUserExists(false);
//                                if (wrapper.getCommandDTO() instanceof RegisterCommandDTO)
//                                    wrapper.setUserExists(true);
//                            }else {
//                                wrapper.setUserExists(true);
//                            }
//                        } catch (SQLException e) {
//                            throw new RuntimeException(e);
//                        }
//                        CommandExecutionResultDTO commandExecutionResultDTO; //= Server.server.response(commandDTO);
//                        Logger logger = LoggerFactory.getLogger(Server.server.getClass());
//                        if (wrapper.isUserExists()) {
//                            CommandExecutionResultDTOWrapper commandExecutionResultDTOWrapper = Server.server.response(commandDTO, wrapper.getUser(), wrapper.getPwd());
//                            commandExecutionResultDTO = commandExecutionResultDTOWrapper.getCommandExecutionResultDTO();
//                            if (commandExecutionResultDTOWrapper.isDataMutationLegal())
//                            {
//                                logger.info("Команда {} выполнена", wrapper.getCommandDTO().getCommandName());
//                            }else {
//                                logger.info("Команда {} не выполнена, так как операция изменяет данные, к которым у пользователя нет досутпа", wrapper.getCommandDTO().getCommandName());
//                            }
//                        }
//                        else {
//                            commandExecutionResultDTO = new RegisterCommandExecutionResultDTO("" +
//                                    "Команда не выполнена, так как пользователь не зарегистрирован");
//                            logger.info("Команда {} не выполнена, так как пользователь не зарегистрирован", wrapper.getCommandDTO().getCommandName());
//                        }
//                        // System.out.println(commandExecutionResultDTO.getCommandName());
//                       //  Logger logger = LoggerFactory.getLogger(Server.server.getClass());
//                        //logger.info("Команда {} выполнена", commandExecutionResultDTO.getCommandName());
//                        byte[] arr2 = serialize(commandExecutionResultDTO);
//                        for (int j = 0; j < Math.min(len, arr2.length); j++) {
//                            arr1[j] = arr2[j];
//                        }
//                        buf1.flip();
//
//                        //3
//                        try {
//                            dc.send(buf1, addr1);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                        Arrays.fill(arr1, (byte) 0);
//
//                    }
//
//                };


                Callable<CommandDTOWrapper> callable0 = ()->
                {
//                    Future<Params1> future1 = exService.submit(callable1);
//                    Params1 params1 = future1.get();

                    CommandDTOWrapper wrapper = (CommandDTOWrapper) deserialize(arr1);
                   // CommandDTO commandDTO = wrapper.getCommandDTO();

                    return wrapper;
                };



                Callable<Params1> callable1 = ()-> {

                    Future<CommandDTOWrapper> future0 = exService2.submit(callable0);
                    CommandDTOWrapper wrapper = future0.get();

                    // CommandDTO commandDTO = (CommandDTO) deserialize(arr1);

                    //1
                //    CommandDTOWrapper wrapper = (CommandDTOWrapper) deserialize(arr1);
                    CommandDTO commandDTO = wrapper.getCommandDTO();

                    //2
                    if (commandDTO instanceof RegisterCommandDTO) {
                        try {
                            PreparedStatement ps11 = connection.prepareStatement("" +
                                    "INSERT INTO User (name, password) VALUES (?, ?);");
                            ps11.setString(1, wrapper.getUser());
                            ps11.setString(2, wrapper.getPwd());
                            ps11.execute();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        PreparedStatement ps12 = connection.prepareStatement("SELECT id\n" +
                                "FROM User\n" +
                                "WHERE EXISTS (SELECT id FROM User WHERE User.name = ? AND User.password = ?);");
                        ps12.setString(1, wrapper.getUser());
                        ps12.setString(2, wrapper.getPwd());
                        ResultSet set = ps12.executeQuery();
                        System.out.println(set.isBeforeFirst());
                        if (!set.isBeforeFirst()) {
                            wrapper.setUserExists(false);
                            if (wrapper.getCommandDTO() instanceof RegisterCommandDTO)
                                wrapper.setUserExists(true);
                        } else {
                            wrapper.setUserExists(true);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    CommandExecutionResultDTO commandExecutionResultDTO; //= Server.server.response(commandDTO);
                    Logger logger = LoggerFactory.getLogger(Server.server.getClass());
                    if (wrapper.isUserExists()) {
                        CommandExecutionResultDTOWrapper commandExecutionResultDTOWrapper = Server.server.response(commandDTO, wrapper.getUser(), wrapper.getPwd());
                        commandExecutionResultDTO = commandExecutionResultDTOWrapper.getCommandExecutionResultDTO();
                        if (commandExecutionResultDTOWrapper.isDataMutationLegal()) {
                            logger.info("Команда {} выполнена", wrapper.getCommandDTO().getCommandName());
                        } else {
                            logger.info("Команда {} не выполнена, так как операция изменяет данные, к которым у пользователя нет досутпа", wrapper.getCommandDTO().getCommandName());
                        }
                    } else {
                        commandExecutionResultDTO = new RegisterCommandExecutionResultDTO("" +
                                "Команда не выполнена, так как пользователь не зарегистрирован");
                        logger.info("Команда {} не выполнена, так как пользователь не зарегистрирован", wrapper.getCommandDTO().getCommandName());
                    }
                    // System.out.println(commandExecutionResultDTO.getCommandName());
                    //  Logger logger = LoggerFactory.getLogger(Server.server.getClass());
                    //logger.info("Команда {} выполнена", commandExecutionResultDTO.getCommandName());
                    byte[] arr2 = serialize(commandExecutionResultDTO);
                    for (int j = 0; j < Math.min(len, arr2.length); j++) {
                        arr1[j] = arr2[j];
                    }
                    buf1.flip();

                    //3
                    try {
                        dc.send(buf1, addr1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Arrays.fill(arr1, (byte) 0);
                    return new Params1(buf1, addr1);
                };


                Callable<Integer> callable2 = ()->
                {
                    Future<Params1> future1 = exService.submit(callable1);
                    Params1 params1 = future1.get();
                    try {
                        dc.send(buf1, addr1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Arrays.fill(arr1, (byte) 0);
                    Logger logger = LoggerFactory.getLogger(Server.server.getClass());
                    logger.info("Команда {} sended, так как пользователь не зарегистрирован", "123");
                   return 0;
                };


                Future<Integer> future2 = forkJoinPool.submit(callable2);

//                try {
//                    future2.get();
//
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                } catch (ExecutionException e) {
//                    throw new RuntimeException(e);
//                }




            //    forkJoinPool.execute(runnable);

             // new Thread(runnable).start();

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