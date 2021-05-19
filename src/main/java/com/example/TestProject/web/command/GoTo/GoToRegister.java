package com.example.TestProject.web.command.GoTo;

import com.example.TestProject.Path;
import com.example.TestProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToRegister extends Command {
    private static final Logger log = Logger.getLogger(GoToRegister.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command starts");
        return Path.PAGE__REGISTER;
    }
}
