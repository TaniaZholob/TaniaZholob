package com.example.TestProject.controller.command.GoTo;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.command.Command;
import com.example.TestProject.controller.services.OrderService;
import com.example.TestProject.model.bean.Record;
import com.example.TestProject.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToMasterTimeTable implements Command {
    private static final Logger log = Logger.getLogger(GoToMasterTimeTable.class);
    private OrderService service = new OrderService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start!");

        User user = (User) request.getSession().getAttribute("user");
        log.trace("User: " + user);

        List<Record> records = service.getRecordsOfMaster(user);
        request.setAttribute("allRecordsOfMaster", records);
        log.trace("Sot attribute of records: " + records);

        log.debug("Command end!");
        return Path.PAGE__MASTER_TIME_TABLE;
    }
}
