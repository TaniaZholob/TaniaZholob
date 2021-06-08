package com.example.TestProject.controller.command;

import com.example.TestProject.constants.Path;
import com.example.TestProject.controller.services.FeedBackService;
import com.example.TestProject.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GiveFeedback extends Command {
    private static final Logger log = Logger.getLogger(GiveFeedback.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.trace("Command start!");
        int rating = Integer.parseInt(request.getParameter("dayscount"));
        String text = request.getParameter("textOfFeedback");
        String master = request.getParameter("masterNameSurname");
        log.debug("Got rating: " + rating + ", text: " + text + ", master name and surname: " + master);
        User user = (User) request.getSession().getAttribute("user");
        log.trace("User " +user);
        FeedBackService service = new FeedBackService();
        if(service.addRating(rating, text, master, user)){
            return Path.PAGE__ERROR_PAGE;
        };

        return Path.PAGE__MAIN;
    }
}
