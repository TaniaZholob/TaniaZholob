package com.example.TestProject.controller.command;

import com.example.TestProject.constants.Path;
import com.example.TestProject.model.Role;
import com.example.TestProject.model.entity.User;
import com.example.TestProject.controller.validator.ValidatorData;
import com.example.TestProject.controller.services.ProvideServices;
import com.example.TestProject.controller.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Register command.
 *
 * @author T.Zholob
 */

public class RegisterCommand implements Command {

    private static final Logger log = Logger.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command starts");


        String email = request.getParameter("login");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");


        User user = new User();
        user.setLogin(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);

        if (ValidatorData.registrationValidation(user)) {
            ProvideServices provideServices = ProvideServices.getInstance();
            UserService userService = provideServices.getUserService();
            if (userService.registration(user)) {
                request.setAttribute("errorMessage", "wrong data");
                log.debug("Command end!");
                return Path.PAGE__REGISTER;
            }
            log.info("Add new user: " + user);
            request.getSession().setAttribute("user", user);
            log.info("Set session attribute: " + Role.CLIENT);
            request.getSession().setAttribute("role", Role.CLIENT);
            log.debug("Command end! Registration success");
            response.sendRedirect("beautySalon");
            return null;
        }
        request.setAttribute("errorMessage", "wrong data");

        return Path.PAGE__REGISTER;

    }
}
