package com.example.TestProject.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Encoding filter.
 *
 * @author T.Zholob
 */

public class EncodingFilter implements Filter {

    private static final Logger log = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Filter initialization");
        encoding = filterConfig.getInitParameter("encoding");
        log.trace("Encoding from web.xml --> " + encoding);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("Filter starts");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        log.trace("Request uri --> " + httpRequest.getRequestURI());

        log.trace("Set encoding --> " + encoding);
        request.setCharacterEncoding(encoding);


        log.debug("Filter finished");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.debug("Filter destruction");
    }
}
