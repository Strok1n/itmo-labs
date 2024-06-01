package server.commandexecutors.concrete;

import contract.CommandName;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.concrete.HelpCommandExecutionResultDTO;
import server.commandexecutors.CommandExecutor;
import server.util.CommandDTOAfterDatabaseWrapper;

import java.util.HashMap;
import java.util.Map;

public class HelpCommandExecutor implements CommandExecutor {
    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {
        CommandDTOAfterDatabaseWrapper wrapper = (CommandDTOAfterDatabaseWrapper) commandDTO;
        if (!wrapper.isDatabaseOperationDone())
            return new AddCommandExecutionResultDTO(wrapper.getMessage());


        commandDTO = wrapper.getCommandDTO();
        Map<CommandName, String> helpMap = new HashMap<>();
        helpMap.put(CommandName.help, "help: вывести справку по доступным командам");
        helpMap.put(CommandName.info, "info: вывести в стандартный поток вывода информацию о коллекции");
        helpMap.put(CommandName.show, "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        helpMap.put(CommandName.add, "add {element}: добавить новый элемент в коллекцию");
        helpMap.put(CommandName.update, "update id {element}: обновить значение элемента коллекции, id которого равен заданному");
        helpMap.put(CommandName.remove_by_id, "remove_by_id id: удалить элемент из коллекции по его id");
        helpMap.put(CommandName.clear, "clear: очистить коллекцию");
     //   helpMap.put(CommandName.save, "save: сохранить коллекцию в файл");
   //     helpMap.put(CommandName.execute_script, "execute_script file_name: считать и исполнить скрипт из указанного файла");
 //       helpMap.put(CommandName.exit, "exit: завершить программу (без сохранения в файл)");
//        helpMap.put(CommandName.remove_greater, "remove_greater {element}: удалить из коллекции все элементы, превышающие заданный");
//        helpMap.put(CommandName.remove_lower, "remove_lower {element}: удалить из коллекции все элементы, меньшие, чем заданный");
//        helpMap.put(CommandName.history, "history: вывести последние 8 команд (без их аргументов)");
//        helpMap.put(CommandName.sum_of_tuned_in_works, "sum_of_tuned_in_works: вывести сумму значений поля tunedInWorks для всех элементов коллекции");
////        helpMap.put(CommandName.print_ascending, "print_ascending: вывести элементы коллекции в порядке возрастания");
//        helpMap.put(CommandName.print_field_descending_difficulty, "print_field_descending_difficulty: вывести значения поля difficulty всех элементов в порядке убывания");
        return new HelpCommandExecutionResultDTO(helpMap);
    }
}
