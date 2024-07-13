/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.Class_SessionDBContext;
import DAO.SchoolYearDBContext;
import DAO.StudentDBContext;
import Entity.Account;
import Entity.ClassSession;
import Entity.SchoolYear;
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
@WebServlet(name = "PromoteToNewClass", urlPatterns = {"/admin/promote"})
public class PromoteToNewClass extends BaseRBACController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        SchoolYearDBContext db = new SchoolYearDBContext();

        // Lấy danh sách tất cả các năm học và lưu vào session
        ArrayList<SchoolYear> listYear = db.getAllSchoolYear();
        session.setAttribute("listYear", listYear);

        // Lấy tham số yid và newCsid từ request
        String yid = request.getParameter("yid");
        String newCsid = request.getParameter("newCsid");

        try {
            //khi ấn submit để insert sẽ gọi đến hàm POST
            if (request.getMethod().equalsIgnoreCase("POST")) {
                //Tạo 1 mảng rỗng để lưu các học sinh đã chọn để lưu vào lớp mới 
                String[] selectedStudents = request.getParameterValues("selectedStudents");

                //Nếu có học sinh được chọn
                if (selectedStudents != null) {
                    StudentDBContext studb = new StudentDBContext();
                    Class_SessionDBContext clsdb = new Class_SessionDBContext();
                    //Hàm đếm có bao nhiêu học sinh trong lớp
                    int currentStudentCount = db.getStudentCountByClassSession(Integer.parseInt(newCsid));

                    // Kiểm tra xem lớp học đã đầy chưa (giới hạn 20 học sinh)
                    if (currentStudentCount + selectedStudents.length > 20) {
                        //nếu lớp full => thông báo
                        session.setAttribute("error", "Class is already full. You cannot add more students.");
                        response.sendRedirect("promote?yid=" + yid + "&newCsid=" + newCsid);
                        return;
                    }

                    // Thêm từng học sinh vào lớp mới
                    for (String studentID : selectedStudents) {
                        StudentClassSession studentClassSession = new StudentClassSession();
                        studentClassSession.setStuid(studb.getStudentById(Integer.parseInt(studentID)));
                        studentClassSession.setCsid(clsdb.getClassSessionById(Integer.parseInt(newCsid)));
                        //Hàm addStudent
                        db.addStudentToClass(studentClassSession);
                    }
                } else {
                    session.setAttribute("error", "You need to choose students to add!");
                }
                response.sendRedirect("promote?yid=" + yid + "&newCsid=" + newCsid);
                return;
            }

            // Nếu chưa chọn năm học, tự động chọn năm học mới nhất
            if (yid == null || yid.isEmpty()) {
                SchoolYear newestYear = db.getNewestSchoolYear();
                if (newestYear != null) {
                    yid = String.valueOf(newestYear.getYid());
                    response.sendRedirect("promote?yid=" + yid);
                    return;
                }
            }

            // Nếu có yid được chọn từ request
            if (yid != null) {
                ArrayList<ClassSession> listClassSession = db.getClassSessionByYid(yid, true);
                session.setAttribute("listClassSession", listClassSession);

                ArrayList<SchoolYear> selectedYear = db.getSchoolYearById(yid);
                session.setAttribute("selectedYear", selectedYear);

                ArrayList<StudentClassSession> assignedStudents = db.getAssignedStudentId(yid);
                session.setAttribute("assignedStudents", assignedStudents);

                // Nếu có newCsid được chọn, lấy danh sách học sinh từ năm học trước
                if (newCsid != null && !newCsid.isEmpty()) {
                    ArrayList<StudentClassSession> studentClassSessionOldYear = db.getStudentsFromPreviousYears(yid);
                    session.setAttribute("studentClassSessionOldYear", studentClassSessionOldYear);

                    int currentStudentCount = db.getStudentCountByClassSession(Integer.parseInt(newCsid));
                    session.setAttribute("currentStudentCount", currentStudentCount);
                }

                // Xử lý tìm kiếm học sinh trong năm học trước
                String searchQuery = request.getParameter("searchQuery");
                if (searchQuery != null && !searchQuery.isEmpty()) {
                    // Tạo một danh sách mới để lưu trữ các học sinh lọc được
                    ArrayList<StudentClassSession> filteredStudents = new ArrayList<>();

                    // Lấy danh sách học sinh từ các năm học trước dựa trên yid
                    ArrayList<StudentClassSession> studentClassSessionOldYear = db.getStudentsFromPreviousYears(yid);

                    // Duyệt qua từng học sinh trong danh sách
                    for (StudentClassSession student : studentClassSessionOldYear) {
                        // Lấy tên lớp học của học sinh hiện tại và chuyển thành chữ thường
                        String className = student.getCsid().getClassID().getClname().toLowerCase();

                        // Kiểm tra xem tên lớp học có chứa searchQuery (chuyển thành chữ thường) không
                        if (className.contains(searchQuery.toLowerCase())) {
                            // Nếu có, thêm học sinh vào danh sách lọc
                            filteredStudents.add(student);
                        }
                    }
                    session.setAttribute("studentClassSessionOldYear", filteredStudents);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("/Error/404.jsp");
            return;
        }

        request.getRequestDispatcher("/FE_Admin/PromoteStudent.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account) throws ServletException, IOException {
        processRequest(request, response, account);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account) throws ServletException, IOException {
        processRequest(request, response, account);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
