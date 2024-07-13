package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.LecturerClassSession;
import DAO.LecturersDBContext;
import DAO.SchoolYearDBContext;
import Entity.Account;
import Entity.Lecturers;
import Entity.Lecturers_Class_Session;
import Entity.SchoolYear;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LecturersController", urlPatterns = {"/admin/lecturers"})
public class LecturersController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        LecturerClassSession lcs = new LecturerClassSession();
        List<Lecturers_Class_Session> list = lcs.getAllLecturerInNewSchoolYear();
        SchoolYear sc = lcs.getNewSchoolYear();
        request.setAttribute("sc", sc);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/FE_Admin/Lecturers.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
