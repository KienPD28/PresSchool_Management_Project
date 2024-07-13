/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.SchoolYearDBContext;
import Entity.Account;
import Entity.ClassSession;
import Entity.Lecturers_Class_Session;
import Entity.SchoolYear;
import Entity.StudentClassSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ClassesController", urlPatterns = {"/admin/classController"})
public class ClassesController extends BaseRBACController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        SchoolYearDBContext yearDB = new SchoolYearDBContext();
        // Lấy danh sách tất cả các năm học
        ArrayList<SchoolYear> listYear = yearDB.getAllSchoolYear();

        // Lấy tham số yid và csid từ request
        String yid = request.getParameter("yid");
        String csid = request.getParameter("csid");
        try {

            // Nếu không có yid trong request, lấy yid của năm học mới nhất
            if (yid == null || yid.isEmpty()) {
                SchoolYear newestYear = yearDB.getNewestSchoolYear();
                if (newestYear != null) {
                    yid = String.valueOf(newestYear.getYid());
                }
            }
            //Nếu có yid , lấy các thông tin liên quan đến năm học
            if (yid != null && !yid.isEmpty()) {

                // Lấy thông tin năm học dựa theo id
                ArrayList<SchoolYear> selectedYear = yearDB.getSchoolYearById(yid);
                request.setAttribute("selectedYear", selectedYear);

                // Lấy danh sách các lớp học trong năm học đã chọn và có status 'Active'
                ArrayList<ClassSession> listClassSession = yearDB.getClassSessionByYid(yid, true);
                request.setAttribute("listClassSession", listClassSession);

            }

            //nếu có csid, lấy các thông tin liên quan đến lớp học
            if (csid != null && !csid.isEmpty()) {

                ArrayList<Lecturers_Class_Session> lecClassSessionbyCsid2 = yearDB.getLecturersByCsid(Integer.parseInt(csid));
                request.setAttribute("lecClassSessionbyCsid2", lecClassSessionbyCsid2);

                ArrayList<StudentClassSession> studentClassSessionbyCsid = yearDB.getStudentClassSessionbyCsid(Integer.parseInt(csid));
                request.setAttribute("studentClassSessionbyCsid", studentClassSessionbyCsid);

            }
        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("Error/404.jsp");
            return;
        }

        request.setAttribute("yid", yid);
        request.setAttribute("listYear", listYear);
        request.getRequestDispatcher("/FE_Admin/Classes_function.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response, account);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response,account);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
