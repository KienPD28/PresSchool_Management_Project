/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Parent;

import Authentication.BaseRBACController;
import DAO.AccountDBContext;
import Entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author NGUYEN THI KHANH VI
 */
@WebServlet(name = "ChangePassWordServlet", urlPatterns = {"/parent/change"})
public class ChangePasswordServlet extends BaseRBACController {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response, account);
     request.getRequestDispatcher("/FE_Parent/ChangePassWord.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
       String oldPass = request.getParameter("old-password");
        String newPass = request.getParameter("new-password");
        String confirmPass = request.getParameter("confirm-password");

        Account ac = (Account) request.getSession().getAttribute("account");
        
        if (oldPass == null || !oldPass.equals(ac.getPassword())) {
            request.setAttribute("mess", "Old password did not match!");
            
        } else if (!newPass.equals(confirmPass)) {
            request.setAttribute("mess", "New password and confirm password do not match!");
            
        } else {
            AccountDBContext acc = new AccountDBContext();
            request.setAttribute("mess", "Change password successfully!");
            ac.setPassword(newPass); 
            acc.changePass(ac.getPid().getPid(), newPass);
            request.getSession().setAttribute("account", ac);
        }
        request.getRequestDispatcher("/FE_Parent/ChangePassWord.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}