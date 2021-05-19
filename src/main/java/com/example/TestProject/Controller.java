package com.example.TestProject;

import com.example.TestProject.db.DBManager;
import com.example.TestProject.web.command.Command;
import com.example.TestProject.web.command.CommandContainer;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * Main servlet controller.
 *
 * @author T.Zholob
 *
 */
@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 2423353715955164816L;
    private static final Logger log = Logger.getLogger(Controller.class);
    private static final String PARAMETER_LOCALE = "locale";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        process(request, response);
    }


    /**
     * Main method of this controller.
     */
    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.getAttribute(PARAMETER_LOCALE) == null) {
            session.setAttribute(PARAMETER_LOCALE, Locale.getDefault());
        }


        log.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        log.trace("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        log.trace("Obtained command --> " + command);
        System.out.println("geted command 2");
        // execute command and get forward address
        String forward = command.execute(request, response);
        log.trace("Forward address --> " + forward);

        log.debug("Controller finished, now go to forward address --> " + forward);

        // if the forward address is not null go to the address
        if (forward != null) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }


        String queryString = request.getQueryString();
        if (queryString != null) {
            String previousRequest = "controller?" + queryString;
            session.setAttribute("previous_request", previousRequest.replaceAll("&message.+?=.+?(?=(&|$))", ""));
        }
    }

}