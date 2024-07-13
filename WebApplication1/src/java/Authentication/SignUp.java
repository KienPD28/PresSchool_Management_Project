/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Authentication;

import DAO.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
@WebServlet(name = "SignUp", urlPatterns = {"/sign-up"})
public class SignUp extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("user");
        String password = request.getParameter("password");
        String confirm_pass = request.getParameter("confirm");
        
        AccountDBContext accdb = new AccountDBContext();

        try {

            // Kiểm tra nếu password và confirm_pass không khớp
           if (password.equals(confirm_pass)) {
            if (accdb.isUsernameTaken(username)) {
                session.setAttribute("errorMessage", "Username already taken");
                request.getRequestDispatcher("FE_Parent/Login.jsp").forward(request, response);
            } else {
                accdb.createNewAccount(username, password);
                session.setAttribute("successMessage", "Account created successfully");
                request.getRequestDispatcher("FE_Parent/Login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Passwords do not match");
            request.getRequestDispatcher("FE_Parent/Login.jsp").forward(request, response);
        }

        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("Error/404.jsp");
            return;
        }
        
        request.getRequestDispatcher("FE_Parent/Login.jsp").forward(request, response);

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
    }// </editor-fold>

}
