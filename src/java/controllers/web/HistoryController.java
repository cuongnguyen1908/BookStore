/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.web;

import constant.SystemConstant;
import dtos.OrderDTO;
import dtos.UserDTO;
import utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.inject.Inject;
import services.IOrderDetailService;
import services.IOrderService;

/**
 * @author nguyen
 */
@WebServlet(urlPatterns = {"/history"})
public class HistoryController extends HttpServlet {

    @Inject
    private IOrderService orderService;

    @Inject
    private IOrderDetailService orderDetailService;

    private final String HISTORY_PAGE = "/views/web/history.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        UserDTO user = (UserDTO) SessionUtil.getInstance().getValue(request, "USERMODEL");
        if (user == null || (user.getRoleId() == SystemConstant.ADMIN)) {
            response.sendRedirect(request.getContextPath()
                    + "/login?action=login&message=Please Login&alert=danger");
        } else { //customer exist 
            OrderDTO order = new OrderDTO();
            order.setListResult(this.orderService.findAllByUserId(user.getId()));
            if (order.getListResult().size() > 0) { // exist order
                for (int i = 0; i < order.getListResult().size(); i++) {
                    order.getListResult().get(i).setOrderDetail(this.orderDetailService.findAllByOrderId(order.getListResult().get(i).getId()));
                }
            }
            request.setAttribute("ORDERLIST", order);
            RequestDispatcher rd = request.getRequestDispatcher(HISTORY_PAGE);
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
