/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import constant.SystemConstant;
import dtos.RoleDTO;
import dtos.UserDTO;
import java.io.IOException;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.IRoleService;
import services.IUserService;

/**
 *
 * @author nguyen
 */
@WebServlet(urlPatterns = {"/admin-edit"})
public class EditController extends HttpServlet {
    private final String EDIT_USER_PAGE = "/views/admin/edit.jsp";

    @Inject
    private IUserService userService;
    
    @Inject
    private IRoleService roleService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Optional<String> id = Optional.ofNullable(request.getParameter("id"));
        
        if (id.isPresent()) {
            UserDTO user = new UserDTO();
            user = userService.findUserByIdAndStatus(Long.valueOf(id.get()), SystemConstant.ACTIVE);
            request.setAttribute("USER", user);
        }
        RoleDTO role = new RoleDTO();
        role.setListResult(roleService.findAll());  
        request.setAttribute("ROLELIST", role);
        RequestDispatcher rd = request.getRequestDispatcher(EDIT_USER_PAGE);
        rd.forward(request, response);
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
