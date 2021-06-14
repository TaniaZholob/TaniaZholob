package com.example.TestProject.controller.command.GoTo;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToFeedbackPage implements Command {
    private static final Logger log = Logger.getLogger(GoToFeedbackPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start!");
        Integer id = Integer.parseInt(request.getParameter("id"));
        String master = request.getParameter("masterNameSurname");
        String procedure = request.getParameter("procedure");
        log.trace("Id of record");
        log.trace("Master: " + master);
        log.trace("Procedure: " + procedure);
        request.setAttribute("id", id);
        request.setAttribute("masterNameSurname", master);
        request.setAttribute("procedure", procedure);
        log.trace("Command end!");
        return Path.PAGE__FEEDBACK_PAGE;
    }
}
