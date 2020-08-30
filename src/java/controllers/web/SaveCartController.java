/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.web;

import cart.CartProductObject;
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
import services.IOrderDetailService;
import services.IOrderService;
import utils.SessionUtil;

/**
 *
 * @author nguyen
 */
@WebServlet(urlPatterns = {"/save-cart"})
public class SaveCartController extends HttpServlet {

    private final String EDIT_PAGE = "/views/admin/editProduct.jsp";
    private final String HOME_PAGE = "/product-get";

    @Inject
    private IProductService productService;

    @Inject
    private ICategoryService categoryService;

    @Inject
    private IOrderService orderService;

    @Inject
    private IOrderDetailService orderDetailService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            CartProductObject cart = (CartProductObject) SessionUtil.getInstance()
                    .getValue(request, "CARTPRODUCT");
            if (cart != null) {
                String id = request.getParameter("id");
                String codeId = request.getParameter("codeId");
                String total = request.getParameter("total");
                String finalTotal = request.getParameter("finalTotal");
                float fTotal = 0;
                if (finalTotal != null) { // apply code
                    System.out.println("worked use code");
                    Long lCodeId = Long.valueOf(codeId);
                    fTotal = Float.parseFloat(finalTotal);
                    //create order
                    Long oderId = this.orderService.save(Long.valueOf(id), "test name", fTotal);
                    if (oderId > 0) {
                        //check & update quantity

                        boolean flagUpdate = this.productService.updateQuantity(cart);
                        if (flagUpdate) {
                            //create order detail
                            if (this.orderDetailService.save(cart, Long.valueOf(id), oderId)) {
                                SessionUtil.getInstance().removeValue(request, "CARTPRODUCT");
                                request.setAttribute("TYPE", "success");
                                request.setAttribute("MESSAGE", "Order success!");
                            }
                        } else { // flag update fail
                            SessionUtil.getInstance().removeValue(request, "CARTPRODUCT");
                            request.setAttribute("TYPE", "danger");
                            request.setAttribute("MESSAGE", "Some thing error. Please try later!");
                        }
                    } else { // order id fail
                        request.setAttribute("TYPE", "danger");
                        request.setAttribute("MESSAGE", "Order fail!");
                    }

                } else { // not use code
                    System.out.println("worked not use code");
                    fTotal = Float.parseFloat(total);
                    
                    
                }

            } else { //cart null
                request.setAttribute("TYPE", "danger");
                request.setAttribute("MESSAGE", "Order fail!");
            }

        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher(HOME_PAGE);
            rd.forward(request, response);
        }

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
