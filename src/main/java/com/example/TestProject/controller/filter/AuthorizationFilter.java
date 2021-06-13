package com.example.TestProject.controller.filter;

import com.example.TestProject.constants.Path;
import com.example.TestProject.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class AuthorizationFilter implements Filter {
    private static final Logger log = Logger.getLogger(AuthorizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Filter init");
    }

    @Override
    public void destroy() {
        log.debug("Filter destroy");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("Filter start");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Optional<User> user = Optional.ofNullable((User) httpRequest.getSession().getAttribute("user"));
        log.trace("Got user: " + user);
        if (user.isEmpty()) {
            log.debug("User is empty, login or registration allow.");
            chain.doFilter(request, response);
        }else {
            log.debug("User is not empty, login not allow. Go to main page.");
            request.getRequestDispatcher(Path.PAGE__MAIN)
                    .forward(request, response);
        }

        log.debug("Filter end");

    }
}
