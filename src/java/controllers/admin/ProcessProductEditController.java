/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import constant.SystemConstant;
import dtos.*;
import services.ICategoryService;
import services.IProductService;
import services.IRoleService;
import services.IUserService;
import utils.HashFunctions;
import utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author nguyen
 */
@WebServlet(urlPatterns = {"/admin-process-product-edit"})
public class ProcessProductEditController extends HttpServlet {
    private final String ERROR_PAGE = "/views/admin/editErrorProduct.jsp";
    private final String SUCCESS = "/admin-product-view";

    @Inject
    private IProductService productService;

    @Inject
    private ICategoryService categoryService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean foundError = false;
        String url = ERROR_PAGE;
        ProductEditErrorDTO error = new ProductEditErrorDTO();
        Optional<String> id = Optional.ofNullable(request.getParameter("id"));
        String photo = request.getParameter("file");

        String title = request.getParameter("title");
        if (title.trim().length() < 1 || title.trim().length() > 100 ) {
            foundError = true;
            error.setTitleLengthError("Title length must be > 1 and < 100 letter");
        }
        String author = request.getParameter("author");
        if (author.trim().length() < 1 || author.trim().length() > 100 ) {
            foundError = true;
            error.setAuthorLengthError("Author length must be > 1 and < 100 letter");
        }


        //chcek valid quantity
        String quantity = request.getParameter("quantity");
        int iQuantity = -1;
        if (quantity.trim().length() < 1) {
            foundError = true;
            error.setQuantityLengthError("Quantity must be > 0");
        }else {
            try {
                iQuantity = Integer.parseInt(quantity);
            }catch (NumberFormatException e) {
                foundError = true;
                error.setQuantityFormatException("Quantity is incorrect format");
            }
        }

        //check valid price
        String price = request.getParameter("price");
        float fPrice = -1;
        if (price.trim().length() < 1) {
            foundError = true;
            error.setPriceLengthError("Price must be > 0");
        }else {
            try {
                fPrice = Float.parseFloat(quantity);
            }catch (NumberFormatException e) {
                foundError = true;
                error.setPriceFormatException("Price is incorrect format");
            }
        }

        String description = request.getParameter("description");
        String categoryId = request.getParameter("categoryId");
        String status = request.getParameter("status");

        if (foundError) {
            //exist error
            if (id.get().length() > 0) {
                //get data again
                ProductDTO productDTO = new ProductDTO();
                productDTO = productService.findBookById(Long.valueOf(id.get()));
                request.setAttribute("BOOK", productDTO);
            }

            CategoryDTO category = new CategoryDTO();
            category.setListResult(categoryService.findAll());
            request.setAttribute("CATEGORYLIST", category);
            request.setAttribute("ERROR", error);

        } else {
            url = SUCCESS;

            if (id.get().length() > 0) {
                //update
                ProductDTO productDTO = new ProductDTO();

                productDTO.setId(Long.valueOf(id.get()));
                productDTO.setTitle(title);
                productDTO.setAuthor(author);
                if (iQuantity != -1) {
                    productDTO.setQuantity(iQuantity);
                }
                if (fPrice != -1) {
                    productDTO.setPrice(fPrice);
                }
                if (status.equals("1")) {
                    productDTO.setStatus(true);
                } else {
                    productDTO.setStatus(false);
                }
                String importDate = request.getParameter("importDate");
                productDTO.setCategoryId(Long.valueOf(categoryId));
                productDTO.setPhoto(photo);
                productDTO.setDescription(description);
                if (this.productService.update(productDTO, importDate)) {
                    request.setAttribute("TYPE", "success");
                    request.setAttribute("MESSAGE", "Update success!");
                } else {
                    request.setAttribute("TYPE", "danger");
                    request.setAttribute("MESSAGE", "Update fail!");
                }

            } else {
                //create
                ProductDTO productDTO = new ProductDTO();
                productDTO.setTitle(title);
                productDTO.setAuthor(author);
                if (iQuantity != -1) {
                    productDTO.setQuantity(iQuantity);
                }
                if (fPrice != -1) {
                    productDTO.setPrice(fPrice);
                }
                productDTO.setCategoryId(Long.valueOf(categoryId));
                productDTO.setPhoto(photo);
                productDTO.setDescription(description);
                productDTO.setStatus(SystemConstant.ACTIVE);
                    Long idNew = this.productService.save(productDTO);
                if (idNew > 0) {
                    request.setAttribute("TYPE", "success");
                    request.setAttribute("MESSAGE", "Create success!");
                } else {
                    request.setAttribute("TYPE", "danger");
                    request.setAttribute("MESSAGE", "Create fail!");
                }
            }

        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
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
