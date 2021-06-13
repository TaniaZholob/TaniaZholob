package com.example.TestProject.controller.command;

import com.example.TestProject.constants.Commands;
import com.example.TestProject.controller.command.GoTo.*;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Logger log = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        commands.put(Commands.CLIENT_REGISTER, new RegisterCommand());
        commands.put(Commands.LOCALE, new AppLocalizationCommand());
//        commands.put(Commands.MAIN_PAGE, new GoToMainPage());
        commands.put(Commands.CLIENT_AUTHORIZATION, new LogInCommand());
        commands.put(Commands.LOG_OUT, new LogOutCommand());
        commands.put(Commands.CLIENT_SELECT_PROCEDURES, new GoToOrder());
        commands.put(Commands.CLIENT_SELECT_MASTER, new GoToOrderMasters());
        commands.put(Commands.CLIENT_SELECT_TIME, new GoToOrderTime());
        commands.put(Commands.CLIENT_DO_RECORD, new AddRecord());
        commands.put(Commands.ADMIN_ALL_RECORDS, new GoToAllRecords());
        commands.put(Commands.CLIENT_ACCOUNT_PAGE, new GoToAccountPage());
        commands.put(Commands.ADMIN_GOTO_EDIT_RECORD, new GoToEditRecordPage());
        commands.put(Commands.ADMIN_CHANGE_RECORD_TIME, new ChangeRecord());
        commands.put(Commands.MASTER_GO_TO_MASTER_TIME_TABLE, new GoToMasterTimeTable());
        commands.put(Commands.MASTER_PERFORM_RECORD, new PerformRecord());
        commands.put(Commands.CLIENT_GO_TO_GIVE_FEEDBACK, new GoToFeedbackPage());
        commands.put(Commands.CLIENT_GIVE_FEEDBACK, new GiveFeedback());
        commands.put(Commands.SORT_MASTERS, new SortCommand());
        commands.put(Commands.GOT_PROCEDURES, new GotProcedures());

    }


    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            log.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }

}
