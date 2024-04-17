package server.commandexecutors.concrete;

import contract.CommandIdentifier;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.HelpCommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;

import java.util.HashMap;
import java.util.Map;

public class HelpCommandExecutor implements CommandExecutor {
    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {
        Map<CommandIdentifier, String> helpMap = new HashMap<>();
        helpMap.put(CommandIdentifier.HELP, "help: вывести справку по доступным командам");
        helpMap.put(CommandIdentifier.INFO, "info: вывести в стандартный поток вывода информацию о коллекции");
        helpMap.put(CommandIdentifier.SHOW, "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        helpMap.put(CommandIdentifier.ADD, "add {element}: добавить новый элемент в коллекцию");
        helpMap.put(CommandIdentifier.UPDATE, "update id {element}: обновить значение элемента коллекции, id которого равен заданному");
        helpMap.put(CommandIdentifier.REMOVE_BY_ID, "remove_by_id id: удалить элемент из коллекции по его id");
        helpMap.put(CommandIdentifier.CLEAR, "clear: очистить коллекцию");
        helpMap.put(CommandIdentifier.SAVE, "save: сохранить коллекцию в файл");
        helpMap.put(CommandIdentifier.EXECUTE_SCRIPT, "execute_script file_name: считать и исполнить скрипт из указанного файла");
        helpMap.put(CommandIdentifier.EXIT, "exit: завершить программу (без сохранения в файл)");
        helpMap.put(CommandIdentifier.REMOVE_GREATER, "remove_greater {element}: удалить из коллекции все элементы, превышающие заданный");
        helpMap.put(CommandIdentifier.REMOVE_LOWER, "remove_lower {element}: удалить из коллекции все элементы, меньшие, чем заданный");
        helpMap.put(CommandIdentifier.HISTORY, "history: вывести последние 8 команд (без их аргументов)");
        helpMap.put(CommandIdentifier.SUM_OF_TUNED_IN_WORKS, "sum_of_tuned_in_works: вывести сумму значений поля tunedInWorks для всех элементов коллекции");
        helpMap.put(CommandIdentifier.PRINT_ASCENDING, "print_ascending: вывести элементы коллекции в порядке возрастания");
        helpMap.put(CommandIdentifier.PRINT_FIELD_DESCENDING_DIFFICULTY, "print_field_descending_difficulty: вывести значения поля difficulty всех элементов в порядке убывания");
        return new HelpCommandExecutionResultDTO(helpMap);
    }
}
