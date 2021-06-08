package com.example.TestProject.controller.command.GoTo;

import com.example.TestProject.constants.Path;
import com.example.TestProject.model.dao.MasterDAO;
import com.example.TestProject.model.entity.Master;
import com.example.TestProject.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToOrderMasters extends Command {
    private static final Logger log = Logger.getLogger(GoToOrderMasters.class);
    private static String MASTERS_OF_PROCEDURE = "all_masters_pr";
    private static String NAME_PROCEDURE = "name_of_procedure";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start!");
        HttpSession session = request.getSession();
        String procedure = request.getParameter("procedure");

        log.trace("Got attribute of procedure: " + procedure);
        if (procedure != null) {
            List<Master> masters = new MasterDAO().getAllMastersByProcedure(procedure);
            session.setAttribute(MASTERS_OF_PROCEDURE, masters);
            session.setAttribute(NAME_PROCEDURE, procedure);
        }
        log.debug("Command end!");
        return Path.PAGE__ORDER_MASTER;
    }
}
