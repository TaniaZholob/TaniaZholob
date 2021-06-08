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

public class GoToAccountPage extends Command {
    private static final Logger log = Logger.getLogger(GoToAccountPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start!");
        Role role = (Role) request.getSession().getAttribute("role");
        log.debug("Role of user: " + role);
        if (role == null || !"client".equals(role.getName())) {
            log.info("Wrong role of user.");
            log.trace("Command end!");
            return Path.PAGE__ERROR_PAGE;
        }
        OrderService service = new OrderService();
        String id = request.getParameter("changePaymentStatus");
        String status = request.getParameter("status");
        if (id != null && status != null) {
            Long idOfRecord = Long.parseLong(id);
            log.trace("Got id of user: " + idOfRecord);
            Payment_Status st = Payment_Status.valueOf(status.toUpperCase());
            log.trace("Got id of status: " + st);
            if (service.changePaymentStatus(idOfRecord, st)) {
                return Path.PAGE__ERROR_PAGE;
            }
        }

        User user = (User) request.getSession().getAttribute("user");
        log.trace("Got user" + user);
        List<Record> records = null;
        if (user != null) {
            records = service.getAllRecordsOfUser(user);
        }
        request.setAttribute("allRecordsOfUser", records);
        log.trace("Set attribute: " + records);
        log.debug("Command end!");
        return Path.PAGE__ACCOUNT_PAGE;
    }
}
