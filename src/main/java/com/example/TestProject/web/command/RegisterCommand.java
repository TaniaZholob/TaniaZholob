package com.example.TestProject.web.command;

import com.example.TestProject.Path;
import com.example.TestProject.db.Role;
import com.example.TestProject.db.dao.UserDAO;
import com.example.TestProject.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Register command.
 *
 * @author T.Zholob
 */

public class RegisterCommand extends Command {
    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger log = Logger.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command starts");
        HttpSession session = request.getSession();
        // obtain information from the request

        User user = new User();
        user.setLogin(request.getParameter("login"));
        System.out.println(user.getLogin());
        user.setPassword(request.getParameter("password"));
        System.out.println(user.getPassword());
        user.setFirstName(request.getParameter("firstName"));
        System.out.println(user.getFirstName());
        user.setLastName(request.getParameter("lastName"));
        System.out.println(user.getLastName());
        user.setLocaleName(request.getParameter("localeName"));
        System.out.println(user.getLocaleName());
        user.setRoleId(Role.CLIENT.ordinal());
        new UserDAO().registerUser(user);

        return Path.PAGE__USER;

    }
}
