package filter;

import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DispatcherFilter implements Filter {

    private final String HOME_PAGE = "/product-get";
    private ServletContext context;
    static Logger logger = Logger.getLogger(Logger.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String url = HOME_PAGE;
        try {
            ServletContext context = request.getServletContext();
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);
            if (resource.length() > 0) {
//                url = "controllers/web/" + resource.substring(0, 1).toUpperCase()
//                        + resource.substring(1) + "Controller";
                 url = resource;
                if (resource.lastIndexOf(".html") > 0 ||
                        resource.lastIndexOf(".jsp") > 0 ||
                        resource.lastIndexOf(".js") > 0 ||
                        resource.lastIndexOf(".css") > 0 ||
                        resource.lastIndexOf(".png") > 0 ||
                        resource.lastIndexOf(".jpg") > 0) {
                        url = resource;
                }
            }
            if (url != null) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(servletRequest, servletResponse);
            } else {
                chain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            logger.error("DispatCherFilter_Exception " + e.getMessage());
        }

    }

    @Override
    public void destroy() {
    }


}
