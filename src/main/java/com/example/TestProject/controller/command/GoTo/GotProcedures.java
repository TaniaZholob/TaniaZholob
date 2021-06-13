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
import java.util.stream.Collectors;

public class GotProcedures extends Command {
    private static final Logger log = Logger.getLogger(GotProcedures.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start!");
        List<Procedure> procedures = new ProcedureService().getProcedures();
        Optional<String> filter = Optional.ofNullable(request.getParameter("filter"));
        if (filter.isPresent()){
            switch (filter.get()){
                case "a":
                    procedures = procedures.stream().filter(procedure ->procedure.getPrice()<=400).collect(Collectors.toList());
                    break;
                case "b":
                    procedures = procedures.stream().filter(procedure ->(procedure.getPrice()>400&&procedure.getPrice()<=600)).collect(Collectors.toList());
                    break;
                case "c":
                    procedures = procedures.stream().filter(procedure ->procedure.getPrice()>600).collect(Collectors.toList());
                    break;
            }
        }


        log.trace("Procedures: "+ procedures);
        request.setAttribute("proceduresALL", procedures);
        return Path.PAGE__PROCEDURES;
    }
}
