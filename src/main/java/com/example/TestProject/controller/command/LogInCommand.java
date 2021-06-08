package com.example.TestProject.controller.command;

import com.example.TestProject.constants.Path;
import com.example.TestProject.model.Role;
import com.example.TestProject.model.dao.UserDAO;
import com.example.TestProject.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogInCommand extends Command {
    private static final Logger log = Logger.getLogger(LogInCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start!");
        HttpSession session = request.getSession();

        String errorMessage;

        String email = request.getParameter("login");
        log.trace("Email: " + email);
        String password = request.getParameter("password");
        log.trace("Password: " + password);


        User user = new UserDAO().findUser(email, password);
        if (user == null) {
            errorMessage = "User not found,check your email or password!";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE__LOGIN;
        } else {
            //get role
            Role role = Role.getRole(user);
            session.setAttribute("role", role);
            log.trace("Set the session attribute: userRole --> " + role);
            session.setAttribute("user", user);
//            return Path.PAGE__MAIN;
            log.debug("Command end!");
            return "/user?command=goToMainPage";
        }


    }
}
