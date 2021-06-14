package com.example.TestProject.controller.command.GoTo;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToEditRecordPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long idS = Long.parseLong(request.getParameter("changeTime"));
        request.setAttribute("id", idS);
        return Path.PAGE__EDIT_RECORD;
    }
}
