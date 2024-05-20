//package tests;
//
//import client.CommandPreparerForSendingToTheServer;
//import client.OutputStringBuilder;
//import client.io.ConsoleReader;
//import client.io.ConsoleWriter;
//import contract.CommandName;
//import contract.dto.commanddto.concrete.*;
//import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
//import contract.dto.commandexecutionresultdto.concrete.InfoCommandExecutionResultDTO;
//import contract.dto.commandexecutionresultdto.concrete.SaveCommandExecutionResultDTO;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.ArrayDeque;
//
//public class OutputStringBuilderTest {
//    OutputStringBuilder outputStringBuilder;
//
//    @BeforeEach
//    public void setUp(){
//        outputStringBuilder =
//                new OutputStringBuilder(new ArrayDeque<>());
//    }
//
//    @Test
//    @DisplayName("testCommandDTOBuildersMap")
//    public void testCommandExecutionResultDTOHandlersMap(){
//        Assertions.assertEquals(
//                outputStringBuilder.buildOutputString(new AddCommandExecutionResultDTO("Лабораторная работа успешно добавлена в коллекцию")),
//                "Лабораторная работа успешно добавлена в коллекцию"
//        );
//
//        LocalDate localDate = LocalDate.now();
//
//        Assertions.assertEquals(
//                outputStringBuilder.buildOutputString(new InfoCommandExecutionResultDTO("HashSet", localDate, 10)),
//                "Тип коллекции: " + "HashSet" + "\n" +
//                        "Дата инициализации коллекции: " +
//                        localDate + "\n" +
//                        "Количество элементов в коллекции: " +
//                        10
//        );
//        Assertions.assertEquals(
//                outputStringBuilder.buildOutputString(new SaveCommandExecutionResultDTO("Коллекция успешно сохранена в файл")),
//                "Коллекция успешно сохранена в файл"
//        );
//    }
//}
