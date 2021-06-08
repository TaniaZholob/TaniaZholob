package com.example.TestProject.controller.command;

import com.example.TestProject.constants.Path;
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

public class RegisterCommand extends Command {

    private static final Logger log = Logger.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command starts");
        String errorMessage;
        String forward = Path.PAGE__REGISTER;

        String email = request.getParameter("login");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");


        User user = new User();
        user.setLogin(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);

        if(ValidatorData.registrationValidation(user)){
            ProvideServices provideServices = ProvideServices.getInstance();
            UserService userService = provideServices.getUserService();
            userService.registration(user);
            log.info("Add new user");
            return Path.PAGE__USER;
        }



        return Path.PAGE__MAIN;

    }
}
