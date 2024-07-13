/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Lecturers;

import Authentication.BaseRBACController;
import DAO.FeedbackDBContext;
import DAO.LecturersDBContext;
import Entity.Account;
import Entity.Lecturers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author hidung
 */
@WebServlet(name = "DeleteFeedback", urlPatterns = {"/lecturers/delete-feedback"})
public class DeleteFeedback extends BaseRBACController {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response, account);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        String lecturerId = request.getParameter("lid");
        String dateFeedback = request.getParameter("dateFeedback");
        int fid = Integer.parseInt(request.getParameter("fid"));
        FeedbackDBContext fbdb = new FeedbackDBContext();
        boolean isDeleted = fbdb.deleteFeedback(fid); // Redirect to an error page
        if (isDeleted) {
            response.sendRedirect(request.getContextPath() + "/lecturers/list-feedback?lecturerId=" + lecturerId + "&dateFeedback=" + dateFeedback);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
