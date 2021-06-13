package com.example.TestProject.controller.filter;

//import java.util.logging.Filter;

import com.example.TestProject.constants.Commands;
import com.example.TestProject.constants.Path;
import com.example.TestProject.model.Role;
import com.example.TestProject.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SecurityFilter implements Filter {
    private static final Logger log = Logger.getLogger(SecurityFilter.class);
    private static final String ATTRIBUTE_USER = "user";
    private static final String PARAMETER_COMMAND = "command";


    private static final List<String> userCommand = new ArrayList<>();
    private static final List<String> adminCommand = new ArrayList<>();
    private static final List<String> masterCommand = new ArrayList<>();
    private static final List<String> commonCommand = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Run init.");
        userCommand.add(Commands.CLIENT_ACCOUNT_PAGE);
        userCommand.add(Commands.CLIENT_SELECT_TIME);
        userCommand.add(Commands.CLIENT_DO_RECORD);
        userCommand.add(Commands.CLIENT_GIVE_FEEDBACK);
        userCommand.add(Commands.CLIENT_GO_TO_GIVE_FEEDBACK);
        userCommand.add(Commands.CLIENT_SELECT_MASTER);
        userCommand.add(Commands.CLIENT_SELECT_PROCEDURES);

        adminCommand.add(Commands.ADMIN_CHANGE_RECORD_TIME);
        adminCommand.add(Commands.ADMIN_ALL_RECORDS);
        adminCommand.add(Commands.ADMIN_GOTO_EDIT_RECORD);

        masterCommand.add(Commands.MASTER_GO_TO_MASTER_TIME_TABLE);
        masterCommand.add(Commands.MASTER_PERFORM_RECORD);
        commonCommand.add(Commands.LOG_OUT);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.trace("DoFilter start!");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String commandName = req.getParameter(PARAMETER_COMMAND);
        if (commandName != null) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(ATTRIBUTE_USER);
            Role role = (Role) session.getAttribute("role");

            if ((userCommand.contains(commandName) && (user == null || !Role.CLIENT.equals(role)))
                    || (adminCommand.contains(commandName)&&(user==null||(!Role.ADMIN.equals(role))))
                    ||(masterCommand.contains(commandName)&&(user==null||!Role.MASTER.equals(role)))
                    ||(commandName.equals(Commands.CLIENT_AUTHORIZATION)&&(user!=null))) {
                log.info("User: \"" + user
                        + "\" tried to get or change private resources \"" + commandName + "\"");
//                resp.sendRedirect("/error");
                request.setAttribute("errorMessage", "You don`t have permission for this!");
                request.getRequestDispatcher(Path.PAGE__ERROR_PAGE)
                        .forward(request, response);
            }else {
                chain.doFilter(request,response);
                log.trace("DoFilter end!");
            }
        }else {

            chain.doFilter(request,response);
        }
    }
}
