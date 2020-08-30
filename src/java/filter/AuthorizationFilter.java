/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import constant.SystemConstant;
import dtos.UserDTO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.SessionUtil;

/**
 *
 * @author nguyen
 */

public class AuthorizationFilter implements Filter {

    private ServletContext context;

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
        String url = request.getRequestURI();
        if (url.startsWith("/views/admin") || url.startsWith("/admin")) {
            UserDTO user = (UserDTO) SessionUtil.getInstance().getValue(request, "USERMODEL");
            if (user != null) {
                if (user.getRole().getId() == SystemConstant.ADMIN) {
                    chain.doFilter(servletRequest, servletResponse);

                } else if (user.getRole().getId() == SystemConstant.CUSTOMER) {
                    response.sendRedirect(request.getContextPath() + "/login?action=login&message=Not permission&alert=danger");

                }
            } else {
                response.sendRedirect(request.getContextPath() + "/login?action=login&message=Please Login&alert=danger");
            }
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
