package com.example.TestProject.web.command;

import com.example.TestProject.web.command.GoTo.GoToLogin;
import com.example.TestProject.web.command.GoTo.GoToMainPage;
import com.example.TestProject.web.command.GoTo.GoToRegister;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Logger log = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        commands.put("GoToRegister", new GoToRegister());
        commands.put("register", new RegisterCommand());
        commands.put("login", new GoToLogin());
        commands.put("locale", new AppLocalizationCommand());
        commands.put("goToMainPage", new GoToMainPage());

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
