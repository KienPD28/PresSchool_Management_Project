/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.StudentClassSessionDBContext;
import Entity.Account;
import Entity.StudentClassSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NGUYEN THI KHANH VI
 */
@WebServlet(name="SearchStudentInactive", urlPatterns={"/admin/search-student-inactive"})
public class SearchStudentInactive extends BaseRBACController {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
    throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");

        String searchInput = request.getParameter("searchInput");
        StudentClassSessionDBContext sdb = new StudentClassSessionDBContext();
        List<StudentClassSession> combinedResults = new ArrayList<>();

        if (searchInput != null && !searchInput.isEmpty()) {
            try {
                int id = Integer.parseInt(searchInput);
                StudentClassSession studentByID = sdb.getStudentInactiveById(id);
                if (studentByID != null) {
                    combinedResults.add(studentByID);
                }
            } catch (NumberFormatException e) {
                // Search by name
                List<StudentClassSession> studentsByName = sdb.getStudentInactiveByName(searchInput);
                combinedResults.addAll(studentsByName);
            }
        }

        request.setAttribute("search", combinedResults);
        request.getRequestDispatcher("/FE_Admin/Inactive_Student.jsp").forward(request, response);
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
