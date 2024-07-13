/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Admin;

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
 * @author DELL
 */
@WebServlet(name="HomeAdmin", urlPatterns={"/admin/adminhome"})
public class HomeAdmin extends BaseRBACController {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
    throws ServletException, IOException {
        AccountDBContext dbContext = new AccountDBContext();
        
        // Retrieve the total number of accounts, students, and teachers
        int totalAccounts = dbContext.countAccounts();
        int totalStudents = dbContext.countStudents();
        int totalTeachers = dbContext.countTeachers();
   
        // Set the total account count as a request attribute
        request.setAttribute("totalAccounts", totalAccounts);
        request.setAttribute("totalStudents", totalStudents);
        request.setAttribute("totalTeachers", totalTeachers);
        
        // Forward the request to the JSP page
        request.getRequestDispatcher("/FE_Admin/Admin_Home.jsp").forward(request, response);
    } 

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
    throws ServletException, IOException {
        processRequest(request, response, account);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
    throws ServletException, IOException {
        processRequest(request, response, account);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
