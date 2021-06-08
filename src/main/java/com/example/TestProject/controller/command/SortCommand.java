package com.example.TestProject.controller.command;

import com.example.TestProject.constants.Path;
import com.example.TestProject.model.dao.MasterDAO;
import com.example.TestProject.model.entity.Master;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class SortCommand extends Command {
    private static final Logger log = Logger.getLogger(SortCommand.class);
    private static String MASTER = "all_masters";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command start!");
        String sort = request.getParameter("sort");
        if (sort == null) {
            sort = "name";
        }
        log.trace("Sort by "+ sort);
        Integer page = null;
        List<Master> masters = new MasterDAO().getAllMasters();
        System.out.println(masters.get(1)+ " my new master");
        System.out.println(masters.get(2)+ " my new master");
        System.out.println(masters.get(3)+ " my new master");
        //sorting by name of sort
        switch (sort) {
            case "name":
                masters.sort(Comparator.comparing(Master::getName));
                break;
            case "rating":
                masters.sort(Comparator.comparing(Master::getRating).reversed());
                break;
        }
        request.setAttribute(MASTER, masters);
        return Path.PAGE__MASTERS;
    }
}
