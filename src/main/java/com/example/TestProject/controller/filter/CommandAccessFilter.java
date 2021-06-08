//package com.example.TestProject.web.filter;
//
//import com.example.TestProject.constants.Path;
//import com.example.TestProject.model.Role;
//import org.apache.log4j.Logger;
//
//import javax.servlet.*;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Security filter. Disabled by default. Uncomment Security filter
// * section in web.xml to enable.
// *
// * @author T.Zholob
// */
//
//public class CommandAccessFilter implements Filter {
//    private static final Logger log = Logger.getLogger(CommandAccessFilter.class);
//    // commands access
//    private static Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();
//    private static List<String> commons = new ArrayList<String>();
//    private static List<String> outOfControl = new ArrayList<String>();
//
//    public void destroy() {
//        log.debug("Filter destruction starts");
//        // do nothing
//        log.debug("Filter destruction finished");
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        log.debug("Filter starts");
//
//        if (accessAllowed(request)) {
//            log.debug("Filter finished");
//            chain.doFilter(request, response);
//        } else {
//            String errorMessasge = "You do not have permission to access the requested resource";
//
//            request.setAttribute("errorMessage", errorMessasge);
//            log.trace("Set the request attribute: errorMessage --> " + errorMessasge);
//
//            request.getRequestDispatcher(Path.PAGE__ERROR_PAGE)
//                    .forward(request, response);
//        }
//    }
//}
