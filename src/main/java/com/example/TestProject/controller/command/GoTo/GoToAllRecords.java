package com.example.TestProject.controller.command.GoTo;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.command.Command;
import com.example.TestProject.controller.services.OrderService;
import com.example.TestProject.model.Payment_Status;
import com.example.TestProject.model.Role;
import com.example.TestProject.model.bean.Record;
import com.example.TestProject.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GoToAllRecords extends Command {
    private static final Logger log = Logger.getLogger(GoToAllRecords.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.trace("Command start!");
        int currentPageInt;
        OrderService service = new OrderService();

        String id = request.getParameter("changePaymentStatus");
        log.trace("Got param of id: ");
        String ps = request.getParameter("status");
        if (id != null) {
            Payment_Status status = Payment_Status.valueOf(ps.toUpperCase());
            log.info("Change of payment status by id: " + id);
            log.trace("Got param of paymentStatus: " + status);
            Long idPars = Long.parseLong(id);
            User user = service.getUserOfRecord(idPars);
            if (service.changePaymentStatus(idPars, status, user)) {
                return Path.PAGE__ERROR_PAGE;
            }
        }
        String currentPage = request.getParameter("currentPage");
        if (currentPage == null) {
            request.setAttribute("currentPage", 1);
            currentPage = "1";
            log.debug("Set attribute of current page: " + currentPage);
        }
        currentPageInt = Integer.parseInt(currentPage);


        List<Record> records = service.getRecords(currentPageInt);
        request.setAttribute("all_records", records);

        double rows = service.getNumberOfRecords().doubleValue();
        int nOfPages = (int) Math.ceil(rows / 2.0);
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        log.debug("Set attribute number of page: " + nOfPages + " and current page: " + currentPage);


        return Path.PAGE__ALL_RECORDS;
    }
}
