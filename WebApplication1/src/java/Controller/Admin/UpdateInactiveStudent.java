/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.ParentDBContext;
import DAO.StudentDBContext;
import Entity.Account;
import Entity.Student;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author NGUYEN THI KHANH VI
 */
@WebServlet(name = "UpdateInactiveStudent", urlPatterns = {"/admin/update-inactive-student"})
public class UpdateInactiveStudent extends BaseRBACController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
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
        String stuid = request.getParameter("stuid");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        try {
            int Stuid = Integer.parseInt(stuid);

            Student stu = new Student();
            stu.setStuid(Stuid);
            stu.setStatus(status);

            StudentDBContext stuDB = new StudentDBContext();
            stuDB.updateStudentStatus(Stuid, status);

            // Get parent ID
            int parentId = stuDB.getParentIdByStudentId(Stuid);

            // Count the number of active students of the parent
            int activeStudentCount = stuDB.countActiveStudentsByParentId(parentId);

            if (activeStudentCount > 0) {
                //update status parent
                ParentDBContext parentDB = new ParentDBContext();
                parentDB.updateParentStatus(parentId, true);
            }

            response.sendRedirect("student");
        } catch (NumberFormatException e) {
        }

        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
