/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import constant.SystemConstant;
import dtos.CategoryDTO;
import dtos.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ICategoryService;
import services.IProductService;

/**
 *
 * @author nguyen
 */
@WebServlet(urlPatterns = {"/admin-product-search"})
public class SearchProductController extends HttpServlet {
    private final String VIEW_PRODUCT_PAGE = "/views/admin/viewProduct.jsp";

    @Inject
    private IProductService productService;

    @Inject
    private ICategoryService categoryService;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String textSearch = request.getParameter("textSearch");
        String priceMin = request.getParameter("priceMin");
        String priceMax = request.getParameter("priceMax");
        String categoryId = request.getParameter("categoryId");
        ProductDTO product = new ProductDTO();
           
        product.setListResult(this.productService
                    .findAllBookByNameAndPriceAndCategory(
                            textSearch.trim().toLowerCase(),
                            priceMin == "" ? 0L : Long.valueOf(priceMin),
                            priceMax == "" ? 0L : Long.valueOf(priceMax),
                            Long.valueOf(categoryId),
                            SystemConstant.ACTIVE));
        CategoryDTO category = new CategoryDTO();
        category.setListResult(this.categoryService.findAll());

        request.setAttribute("PRODUCTLIST", product);
        request.setAttribute("CATEGORYLIST", category);

        RequestDispatcher rd = request.getRequestDispatcher(VIEW_PRODUCT_PAGE);
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
