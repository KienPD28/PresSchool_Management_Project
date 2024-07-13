/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.SchoolYearDBContext;
import DAO.StudentDBContext;
import Entity.Account;
import Entity.Lecturers_Class_Session;
import Entity.Student;
import Entity.StudentClassSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
@WebServlet(name = "SchoolYearHistory", urlPatterns = {"/admin/historyschoolyear"})
public class SchoolYearHistory extends BaseRBACController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        SchoolYearDBContext db = new SchoolYearDBContext();
        StudentDBContext studb = new StudentDBContext();

        // Lấy danh sách tất cả sinh viên
        ArrayList<Student> students = studb.getAllStudent();
        session.setAttribute("students", students);

        // Lấy ID sinh viên được chọn từ các tham số
        String selectedStuid = request.getParameter("selectedStudent");

        try {
            // Nếu có ID sinh viên được chọn
            if (selectedStuid != null && !selectedStuid.isEmpty()) {
                // Lấy lịch sử năm học của sinh viên được chọn
                ArrayList<StudentClassSession> history = db.getHistorySchoolYearbyStuid(selectedStuid);
                session.setAttribute("selectedStuid", selectedStuid);
                session.setAttribute("history", history);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Error/404.jsp");
            return;
        }

        // Forward to JSP page
        request.getRequestDispatcher("/FE_Admin/SchoolYearHistory.jsp").forward(request, response);
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
