package com.example.TestProject.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutCommand extends Command {
    private static final Logger log = Logger.getLogger(LogOutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start");
        HttpSession session = request.getSession(false);
        session.setAttribute("user", null);
        session.setAttribute("role", null);


        log.debug("Command end!");
        response.sendRedirect("beautySalon");
        return null;
    }
}

