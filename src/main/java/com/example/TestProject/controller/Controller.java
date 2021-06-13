package com.example.TestProject.controller;

import com.example.TestProject.controller.command.Command;
import com.example.TestProject.controller.command.CommandContainer;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * Main servlet controller.
 *
 * @author T.Zholob
 */

public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class);
    private static final String PARAMETER_LOCALE = "locale";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("Charter encoding: " +request.getCharacterEncoding());
//        request.setCharacterEncoding("UTF-8");
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

        String uri = request.getRequestURI();
        System.out.println("URI: "+ uri);

        log.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        log.trace("Request parameter: command --> " + commandName);
        System.out.println(commandName + " command name from request");
        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        log.trace("Obtained command --> " + command);
        // execute command and get forward address
        String forward = command.execute(request, response);
        log.trace("Forward address --> " + forward);

        log.debug("Controller finished, now go to forward address --> " + forward);

        // if the forward address is not null go to the address
        if (forward != null) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }

        Optional<String> queryString = Optional.ofNullable(request.getQueryString());

        System.out.println(queryString + " query String in controller");

//        queryString.ifPresent(value -> {
//            String previousRequest = "controller?" + value;
//            session.setAttribute("previous_request", previousRequest.replaceAll("&message.+?=.+?(?=(&|$))", ""));
//        });

        if(queryString.isPresent()){
            String previousRequest = "controller?" + queryString.get();
            session.setAttribute("previous_request", previousRequest.replaceAll("&message.+?=.+?(?=(&|$))", ""));
        }else {
            session.setAttribute("previous_request", null);
        }

    }

}