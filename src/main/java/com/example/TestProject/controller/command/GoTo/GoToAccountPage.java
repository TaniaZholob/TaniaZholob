package com.example.TestProject.controller.command.GoTo;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.command.Command;
import com.example.TestProject.controller.services.OrderService;
import com.example.TestProject.model.Payment_Status;
import com.example.TestProject.model.Role;
import com.example.TestProject.model.bean.Record;
import com.example.TestProject.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GoToAccountPage implements Command {
    private static final Logger log = Logger.getLogger(GoToAccountPage.class);
    private OrderService service = new OrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start!");

        Role role = (Role) request.getSession().getAttribute("role");
        User user = (User) request.getSession().getAttribute("user");
        log.debug("Role of user: " + role);

        Optional<String> id = Optional.ofNullable(request.getParameter("changePaymentStatus"));
        Optional<String> status = Optional.ofNullable(request.getParameter("status"));

        if (id.isPresent() && status.isPresent()) {

            Long idOfRecord = Long.parseLong(id.get());
            log.trace("Got id of user: " + idOfRecord);
            Payment_Status st = Payment_Status.valueOf(status.get().toUpperCase());
            log.trace("Got id of status: " + st);

            if (service.changePaymentStatus(idOfRecord, st, user)) {
                log.trace("Command end! Error in BD!");
                return Path.PAGE__ERROR_PAGE;
            }
        }

        log.trace("Got user" + user);
        List<Record> records = service.getAllRecordsOfUser(user);
        request.setAttribute("allRecordsOfUser", records);
        log.trace("Set attribute: " + records);
        log.debug("Command end!");
        return Path.PAGE__ACCOUNT_PAGE;
    }
}
