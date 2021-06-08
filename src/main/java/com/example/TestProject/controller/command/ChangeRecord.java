package com.example.TestProject.controller.command;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.services.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class ChangeRecord extends Command {
    private static final Logger log = Logger.getLogger(ChangeRecord.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start1");
        Optional<String> save = Optional.ofNullable(request.getParameter("save"));
        Optional<String> delete = Optional.ofNullable(request.getParameter("delete"));
        Long id = Long.parseLong(request.getParameter("id"));
        OrderService service = new OrderService();
        if (save.isPresent()) {
            String time = request.getParameter("data") + " "+request.getParameter("time");
            log.info("Change time of order by id: "+id +" on "+ time);
            if(service.changeTimeOfOrder(id, time)){
                log.info("Something wrong with database");
                log.debug("Command end, go to error page!");
                return Path.PAGE__ERROR_PAGE;
            }
        }

        if(delete.isPresent()){
            log.info("Delete order by id: "+id +".");
            if(service.deleteRecord(id)){
                log.info("Something wrong with database");
                log.debug("Command end, go to error page!");
                return Path.PAGE__ERROR_PAGE;
            }
        }
        log.debug("Command end!");
        return Path.PAGE__MAIN;
    }
}
