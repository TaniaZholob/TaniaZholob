package com.example.TestProject.controller.command;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PerformRecord implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("changePerformStatus"));
        OrderService service =new OrderService();
        if(service.changePerformStatus(id)){
            return Path.PAGE__ERROR_PAGE;
        }
        response.sendRedirect("master?command=goToMasterTimeTable");
        return null;
    }
}
