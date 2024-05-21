package client;

import client.io.ConsoleReader;
import client.io.ConsoleWriter;
import client.util.ClientInitializer;
import client.util.CommandDTOExtractor;
import contract.CommandName;
import contract.dto.CommandDTOWrapper;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Client
{
    final private ConsoleReader consoleReader;
    final private ConsoleWriter consoleWriter;
    private final CommandPreparerForSendingToTheServer commandPreparerForSendingToTheServer;
    private final CommandSenderToTheServer commandSenderToTheServer;
    private final OutputStringBuilder outputStringBuilder;

    private ArrayDeque<CommandName> history;


    public Client()
    {
        this.history = new ArrayDeque<>();
        this.consoleReader = ClientInitializer.initializeConsoleReader();
        this.consoleWriter = ClientInitializer.initializeConsoleWriter();
        this.commandPreparerForSendingToTheServer = ClientInitializer.initializeCommandPreparerForSendingToTheServer(this.consoleReader, this.consoleWriter);
        this.commandSenderToTheServer = ClientInitializer.initializeCommandSenderToTheServer();
        this.outputStringBuilder = ClientInitializer.initializeOutputStringBuilder(history);
    }
    public void start() throws InterruptedException {
        try
        {
            while (true)
            {
                try
                {
                    this.consoleWriter.printlnToTheOutputStream("Введите команду:");
                    String input = this.consoleReader.getNextLine();

                    CommandName commandName = this.getCommandNameFromInputString(input);
                    String[] commandArguments = this.getCommandArgumentsFromInputString(input);

                    this.consoleWriter.printlnToTheOutputStream("Введите имя пользователя:");
                    String userName = this.consoleReader.getNextLine();
                    this.consoleWriter.printlnToTheOutputStream("Введите пароль:");
                    String passwd = this.consoleReader.getNextLine();

                    System.out.println(encryptThisString(passwd));



                    CommandDTO commandDTO = this.commandPreparerForSendingToTheServer.prepareCommandDTOForSending(commandName, commandArguments);
                    CommandDTOExtractor commandDTOExtractor = new CommandDTOExtractor(commandDTO);

                    for (CommandDTO cmdDTO: commandDTOExtractor.getCommandDTOList())
                    {
                        //CommandExecutionResultDTO commandExecutionResultDTO = this.commandSenderToTheServer.sendCommandDTOToTheServer(cmdDTO);
                        CommandSenderToTheServer sender = this.commandSenderToTheServer;
                        OutputStringBuilder builder = this.outputStringBuilder;
                        ConsoleWriter writer = this.consoleWriter;
                        Timer timer = new Timer();

                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                try {

                                    Timer timer1 = new Timer();
                                    timer1.schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            consoleWriter.printlnToTheOutputStream(
                                                    "Время ожидания ответа сервера истекло. Команда " + commandDTO.getCommandName()
                                                            +" не выполнена"
                                            );
                                            consoleWriter.printlnToTheOutputStream("Введите команду:");
                                        }
                                    }, 5000);


                                    CommandDTOWrapper wrapper =
                                            new CommandDTOWrapper(userName,encryptThisString(passwd)
                                                    ,commandDTO, false
                                                    );
//                                    CommandExecutionResultDTO result =
//                                            sender.sendCommandDTOToTheServer(commandDTO);
                                    CommandExecutionResultDTO result =
                                            sender.sendCommandDTOToTheServer(wrapper);

                                    timer1.cancel();
                                    String str = builder.buildOutputString(result);
                                    consoleWriter.printlnToTheOutputStream(str);
                                    consoleWriter.printlnToTheOutputStream("Введите команду:");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        };


                        timer.schedule(task, 0);
                       // task.wait();


                        //String outputString = this.outputStringBuilder.buildOutputString(commandExecutionResultDTO);
                      //  history.add(CommandName.valueOf(commandExecutionResultDTO.getCommandName()));
                       // if (history.size() > 8)
                         //   history.removeFirst();
                      //  this.consoleWriter.printlnToTheOutputStream(outputString);
                    }
                }catch (IllegalArgumentException illegalArgumentException)
                {
                    this.consoleWriter.printlnToTheOutputStream("Неизвестная команда");
                }
            }
        }
        catch (NoSuchElementException noSuchElementException)
        {
            throw noSuchElementException;
          //  this.consoleWriter.printlnToTheOutputStream("Программа завершена из-за нажатия ctrl + D");
        }
    }
    CommandName getCommandNameFromInputString(String inputString)
    {
        String[] tokens = inputString.split(" ");
        return CommandName.valueOf(tokens[0].trim());
    }
    String[] getCommandArgumentsFromInputString(String inputString)
    {
        String[] tokens = inputString.split(" ");
        String[] commandArguments = Arrays.copyOfRange(tokens, 1, tokens.length);
        for (int i = 0; i < commandArguments.length; i++)
            commandArguments[i] = commandArguments[i].trim();
        return commandArguments;
    }








    public void handle(String command)
    {

        try
        {
            // this.consoleWriter.printlnToTheOutputStream("Введите команду:");
            // String input = this.consoleReader.getNextLine();

            CommandName commandName = this.getCommandNameFromInputString(command);
            String[] commandArguments = this.getCommandArgumentsFromInputString(command);




            CommandDTO commandDTO = this.commandPreparerForSendingToTheServer.prepareCommandDTOForSending(commandName, commandArguments);
            CommandDTOExtractor commandDTOExtractor = new CommandDTOExtractor(commandDTO);

            for (CommandDTO cmdDTO: commandDTOExtractor.getCommandDTOList())
            {
                //CommandExecutionResultDTO commandExecutionResultDTO = this.commandSenderToTheServer.sendCommandDTOToTheServer(cmdDTO);
                CommandSenderToTheServer sender = this.commandSenderToTheServer;
                OutputStringBuilder builder = this.outputStringBuilder;
                ConsoleWriter writer = this.consoleWriter;
                Timer timer = new Timer();

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        try {

                            Timer timer1 = new Timer();
                            timer1.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    consoleWriter.printlnToTheOutputStream(
                                            "Время ожидания ответа сервера истекло. Команда " + commandDTO.getCommandName()
                                                    +" не выполнена"
                                    );
                                    consoleWriter.printlnToTheOutputStream("Введите команду:");
                                }
                            }, 5000);


                            CommandExecutionResultDTO result =
                                    sender.sendCommandDTOToTheServer(commandDTO);

                            timer1.cancel();
                            String str = builder.buildOutputString(result);
                            consoleWriter.printlnToTheOutputStream(str);
                            consoleWriter.printlnToTheOutputStream("Введите команду:");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };


                timer.schedule(task, 0);
                // task.wait();


                //String outputString = this.outputStringBuilder.buildOutputString(commandExecutionResultDTO);
                //  history.add(CommandName.valueOf(commandExecutionResultDTO.getCommandName()));
                // if (history.size() > 8)
                //   history.removeFirst();
                //  this.consoleWriter.printlnToTheOutputStream(outputString);
            }
        }catch (IllegalArgumentException illegalArgumentException)
        {
            this.consoleWriter.printlnToTheOutputStream("Неизвестная команда");
        }




    }






    public static String encryptThisString(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-384
            MessageDigest md = MessageDigest.getInstance("SHA-384");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



}
