package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.SessionDBContext;
import Entity.Account;
import Entity.Session;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SessionController", urlPatterns = {"/admin/session"})
public class SessionController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String nameAct = request.getParameter("nameAct");
        SessionDBContext sessionDB = new SessionDBContext();
        
        if (nameAct != null && !nameAct.trim().isEmpty()) {
            List<Session> list1 = sessionDB.SearchByNameSession(nameAct);
            request.setAttribute("list1", list1);
        } else {
            List<Session> list = sessionDB.getAllSession();
            request.setAttribute("list", list);
        }

        request.getRequestDispatcher("/FE_Admin/Session.jsp").forward(request, response);
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
        return "Session management servlet";
    }
}

