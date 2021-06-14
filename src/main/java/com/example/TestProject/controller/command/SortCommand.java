package com.example.TestProject.controller.command;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.services.MasterService;
import com.example.TestProject.model.dao.MasterDAO;
import com.example.TestProject.model.entity.Master;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class SortCommand implements Command {
    private static final Logger log = Logger.getLogger(SortCommand.class);
    private static String MASTER = "all_masters";
    private MasterService service = new MasterService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start!");
        String sort = request.getParameter("sort");
        if (sort == null) {
            sort = "name";
        }

        log.trace("Sort by " + sort);
        List<Master> masters = service.sort(sort);
        request.setAttribute(MASTER, masters);
        log.debug("Command end!");
        return Path.PAGE__MASTERS;
    }
}
