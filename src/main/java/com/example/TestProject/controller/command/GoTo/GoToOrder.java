package com.example.TestProject.controller.command.GoTo;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.services.ProcedureService;
import com.example.TestProject.model.dao.ProcedureDAO;
import com.example.TestProject.model.entity.Procedure;
import com.example.TestProject.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToOrder implements Command {
    private static final Logger log = Logger.getLogger(GoToOrder.class);
    private static String PROCEDURE = "all_procedures";
    private ProcedureService procedureService = new ProcedureService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start!");

        List<Procedure> procedures = procedureService.getProcedures();

        HttpSession session = request.getSession();
        session.setAttribute(PROCEDURE, procedures);
        log.trace("Set request attribute: " + procedures);

        log.debug("Command end!");
        return Path.PAGE__ORDER;
    }
}
