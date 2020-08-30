package controllers.web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import constant.SystemConstant;
import dtos.UserDTO;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.IUserService;
import utils.HashFunctions;
import utils.SessionUtil;

/**
 *
 * @author nguyen
 */

@WebServlet(urlPatterns = {"/login", "/logout"})
public class AuthenticationController extends HttpServlet {
    private final String GET_PRODUCT = "/views/web/getProduct.jsp";
    private final String ADMIN_PAGE = "/views/admin/home.jsp";

    @Inject
    private IUserService userService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            String alert = request.getParameter("alert");
            String message = request.getParameter("message");
            if (message != null && alert != null) {
                request.setAttribute("message", message);
                request.setAttribute("alert", alert);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("logout")) {
            SessionUtil.getInstance().removeAll(request);
            response.sendRedirect("/product-get");

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
            rd.forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            UserDTO model = new UserDTO();
            String username = request.getParameter("username");
            model.setUsername(username);
            String password = request.getParameter("password");
            String passHash = HashFunctions.getHash(password.trim().getBytes(), "SHA-256");
            model.setPassword(passHash);
            model = userService.findByUserNameAndPasswordAndStatus(model.getUsername(), model.getPassword(), SystemConstant.ACTIVE);
            if (model != null) {
                SessionUtil.getInstance().putValue(request, "USERMODEL", model);
                if (model.getRole().getId() == SystemConstant.CUSTOMER) {

                    response.sendRedirect(GET_PRODUCT);
                } else if (model.getRole().getId() == SystemConstant.ADMIN) {
                    response.sendRedirect(ADMIN_PAGE);
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/login?action=login&message=username password in valid&alert=danger&username=" + username);
            }

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
