/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import dtos.CodeDTO;
import dtos.CodeEditErrorDTO;
import services.ICodeService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author nguyen
 */
@WebServlet(urlPatterns = {"/admin-create-code"})
public class EditCodeController extends HttpServlet {

    private final String SUCCESS_PAGE = "/admin-code";
    private final String ERROR_PAGE = "/views/admin/editCodeError.jsp";

    @Inject
    private ICodeService codeService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean foundErr = false;
        CodeEditErrorDTO error = new CodeEditErrorDTO();
        String url = ERROR_PAGE;
        String code = request.getParameter("code");

        try {
            float percent = Float.parseFloat(request.getParameter("percent"));
            String date = request.getParameter("date");
            if (code.trim().length() < 1 || code.trim().length() > 50) {
                foundErr = true;
                error.setCodeLenghtError("Code must be > 0 and < 50 character");
            }

            if (this.codeService.existCodeByName(code)) {
                foundErr = true;
                error.setCodeExist("Code has been exist");
            }

            if (percent < 1 || percent > 100) {
                foundErr = true;
                error.setPercentError("Percent must be > 0 and < 100");
            }
            if (foundErr) {
                request.setAttribute("ERROR", error);
                System.out.println("worked error");
            }else {
                CodeDTO dto = new CodeDTO();
                dto.setCode(code);
                dto.setPercent(percent);
                if (this.codeService.save(dto, date) > 0) {
                    url = SUCCESS_PAGE;
                    request.setAttribute("TYPE", "success");
                    request.setAttribute("MESSAGE", "Create success!");
                }

            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

        }catch (NumberFormatException e) {
            if (code.trim().length() < 1 || code.trim().length() > 50) {
                error.setCodeLenghtError("Code must be > 0 and < 50 character");
            }
            error.setPercentError("Percent is incorrect format");
            request.setAttribute("ERROR", error);
            RequestDispatcher rd = request.getRequestDispatcher(ERROR_PAGE);
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
