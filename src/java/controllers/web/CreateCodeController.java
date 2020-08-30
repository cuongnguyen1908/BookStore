/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.web;

import dtos.CategoryDTO;
import dtos.ProductDTO;
import services.ICategoryService;
import services.IProductService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 *
 * @author nguyen
 */
@WebServlet(urlPatterns = {"/admin-code-create"})
public class CreateCodeController extends HttpServlet {

    private final String EDIT_PAGE = "/views/admin/editProduct.jsp";

    @Inject
    private IProductService productService;

    @Inject
    private ICategoryService categoryService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Optional<String> id = Optional.ofNullable(request.getParameter("id"));

        if (id.isPresent()) {
            ProductDTO product = new ProductDTO();
            product = this.productService.findBookById(Long.valueOf(id.get()));
            request.setAttribute("BOOK", product);
        }
        CategoryDTO category = new CategoryDTO();
        category.setListResult(categoryService.findAll());
        request.setAttribute("CATEGORYLIST", category);

        RequestDispatcher rd = request.getRequestDispatcher(EDIT_PAGE);
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
