/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.StudentDBContext;
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
@WebServlet(name="DeleteStudentController", urlPatterns={"/deleteStudent"})
public class DeleteStudentController extends BaseRBACController {
   
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
    throws ServletException, IOException {
        
    } 

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
    throws ServletException, IOException {
        processRequest(request, response, account);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
    throws ServletException, IOException {
        String stuid = request.getParameter("stuid");
        String pid = request.getParameter("pid");
        StudentDBContext stuDB = new StudentDBContext();
        stuDB.deleteStudentAndParent(stuid, pid);
        response.sendRedirect("student");  
    }
  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
