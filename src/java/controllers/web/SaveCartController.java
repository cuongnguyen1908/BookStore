/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.web;

import cart.CartProductObject;
import dtos.UserCodeDTO;
import services.IProductService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import services.IOrderDetailService;
import services.IOrderService;
import services.IUserCodeService;
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
    private IOrderService orderService;

    @Inject
    private IOrderDetailService orderDetailService;

    @Inject
    private IUserCodeService userCodeService;

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
                if (finalTotal.trim().length() > 1) { // apply code
                    fTotal = Float.parseFloat(finalTotal);
                } else { // not use code
                    fTotal = Float.parseFloat(total);
                }

                //create order
                Random rand = new Random();
                Long oderId = this.orderService.save(Long.valueOf(id), "Order no " + rand.nextInt(10000), fTotal);
                if (oderId > 0) { //create order success
                    //check & update quantity
                    boolean flagUpdate = this.productService.updateQuantity(cart);
                    if (flagUpdate) { //check and update minus 
                        //create order detailsuccess
                        if (this.orderDetailService.save(cart, Long.valueOf(id), oderId)) { //create orderdetail success
                            SessionUtil.getInstance().removeValue(request, "CARTPRODUCT");
                            if (finalTotal.trim().length() > 1) { //has use code
                                //create user code
                                Long lCodeId = Long.valueOf(codeId);
                                UserCodeDTO userCodeDTO = new UserCodeDTO();
                                userCodeDTO.setCode_id(lCodeId);
                                userCodeDTO.setUserId(Long.valueOf(id));
                                if (this.userCodeService.save(userCodeDTO) == 0) { // create code user fail
                                    SessionUtil.getInstance().removeValue(request, "CARTPRODUCT");
                                    boolean flagDeleteOrder = this.orderService.deleteOrderById(oderId);
                                    request.setAttribute("TYPE", "danger");
                                    request.setAttribute("MESSAGE", "Some thing error. Please try later!");
                                    RequestDispatcher rd = request.getRequestDispatcher(HOME_PAGE);
                                    rd.forward(request, response);
                                }
                            }
                            request.setAttribute("TYPE", "success");
                            request.setAttribute("MESSAGE", "Save cart success!");
                        } else { //create order detail fail
                            SessionUtil.getInstance().removeValue(request, "CARTPRODUCT");

                            boolean flagDeleteOrder = this.orderService.deleteOrderById(oderId);
                            request.setAttribute("TYPE", "danger");
                            request.setAttribute("MESSAGE", "Some thing error. Please try later!");
                            RequestDispatcher rd = request.getRequestDispatcher(HOME_PAGE);
                            rd.forward(request, response);
                        }
                    } else { // flag update fail
                        SessionUtil.getInstance().removeValue(request, "CARTPRODUCT");
                        boolean flagDeleteOrder = this.orderService.deleteOrderById(oderId);
                        request.setAttribute("TYPE", "danger");
                        request.setAttribute("MESSAGE", "Some thing error. Please try later!");
                    }
                } else { // create order fail

                    request.setAttribute("TYPE", "danger");
                    request.setAttribute("MESSAGE", "Some thing error. Please try later!");
                }

            } else { //cart null
                request.setAttribute("TYPE", "danger");
                request.setAttribute("MESSAGE", "Some thing error. Please try later!");
            }
            RequestDispatcher rd = request.getRequestDispatcher(HOME_PAGE);
            rd.forward(request, response);

        } catch (Exception e) { // if invalid or nul
            request.setAttribute("TYPE", "danger");
            request.setAttribute("MESSAGE", "Some thing error. Please try later!");
            RequestDispatcher rd = request.getRequestDispatcher(HOME_PAGE);
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
