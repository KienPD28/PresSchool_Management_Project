package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.AgeDBContext;
import DAO.SessionDBContext;
import Entity.Account;
import Entity.AgeCategory;
import Entity.Session;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "UpdateSession", urlPatterns = {"/admin/update-session"})
public class UpdateSession extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String sid = request.getParameter("sid");
        int sid_raw = Integer.parseInt(sid);
        SessionDBContext se = new SessionDBContext();
        Session s = se.getSessionById(sid_raw);
        AgeDBContext age = new AgeDBContext();
        List<AgeCategory> list = age.getAge();
        request.setAttribute("s", s);
        request.setAttribute("list",list );
        request.getRequestDispatcher("/FE_Admin/UpdateSession.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String sname = request.getParameter("sname");
        String totalSession = request.getParameter("totalSession");
        String ageid = request.getParameter("ageid");

        SessionDBContext se = new SessionDBContext();
        Session s = new Session();
        s.setSid(Integer.parseInt(sid));
        s.setSname(sname);
        s.setTotalSession(Integer.parseInt(totalSession));
        
        AgeCategory ageCategory = new AgeCategory();
        ageCategory.setAgeid(Integer.parseInt(ageid));
        s.setAge(ageCategory);

        se.updateSession(sname, totalSession, ageid, sid); // Ensure this method updates the session in the database

        response.sendRedirect("session"); // Redirect to the session list page after update
    }

    @Override
    public String getServletInfo() {
        return "UpdateSession Servlet";
    }
}
