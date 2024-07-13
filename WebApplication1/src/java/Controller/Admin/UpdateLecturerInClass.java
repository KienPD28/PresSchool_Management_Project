package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.LecturerClassSession;
import Entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateLecturerInClass", urlPatterns = {"/admin/update-lecturer-class"})
public class UpdateLecturerInClass extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lid = request.getParameter("lid");
        String classid = request.getParameter("classid");
        LecturerClassSession lcs = new LecturerClassSession();
        int total = lcs.getTotalLecturerInClass(classid);
        if (total >= 5) {
            request.setAttribute("errorMessage", "Khai Báo Không Hợp Lệ,Vui Lòng Thử Lại.");
            request.setAttribute("lid", lid);
            request.getRequestDispatcher("update-lecturers?lid=" + lid).forward(request, response);
        } else {

            lcs.updateClass(lid, classid);
            response.sendRedirect("update-lecturers?lid=" + lid + "&successMessage=Lecturer updated successfully.");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
