package com.example.TestProject.controller.command;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.services.OrderService;
import com.example.TestProject.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.io.IOException;

public class AddRecord implements Command {
    private static final Logger log = Logger.getLogger(AddRecord.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start!");
        HttpSession session = request.getSession();
        String dataTime = request.getParameter("data");
        dataTime += " " + request.getParameter("time");
        log.trace("Got parameter of time from user: " + dataTime);
        Long idMaster = Long.parseLong((String) session.getAttribute("master"));
        log.trace("Id of master: " + idMaster);
        User user = (User) request.getSession().getAttribute("user");
        log.trace("User: " + user);

        String procedure = (String) request.getSession().getAttribute("name_of_procedure");
        log.trace("Procedure: " + procedure);
        OrderService service = new OrderService();

        if (service.insertRecord(idMaster, dataTime, user, procedure)) {
            request.setAttribute("wrongData", "wrongData");
            return Path.PAGE__ERROR_PAGE;
        }

        return Path.PAGE__RECORD_END;
    }
}
