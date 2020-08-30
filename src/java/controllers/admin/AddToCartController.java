/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import cart.CartObject;
import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.SessionUtil;

/**
 *
 * @author nguyen
 */
@WebServlet(urlPatterns = {"/admin-cart-add"})
public class AddToCartController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        CartObject cart = (CartObject) SessionUtil.getInstance().getValue(request, "CART");

        if (cart == null) {
            cart = new CartObject();
            String fullName = request.getParameter("fullName");
            cart.addItemToCard(Long.valueOf(id), fullName);
            SessionUtil.getInstance().putValue(request, "CART", cart);
            request.setAttribute("TYPE", "success");
            request.setAttribute("MESSAGE", "Add to cart success!");
        } else {
            boolean existIncart = false;

            //check id in cart
            for (Map.Entry<Long, String> entry : cart.getCart().entrySet()) {
                if (entry.getKey() == Long.valueOf(id)) {
                    //exist in cart
                    existIncart = true;
                    request.setAttribute("TYPE", "warning");
                    request.setAttribute("MESSAGE", "User has already in cart!");
                    break;
                }
            }

            if (!existIncart) {
                String fullName = request.getParameter("fullName");
                cart.addItemToCard(Long.valueOf(id), fullName);
                SessionUtil.getInstance().putValue(request, "CART", cart);
                request.setAttribute("TYPE", "success");
                request.setAttribute("MESSAGE", "Add to cart success!");
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("/admin-view");
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
