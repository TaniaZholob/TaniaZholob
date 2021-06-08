package com.example.TestProject.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AppLocalizationCommand extends Command {
    private static final Logger log = Logger.getLogger(AppLocalizationCommand.class);
    private static final String PARAMETER_LOCALE = "locale";

    private static final String ATTRIBUTE_PREVIOUS_REQUEST = "previous_request";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start");
        HttpSession session = request.getSession();
        String locale = request.getParameter(PARAMETER_LOCALE);
        log.trace("Got parameter of locale: "+locale);
        String previousRequest = (String) session.getAttribute(ATTRIBUTE_PREVIOUS_REQUEST);
        session.setAttribute(PARAMETER_LOCALE, locale);
        log.trace("Set new parameter of locale: "+locale);
        response.sendRedirect(previousRequest);
        log.debug("Command end!");
        return null;
    }
}
