/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Authentication;

import DAO.AccountDBContext;
import DAO.LecturersDBContext;
import DAO.SchoolYearDBContext;
import DAO.StudentDBContext;
import Entity.Account;
import Entity.Lecturers;
import Entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DELL
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("FE_Parent/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy tên đăng nhập và mật khẩu từ request
        String user_raw = request.getParameter("username");
        String pass_raw = request.getParameter("password");
        // Lấy giá trị checkbox "remember me"
        String rememberMe = request.getParameter("rememberMe");
        AccountDBContext db = new AccountDBContext();
        StudentDBContext stu = new StudentDBContext();
        LecturersDBContext lec = new LecturersDBContext();

        // check tài khoản với user và pass
        Account acc = db.getByUsernamePassword(user_raw, pass_raw);

        // Inside your login servlet or controller
        HttpSession session = request.getSession();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        session.setAttribute("dateN", dateFormat.format(currentDate));
        // Assume account is populated with user data after login validation
        if (acc != null) {
            // Lưu tài khoản vào session
            session.setAttribute("account", acc);
            // nếu "remember me" được chọn
            if (rememberMe != null && rememberMe.equals("on")) {
                // Tạo cookie cho tên đăng nhập và mật khẩu
                Cookie c_user = new Cookie("username", user_raw);
                Cookie c_pass = new Cookie("password", pass_raw);
                c_user.setMaxAge(7 * 24 * 60 * 60); // 7 ngày 
                c_pass.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(c_user);
                response.addCookie(c_pass);
            }

            // Kiểm tra vai trò của tài khoản
            if (acc.getRole() == 1) {
                //role = 1 => lấy danh sách theo PID
                List<Student> list = stu.getStudentByPid(acc.getPid().getPid());
                session.setAttribute("role", acc.getRole());
                session.setAttribute("pid", acc.getPid().getPid());
                response.sendRedirect("parent/timetable?stuid=" + list.get(0).getStuid());
            } else if (acc.getRole() == 2) {
                Lecturers lectu = lec.getLecturerByid(acc.getLid().getLid());
                session.setAttribute("role", acc.getRole());
                session.setAttribute("lid", lectu.getLid());
                response.sendRedirect("lecturers/timeTableLecturer?lid=" + lectu.getLid());
            } else if (acc.getRole() == 3) {

                response.sendRedirect("admin/adminhome");
            } else {
                // Kiểm tra nếu tài khoản không có bất kỳ vai trò nào được set
                request.setAttribute("err", "Bạn cần liên hệ admin để active tài khoản");
                request.getRequestDispatcher("FE_Parent/Login.jsp").forward(request, response);
            }

            request.setAttribute("err", "Login Success");
        } else {
            // Kiểm tra nếu không tìm thấy tài khoản
            request.setAttribute("err", "Password or Username is wrong. Please log in again");
            request.getRequestDispatcher("FE_Parent/Login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
