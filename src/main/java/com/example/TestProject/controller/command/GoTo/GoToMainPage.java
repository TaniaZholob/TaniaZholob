package com.example.TestProject.controller.command.GoTo;

import com.example.TestProject.constants.Path;
import com.example.TestProject.model.dao.MasterDAO;
import com.example.TestProject.model.entity.Master;
import com.example.TestProject.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class GoToMainPage extends Command {
    private static final Logger log = Logger.getLogger(GoToMainPage.class);
    private static String MASTER = "all_masters";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command start!");

        return Path.PAGE__MAIN;
    }


}
