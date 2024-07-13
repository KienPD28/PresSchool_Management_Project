/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.Class_SessionDBContext;
import DAO.ParentDBContext;
import DAO.StudentClassSessionDBContext;
import DAO.StudentDBContext;
import Entity.Account;
import Entity.ClassSession;
import Entity.Student;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author NGUYEN THI KHANH VI
 */
@WebServlet(name = "UpdateStudentController", urlPatterns = {"/admin/update-student"})
public class UpdateStudentController extends BaseRBACController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {

//        ClassDBContext clDB = new ClassDBContext();
//        List<Entity.Class> clList = clDB.getAllClasses();
//        request.setAttribute("clList", clList);

        Class_SessionDBContext cl = new Class_SessionDBContext();
          List<ClassSession> classIDs = cl.getAllClass();
        request.setAttribute("classIDs", classIDs);

        request.getRequestDispatcher("/FE_Admin/Update_Student.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response, account);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account) throws ServletException, IOException {
        String stuid = request.getParameter("stuid");
        String sname = request.getParameter("sname");
        String dob = request.getParameter("dob");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String address = request.getParameter("address");
        String className = request.getParameter("className");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        // Validate name, dob, address
        if (sname == null || sname.trim().isEmpty()) {
            request.setAttribute("nameError", "Student Name is required.");
            processRequest(request, response, account);
            return;
        }
        try {
            LocalDate dobDate = LocalDate.parse(dob);
        } catch (DateTimeParseException e) {
            request.setAttribute("dobError", "Invalid date format for Date of Birth. Please use yyyy-MM-dd format.");
            processRequest(request, response, account);
            return;
        }

        if (address == null || address.trim().isEmpty()) {
            request.setAttribute("addressError", "Address is required.");
            processRequest(request, response, account);
            return;
        }

       
        int newClassId = 0;
        try {
            newClassId = Integer.parseInt(className);
        } catch (NumberFormatException e) {
            request.setAttribute("classError", "Invalid Class ID.");
            processRequest(request, response, account);
            return;
        }

        StudentClassSessionDBContext stuDB = new StudentClassSessionDBContext();
        int totalStudentsInNewClass = stuDB.getTotalStudentsByClassId(String.valueOf(newClassId));

        if (totalStudentsInNewClass >= 20) {
            request.setAttribute("classError", "The class is full. Please choose another class!");
            processRequest(request, response, account);
            return;
        }

       // Update student information
        int studentId = 0;
        try {
            studentId = Integer.parseInt(stuid);
        } catch (NumberFormatException e) {
            request.setAttribute("classError", "Invalid Student ID.");
            processRequest(request, response, account);
            return;
        }
        
        //// Create Student object
        Student updatedStudent = new Student();
        updatedStudent.setStuid(studentId);
        updatedStudent.setSname(sname);
        updatedStudent.setDob(dob);
        updatedStudent.setGender(gender);
        updatedStudent.setAddress(address);

      // Update students and classes
        StudentDBContext studentDB = new StudentDBContext();
        studentDB.updateStudent(updatedStudent);
        studentDB.updateStudentClass(studentId, newClassId);
        studentDB.updateStudentStatus(studentId, status);

 
        updateParentStatusIfOnly1Student(studentId, status);
          
          
        response.sendRedirect("student");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //Update parent status if the parent has only 1 child
     private void updateParentStatusIfOnly1Student(int studentId, boolean studentStatus) {
        StudentDBContext studentDB = new StudentDBContext();
               ParentDBContext parent= new ParentDBContext();
        int parentId = studentDB.getParentIdByStudentId(studentId);
        int activeStudentsCount = studentDB.countActiveStudentsByParentId(parentId);

        if (activeStudentsCount < 1) {
            parent.updateParentStatus(parentId, studentStatus);
        }
    }
}

