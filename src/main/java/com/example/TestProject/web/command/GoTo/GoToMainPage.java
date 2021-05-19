package com.example.TestProject.web.command.GoTo;

import com.example.TestProject.Path;
import com.example.TestProject.db.dao.MasterDAO;
import com.example.TestProject.db.entity.Master;
import com.example.TestProject.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToMainPage extends Command {
    private static String PAGE = "page";
    private static String MASTER = "all_masters";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Integer page = null;
        List<Master> masters = null;
        if (session.getAttribute(PAGE) == null) {
            session.setAttribute(PAGE, 1);
            masters = new MasterDAO().getMasters(1);
            session.setAttribute(MASTER, masters);
            System.out.println(masters);
        } else {
//            page = Integer.parseInt((String) session.getAttribute(PAGE));
//            session.setAttribute(PAGE, ++page);
//            masters = new MasterDAO().getMasters(++page);
//            session.setAttribute(MASTER, masters);
        }
        System.out.println(masters);
        System.out.println(session.getAttribute(MASTER));
        return Path.PAGE__MAIN;
    }
}
