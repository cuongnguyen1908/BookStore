/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.web;

import cart.CartProductObject;
import utils.CalculateTotal;
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
@WebServlet(urlPatterns = {"/product-cart-update"})
public class UpdateCartProductController extends HttpServlet {

    private final String CART_PRODUCT_PAGE = "/views/web/viewProductCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            CartProductObject cart = (CartProductObject) SessionUtil.getInstance()
                    .getValue(request, "CARTPRODUCT");
            if (cart != null) {
                int quantityTotal = -1;
                try {
                    quantityTotal = cart.getCart().get(id).getTotalQuantity();
                } catch (NullPointerException e) {
                    request.setAttribute("TYPE", "danger");
                    request.setAttribute("MESSAGE", "Update fail!");
                    request.setAttribute("TOTAL", CalculateTotal.calculate(cart));
                    RequestDispatcher rd = request.getRequestDispatcher(CART_PRODUCT_PAGE);
                    rd.forward(request, response);
                }

                int flagInStock = quantityTotal - quantity;
                if (flagInStock >= 0) { // instock
                    //in stock
                    cart.updateQuantity(id, quantity);
                    request.setAttribute("TOTAL", CalculateTotal.calculate(cart));
                    request.setAttribute("TYPE", "success");
                    request.setAttribute("MESSAGE", "Update success!");
                } else { //out of stock
                    try {
                        request.setAttribute("TYPE", "danger");
                        request.setAttribute("TOTAL", CalculateTotal.calculate(cart));
                        request.setAttribute("MESSAGE", "Product "
                                + cart.getCart().get(id).getName() + " only have " + cart.getCart().get(id).getTotalQuantity() + " item in stock!");
                    } catch (NullPointerException e) {
                        request.setAttribute("TYPE", "danger");
                        request.setAttribute("MESSAGE", "Update fail!");
                        RequestDispatcher rd = request.getRequestDispatcher(CART_PRODUCT_PAGE);
                        rd.forward(request, response);
                    }

                }
            } else {
                request.setAttribute("TYPE", "danger");
                request.setAttribute("MESSAGE", "Update fail!");
                request.setAttribute("TOTAL", CalculateTotal.calculate(cart));

            }

            RequestDispatcher rd = request.getRequestDispatcher(CART_PRODUCT_PAGE);
            rd.forward(request, response);

        } catch (NumberFormatException e) {
            CartProductObject cart = (CartProductObject) SessionUtil.getInstance()
                    .getValue(request, "CARTPRODUCT");
            if (cart != null) {
                request.setAttribute("TOTAL", CalculateTotal.calculate(cart));
            }
            request.setAttribute("TYPE", "danger");
            request.setAttribute("MESSAGE", "Update fail!");
             RequestDispatcher rd = request.getRequestDispatcher(CART_PRODUCT_PAGE);
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
