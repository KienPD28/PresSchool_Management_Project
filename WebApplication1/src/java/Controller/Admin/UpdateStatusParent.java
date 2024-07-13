/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.ParentDBContext;
import Entity.Account;
import Entity.Parent;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author NGUYEN THI KHANH VI
 */
@WebServlet(name = "UpdateStatusParent", urlPatterns = {"/admin/update-tatus-parent"})
public class UpdateStatusParent extends BaseRBACController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {

        request.getRequestDispatcher("/FE_Admin/Parent_Management.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response, account);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        String Pid = request.getParameter("pid");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        try {
            int pid = Integer.parseInt(Pid);

            Parent parent = new Parent();
            parent.setPid(pid);
            parent.setStatus(status);

            ParentDBContext parentDB = new ParentDBContext();
            parentDB.updateParentStatus(pid, status);

            response.sendRedirect("parent");
        } catch (NumberFormatException e) {
        }

        processRequest(request, response, account);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
