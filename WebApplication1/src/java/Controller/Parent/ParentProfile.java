/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Parent;

import Authentication.BaseRBACController;
import DAO.ParentDBContext;
import DAO.StudentDBContext;
import Entity.Account;
import Entity.Parent;
import Entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name="ParentProfile", urlPatterns={"/parent/parent-profile"})
public class ParentProfile extends BaseRBACController {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
    throws ServletException, IOException {
              HttpSession session = request.getSession();
        String pid = request.getParameter("pid");
        Account acc = (Account) session.getAttribute("account");
        if(acc!=null) {
        ParentDBContext parentDB = new ParentDBContext();
            Parent pa = parentDB.getParentByid(acc.getPid().getPid());
            request.setAttribute("pa", pa);

            StudentDBContext studentDB = new StudentDBContext();
            List<Student> stu = studentDB.getStudentByIdUser(pa.getPid());

            if (stu == null) {
                stu = new ArrayList<>(); // Ensure it's not null
            }
            request.setAttribute("stu", stu);

            request.getRequestDispatcher("/FE_Parent/ParentProfile.jsp").forward(request, response);   }
        else {
            response.sendRedirect("login");
        }
     
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
