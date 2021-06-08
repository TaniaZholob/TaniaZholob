//package com.example.TestProject.web.filter;
//
//import org.apache.log4j.Logger;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//
//
//public class AuthenticationFilter implements Filter {
//    private static final Logger log = Logger.getLogger(AuthenticationFilter.class);
//    private FilterConfig filterConfig;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        log.debug("Filter initialization starts");
//        this.filterConfig = filterConfig;
//        System.out.println("filter init");
//
//        log.debug("Filter initialization finished");
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        log.debug("Filter starts");
//
////        final HttpServletRequest req = (HttpServletRequest) request;
////        final HttpServletResponse res = (HttpServletResponse) response;
////
////        final String email = req.getParameter("login");
////        final String password = req.getParameter("password");
////
////
////        chain.doFilter(request, response);
////        System.out.println("after filter work");
//
//        System.out.println(filterConfig.getInitParameter("active")+" init parametr og filter");
//        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
//
//            final HttpServletRequest req = (HttpServletRequest) request;
//            final HttpServletResponse res = (HttpServletResponse) response;
//
//            final HttpSession session = req.getSession();
//            System.out.println(session.getAttribute("user") +" user in session");
//            System.out.println(session.getAttribute("role")+" role in session");
//            if (session != null && session.getAttribute("user") != null && session.getAttribute("userRole") != null) {
//
//                chain.doFilter(request, res);
//                return;
//
//            }
//
//            response.getWriter().write(notifyAccessDenied());
//        }
//    }
//
//
//    private String notifyAccessDenied() {
//        return "<script>" + "alert('You don`t have a permission!');" + "window.location = 'http://localhost:8080/login';" + "</script>";
//    }
//
////    @Override
////    public void destroy() {
////        filterConfig = null;
////    }
//}
