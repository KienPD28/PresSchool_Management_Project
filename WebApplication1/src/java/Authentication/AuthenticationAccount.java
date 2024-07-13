/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Authentication;

import DAO.AccountDBContext;
import Entity.Account;
import Entity.Lecturers;
import Entity.Parent;
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
@WebServlet(name = "AuthenticationAccount", urlPatterns = {"/authentication-account"})
public class AuthenticationAccount extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        AccountDBContext accdb = new AccountDBContext();

        //Lấy Danh Sách các tài khoản mới được tạo , phụ huynh và Giáo Viên
        ArrayList<Account> newAccountList = accdb.getOnlyNewAccount();
        ArrayList<Parent> availableParents = accdb.getPidNotExitsAccount();
        ArrayList<Lecturers> availableLecturers = accdb.getLidNotExitsAccount();

        //Lưu vào Session
        HttpSession session = request.getSession();
        session.setAttribute("newAccountList", newAccountList);
        session.setAttribute("availableParents", availableParents);
        session.setAttribute("availableLecturers", availableLecturers);

        request.getRequestDispatcher("FE_Admin/AuthenticationAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Account> newAccountList = (ArrayList<Account>) session.getAttribute("newAccountList");

        try {
            AccountDBContext accdb = new AccountDBContext();

           
            int accountId = Integer.parseInt(request.getParameter("accountId"));

            for (Account acc : newAccountList) {
                // Tìm tài khoản cần cập nhật dựa trên accountId
                if (acc.getAid() == accountId) {
                    String roleStr = request.getParameter("role-" + acc.getAid());
                    String pid = request.getParameter("pid-" + acc.getAid());
                    String lid = request.getParameter("lid-" + acc.getAid());

                    int role;
                    // Kiểm tra roleStr có null hay không
                    if (roleStr != null && !roleStr.isEmpty()) {
                        role = Integer.parseInt(roleStr);
                    } else {
                        role = 0;
                    }
                    acc.setRole(role);

                    //Set pid và lid theo từng role
                    
                    // nếu role 1 => set pid , lid is NULL
                    if (role == 1) {
                        Parent parent = new Parent();
                        parent.setPid(Integer.parseInt(pid));
                        acc.setPid(parent);
                        acc.setLid(null);
                        
                        // nếu role 2 => set lid , pid is NULL
                    } else if (role == 2) {
                        Lecturers lecturer = new Lecturers();
                        lecturer.setLid(Integer.parseInt(lid));
                        acc.setPid(null);
                        acc.setLid(lecturer);
                        
                        // nếu role 3 => set lid null , pid NULL
                    } else if (role == 3) {
                        acc.setPid(null);
                        acc.setLid(null);
                    }
                    
                    
                    boolean status = true;
                    // update
                    accdb.updateAuthenticationAccount3(acc,status);

                   
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("Error/404.jsp");
            return;
        }

        response.sendRedirect("account-list");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
