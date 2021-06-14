package com.example.TestProject.controller.command.GoTo;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToOrderTime implements Command {
    private static final Logger log = Logger.getLogger(GoToOrderTime.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start");
        HttpSession session = request.getSession();
        String masterId = request.getParameter("master");
        session.setAttribute("master", masterId);
        log.trace("Master id: "+masterId);
        log.debug("Command end");
        return Path.PAGE__TIME_RECORD;
    }
}
