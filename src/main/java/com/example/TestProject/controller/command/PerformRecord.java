package com.example.TestProject.controller.command;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.services.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PerformRecord implements Command {
    private static final Logger log = Logger.getLogger(PerformRecord.class);
    private OrderService service = new OrderService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.trace("Command start!");
        Long id = Long.parseLong(request.getParameter("changePerformStatus"));
        log.debug("Id of record "+id+ " of changing perform status.");
        if (service.changePerformStatus(id)) {
            return Path.PAGE__ERROR_PAGE;
        }
        log.trace("Command end! Send redirect to master time table.");
        response.sendRedirect("master?command=goToMasterTimeTable");
        return null;
    }
}
