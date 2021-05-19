package com.example.TestProject.web.command;

import com.example.TestProject.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AppLocalizationCommand extends Command {
    private static final String PARAMETER_LOCALE = "locale";

    private static final String ATTRIBUTE_PREVIOUS_REQUEST = "previous_request";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String locale = request.getParameter(PARAMETER_LOCALE);
        String previousRequest = (String) session.getAttribute(ATTRIBUTE_PREVIOUS_REQUEST);
        session.setAttribute(PARAMETER_LOCALE, locale);
        response.sendRedirect(previousRequest);
        return null;
    }
}
