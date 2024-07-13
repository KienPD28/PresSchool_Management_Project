/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Lecturers;

import Authentication.BaseRBACController;
import DAO.LecturerClassSession;
import DAO.LecturersDBContext;
import Entity.Account;
import Entity.Lecturers;
import Entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author hidung
 */
@WebServlet(name = "ListStudent", urlPatterns = {"/lecturers/liststudent"})
public class ListStudent extends BaseRBACController {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        String lid = request.getParameter("lid");

 
            if (lid != null) {
                LecturersDBContext lecDB = new LecturersDBContext();
                Lecturers lecturer = lecDB.getLecturerByid(Integer.parseInt(lid));

                request.setAttribute("lec", lecturer);

                // Initialize contexts for accessing database
                LecturerClassSession lecClassSessionDB = new LecturerClassSession();
                List<Student> studentList = lecClassSessionDB.getStudentsForLecturers(Integer.parseInt(lid));

                request.setAttribute("allStudent", studentList);

                // Forward to JSP page
                request.getRequestDispatcher("/FE_Lecturers/HomeLecturers.jsp").forward(request, response);
            } else {
                response.sendRedirect("login"); // Redirect to login page if no account found
            }
        
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
