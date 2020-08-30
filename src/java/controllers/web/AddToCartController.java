/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.web;

import cart.CartProductObject;
import constant.SystemConstant;
import dtos.ProductCartDTO;
import dtos.UserDTO;
import utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author nguyen
 */
@WebServlet(urlPatterns = {"/product-cart-add"})
public class AddToCartController extends HttpServlet {

    private final String HOME_PAGE = "/product-get";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            UserDTO user = (UserDTO) SessionUtil.getInstance().getValue(request, "USERMODEL");
            if (user == null || (user.getRoleId() == SystemConstant.ADMIN)) {
                response.sendRedirect(request.getContextPath() 
                        + "/login?action=login&message=Please Login&alert=danger");
            }

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            int quantityTotal = Integer.parseInt(request.getParameter("quantity"));
            float price = Float.parseFloat(request.getParameter("price"));

            int flagQuantity = 0;
            CartProductObject cart = (CartProductObject) SessionUtil.getInstance().getValue(request, "CARTPRODUCT");

            if (cart == null) {
                cart = new CartProductObject();
            }
            int quantityInCart = -1;

            quantityInCart = cart.getQuantityInCart(Long.valueOf(id));

            if (quantityInCart != -1) { // cart exist
                flagQuantity = quantityTotal - (quantityInCart + 1);
                if (flagQuantity > 0) { //in stock
                    ProductCartDTO productCartDTO = new ProductCartDTO();
                    productCartDTO.setName(name);
                    productCartDTO.setPrice(price);
                    productCartDTO.setTotalQuantity(quantityTotal);
                    cart.addItemToCard(Long.valueOf(id), productCartDTO);
                    SessionUtil.getInstance().putValue(request, "CARTPRODUCT", cart);
                    request.setAttribute("TYPE", "success");
                    request.setAttribute("MESSAGE", "Add to cart success!");
                } else { // out of stock
                    request.setAttribute("TYPE", "warning");
                    request.setAttribute("MESSAGE", "Add to cart fail! No more item in stock");
                }
            } else {
                ProductCartDTO productCartDTO = new ProductCartDTO();
                productCartDTO.setName(name);
                productCartDTO.setPrice(price);
                productCartDTO.setTotalQuantity(quantityTotal);
                cart.addItemToCard(Long.valueOf(id), productCartDTO);
                SessionUtil.getInstance().putValue(request, "CARTPRODUCT", cart);
                request.setAttribute("TYPE", "success");
                request.setAttribute("MESSAGE", "Add to cart success!");
            }

            RequestDispatcher rd = request.getRequestDispatcher(HOME_PAGE);
            rd.forward(request, response);
        } catch (NullPointerException | NumberFormatException e) {
            request.setAttribute("TYPE", "danger");
            request.setAttribute("MESSAGE", "Add to cart fail!");
            RequestDispatcher rd = request.getRequestDispatcher(HOME_PAGE);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the
    // code.">
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
