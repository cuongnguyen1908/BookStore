/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.web;

import cart.CartProductObject;
import dtos.CodeDTO;
import services.IProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import services.ICodeService;
import services.IUserCodeService;
import utils.CalculateTotal;
import utils.SessionUtil;

/**
 *
 * @author nguyen
 */
@WebServlet(urlPatterns = {"/apply-code"})
public class ApplyCodeController extends HttpServlet {

    private final String CART_PAGE = "/views/web/viewProductCart.jsp";

    @Inject
    private IProductService productService;

    @Inject
    private ICodeService codeService;

    @Inject
    IUserCodeService userCodeService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String id = request.getParameter("id");
            float total = Float.parseFloat(request.getParameter("total"));
            String code = request.getParameter("code");
            CodeDTO codeDTO = this.codeService.findCodeByName(code);

            if (codeDTO == null) {
                request.setAttribute("TYPE", "danger");
                request.setAttribute("MESSAGE", "Code is invalid");
                request.setAttribute("TOTAL", total);
            } else { //exist
                if (this.userCodeService
                        .existCodeByUserIdAndCodeId(Long.valueOf(id), codeDTO.getId())) {
                    //has been use
                    request.setAttribute("TYPE", "danger");
                    request.setAttribute("MESSAGE", "Code has been used");
                    request.setAttribute("TOTAL", total);
                } else {
                    //not use
                    float discount = (total * codeDTO.getPercent()) / 100;
                    float afterApplyCode = total - discount;
                    
                    request.setAttribute("TYPE", "success");
                    request.setAttribute("MESSAGE", "Apply success");
                    request.setAttribute("DISCOUNT", discount);
                    request.setAttribute("AFTERAPPLY", afterApplyCode);
                    request.setAttribute("TOTAL", total);
                    request.setAttribute("IDCODE", codeDTO.getId());
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(CART_PAGE);
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            CartProductObject cart = (CartProductObject) SessionUtil.getInstance()
                .getValue(request, "CARTPRODUCT");
            if (cart != null) {
                request.setAttribute("TOTAL", CalculateTotal.calculate(cart));
            }
            request.setAttribute("TYPE", "danger");
            request.setAttribute("MESSAGE", "Apply code fail");
            RequestDispatcher rd = request.getRequestDispatcher(CART_PAGE);
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
