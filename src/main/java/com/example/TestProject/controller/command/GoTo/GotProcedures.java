package com.example.TestProject.controller.command.GoTo;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.command.Command;
import com.example.TestProject.controller.services.ProcedureService;
import com.example.TestProject.model.entity.Procedure;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GotProcedures implements Command {
    private static final Logger log = Logger.getLogger(GotProcedures.class);
    private ProcedureService service = new ProcedureService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start!");

        List<Procedure> procedures = service.getProcedures();
        Optional<String> filter = Optional.ofNullable(request.getParameter("filter"));

        if (filter.isPresent()) {
              procedures = service.filter(filter.get(), procedures);
        }

        log.trace("Procedures: " + procedures);
        request.setAttribute("proceduresALL", procedures);

        log.trace("Command end!");
        return Path.PAGE__PROCEDURES;
    }
}
