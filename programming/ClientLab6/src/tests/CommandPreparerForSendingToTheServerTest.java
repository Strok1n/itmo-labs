package tests;

import client.CommandPreparerForSendingToTheServer;
import client.io.ConsoleReader;
import client.io.ConsoleWriter;
import contract.CommandName;
import contract.dto.commanddto.concrete.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommandPreparerForSendingToTheServerTest {
    CommandPreparerForSendingToTheServer commandPreparerForSendingToTheServer;

    @BeforeEach
    public void setUp(){
        commandPreparerForSendingToTheServer =
                new CommandPreparerForSendingToTheServer(
                        new ConsoleReader(System.in),
                        new ConsoleWriter(System.out)
                );
    }

    @Test
    @DisplayName("testCommandDTOBuildersMap")
    public void testCommandDTOBuildersMap(){
        Assertions.assertEquals(
                commandPreparerForSendingToTheServer.prepareCommandDTOForSending(CommandName.info, null).getClass(),
                 InfoCommandDTO.class
        );
        Assertions.assertEquals(
                commandPreparerForSendingToTheServer.prepareCommandDTOForSending(CommandName.help, null).getClass(),
                HelpCommandDTO.class
        );
        Assertions.assertEquals(
                commandPreparerForSendingToTheServer.prepareCommandDTOForSending(CommandName.sum_of_tuned_in_works, null).getClass(),
                SumOfTunedInWorksCommandDTO.class
        );
        Assertions.assertEquals(
                commandPreparerForSendingToTheServer.prepareCommandDTOForSending(CommandName.history, null).getClass(),
                HistoryCommandDTO.class
        );
        Assertions.assertEquals(
                commandPreparerForSendingToTheServer.prepareCommandDTOForSending(CommandName.show, null).getClass(),
                ShowCommandDTO.class
        );
    }

}


