/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.ClassDBContext;
import DAO.Class_SessionDBContext;
import DAO.SchoolYearDBContext;
import DAO.StudentClassSessionDBContext;
import DAO.StudentDBContext;
import Entity.Account;
import Entity.Student;
import Entity.Class;
import Entity.ClassSession;
import Entity.StudentClassSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author NGUYEN THI KHANH VI
 */
@WebServlet(name = "StudentController", urlPatterns = {"/admin/student"})
public class StudentController extends BaseRBACController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        StudentClassSessionDBContext stuDB = new StudentClassSessionDBContext();
        StudentDBContext sdc = new StudentDBContext();
        //ClassDBContext cdb = new ClassDBContext();
        Class_SessionDBContext cl = new Class_SessionDBContext();
        SchoolYearDBContext sy = new SchoolYearDBContext();

        String classId = request.getParameter("classId");
        String indexPage = request.getParameter("index");

        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);

        //List<Class> classList = cdb.getAllClasses();
        List<ClassSession> classIDs = cl.getAllClass();
        request.setAttribute("classIDs", classIDs);

        if (classId != null && !classId.isEmpty()) {
            List<StudentClassSession> studentList = stuDB.getStudentsByClassIdWithPaging(classId, index);
            int count = stuDB.getTotalStudentsByClassId(classId);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            request.setAttribute("studentList", studentList);
            request.setAttribute("index", index);
            request.setAttribute("endPage", endPage);
            request.setAttribute("classId", classId);
        } else {

            // Get students for the latest school year
            List<StudentClassSession> studentList = stuDB.getStudentClassSessionByLatestSchoolYearWithPaging(index);
            int count = stuDB.getTotalStudentByLatestSchoolYear();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            request.setAttribute("allStudent", studentList);
            request.setAttribute("index", index);
            request.setAttribute("endPage", endPage);
        }

        request.getRequestDispatcher("/FE_Admin/CRUD_Student.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response,account);
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
