package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.ClassDBContext;
import DAO.LecturerClassSession;
import DAO.LecturersDBContext;
import Entity.Account;
import Entity.Lecturers;
import Entity.Lecturers_Class_Session;
import Entity.Class;
import Entity.ClassSession;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet(name = "UpdateLecturers", urlPatterns = {"/admin/update-lecturers"})
public class UpdateLecturers extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        LecturerClassSession lcs = new LecturerClassSession();
        String id = request.getParameter("lid");
        Lecturers_Class_Session lec = lcs.getLecturerByid(id);
        request.setAttribute("lec", lec);

        Lecturers_Class_Session lec1 = lcs.getLecturerByidClass(id);
        request.setAttribute("lec1", lec1);

        ClassDBContext cl = new ClassDBContext();
        List<ClassSession> list = cl.getAllClass();
        request.setAttribute("list1", list);

        // Check for success message
        if (request.getParameter("successMessage") != null) {
            request.setAttribute("successMessage", request.getParameter("successMessage"));
        }
        String errorMessage3 = (String) request.getAttribute("errorMessage3");
        if (errorMessage3 != null) {
            request.setAttribute("errorMessage3", errorMessage3);
            request.getRequestDispatcher("/FE_Admin/UpdateLecturer.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("/FE_Admin/UpdateLecturer.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int lid_raw = 0;
        String lid = request.getParameter("lid");
        String lname = request.getParameter("lname");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String IDcard = request.getParameter("IDcard");

        if (phoneNumber == null || !phoneNumber.matches("0\\d{9}")) {
            request.setAttribute("errorMessage", "Khai Báo Không Hợp Lệ,Vui Lòng Thử Lại.");
            request.setAttribute("lid", lid);
            processRequest(request, response);
            return;
        }

        if (lname == null || !lname.matches("[\\p{L} ]+")) {
            request.setAttribute("errorMessage", "Khai Báo Không Hợp Lệ,Vui Lòng Thử Lại.");
            request.setAttribute("lid", lid);
            processRequest(request, response);
            return;
        }
        try {
            lid_raw = Integer.parseInt(lid);
        } catch (Exception e) {
            System.out.println(e);
        }

        Lecturers lec = new Lecturers();
        lec.setLid(lid_raw);
        lec.setLname(lname);
        lec.setGender(gender);
        lec.setDob(dob);
        lec.setAddress(address);
        lec.setPhoneNumber(phoneNumber);
        lec.setEmail(email);
        lec.setIDcard(IDcard);

        LecturersDBContext ldb = new LecturersDBContext();
        ldb.updateLecturers(lec);

        response.sendRedirect("update-lecturers?lid=" + lid + "&successMessage=Lecturer updated successfully.");
    }

    @Override
    public String getServletInfo() {
        return "Servlet for updating lecturers";
    }
}
