/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import dtos.EditErrorDTO;
import dtos.RoleDTO;
import dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import services.IUserService;
import utils.HashFunctions;
import services.IRoleService;
import utils.SessionUtil;

/**
 *
 * @author nguyen
 */
@WebServlet(urlPatterns = {"/admin-process-edit"})
public class ProcessEditController extends HttpServlet {

    private final String ERROR_PAGE = "/views/admin/editError.jsp";
    private final String SUCCESS = "/admin-view";

    @Inject
    private IUserService userService;

    @Inject
    private IRoleService roleService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean foundError = false;
        String url = ERROR_PAGE;
        String regex = "^[a-zA-Z0-9][a-z0-9_\\.]{1,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
        EditErrorDTO error = new EditErrorDTO();
        Optional<String> id = Optional.ofNullable(request.getParameter("id"));
        String fileName = request.getParameter("file");
        if (fileName == null) {
            foundError = true;
            error.setFileEmpty("Please select file");
        }

        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");

        if (password.trim().length() < 8) {
            foundError = true;
            error.setPasswordLengthError("Password length must be > 8 letter");
        } else {
            if (!password.trim().equals(rePassword.trim())) {
                foundError = true;
                error.setConfirmPasswordNotMatch("Password is not match");
            }
        }
        String fullName = request.getParameter("fullName");
        if (!(fullName.trim().length() > 0)) {
            foundError = true;
            error.setFullNameLengthError("Fullname length must be > 8 characters");
        }

        String email = request.getParameter("email");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email.trim());
        boolean checkEmail = matcher.matches();
        if (checkEmail == false) {
            foundError = true;
            error.setEmailError("Only letters (a-z), number (0-9), > 8 digit and periods (@ .) are allowed");
        }

        String phone = request.getParameter("phone");

        if (phone.trim().length() < 8) {
            foundError = true;
            error.setPhoneLengthError("Phone length must be > 8 digit");
        }
        String role = request.getParameter("typeRoleId");
        String status = request.getParameter("status");
        if (foundError) {
            //exist errorstatus
            if (id.get().length() > 0) {
                UserDTO user = new UserDTO();
                user = userService.findUserByIdAndStatus(Long.valueOf(id.get()), true);
                request.setAttribute("USER", user);
            }

            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setListResult(roleService.findAll());
            request.setAttribute("ROLELIST", roleDTO);
            request.setAttribute("ERROR", error);

        } else {
            url = SUCCESS;

            if (id.get().length() > 0) {
                //update
                UserDTO user = new UserDTO();

                UserDTO userAdmin = (UserDTO) SessionUtil.getInstance().getValue(request, "USERMODEL");
                if (userAdmin.getId() == Long.valueOf(id.get())) {
                    user.setRoleId(Long.valueOf(1 + ""));
                    user.setStatus(true);
                } else {
                    if (status.equals("1")) {
                        user.setStatus(true);
                    } else {
                        user.setStatus(false);

                    }
                    user.setRoleId(Long.valueOf(role));
                }

                user.setId(Long.valueOf(id.get()));
                String passHash = HashFunctions.getHash(password.trim().getBytes(), "SHA-256");
                user.setPassword(passHash);
                user.setFullName(fullName);
                user.setEmail(email);
                user.setPhone(phone);
                if (this.userService.update(user)) {
                    request.setAttribute("TYPE", "success");
                    request.setAttribute("MESSAGE", "Update success!");
                } else {
                    request.setAttribute("TYPE", "danger");
                    request.setAttribute("MESSAGE", "Update fail!");
                }

            } else {
                //create
                String username = request.getParameter("username");
                boolean checkUserExist = this.userService.existUserByUsername(username);

                if (username.trim().length() < 8) {
                    foundError = true;
                    error.setUsernameLengthError("Username length must be > 8 characters");
                    url = ERROR_PAGE;
                }

                if (checkUserExist) {
                    foundError = true;
                    error.setUsernameHasExist("Username has exist");
                    url = ERROR_PAGE;
                }

                if (foundError) {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setListResult(roleService.findAll());
                    request.setAttribute("ROLELIST", roleDTO);
                    request.setAttribute("ERROR", error);
                }

                UserDTO user = new UserDTO();
                user.setUsername(username);
                String passHash = HashFunctions.getHash(password.trim().getBytes(), "SHA-256");
                user.setPassword(passHash);
                user.setFullName(fullName);
                user.setEmail(email);
                user.setRoleId(Long.valueOf(role));
                user.setPhone(phone);
                user.setStatus(true);
                if (!this.userService.existUserByUsername(username)) {
                    Long idNew = this.userService.save(user);
                    request.setAttribute("TYPE", "success");
                    request.setAttribute("MESSAGE", "Create success!");
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
