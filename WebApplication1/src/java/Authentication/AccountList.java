/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Authentication;

import Controller.Admin.*;
import DAO.AccountDBContext;
import Entity.Account;
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
@WebServlet(name = "AccountList", urlPatterns = {"/admin/account-list"})
public class AccountList extends BaseRBACController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account1)
            throws ServletException, IOException {
        //Tạo Session
        HttpSession session = request.getSession();
        AccountDBContext db = new AccountDBContext();

        String role = request.getParameter("role");
        String searchName = request.getParameter("searchName");
        String aid = request.getParameter("aid");
        String action = request.getParameter("action");

        ArrayList<Account> accountList;

        try {

            if (action != null && action.equals("delete") && aid != null) {
                // Nếu action là "delete" và có thông tin về aid, xóa tài khoản
                db.deleteAccount(aid);
            }

            if (role != null && !role.isEmpty()) {
                //Lấy Account theo Role
                accountList = db.getAllAccountByRole(role);
                request.setAttribute("role", Integer.parseInt(role));
            } else {
                //Lấy All ACCount
                accountList = db.getAllAccount();
            }

            //Tìm kiếm theo username, pname, lname
            if (searchName != null && !searchName.isEmpty()) {
                String searchNameLower = searchName.toLowerCase();
                accountList.removeIf(account -> {
                    //Convert username => toLowerCase , kh phan biet hoa thuong
                    String usernameLower = account.getUsername().toLowerCase();
                    String parentNameLower = "";
                    //nếu tên tồn tại => kết quả tìm kiếm
                    if (account.getPid() != null && account.getPid().getPname() != null) {
                        parentNameLower = account.getPid().getPname().toLowerCase();
                    }
                    String lecturerNameLower = "";
                    if (account.getLid() != null && account.getLid().getLname() != null) {
                        lecturerNameLower = account.getLid().getLname().toLowerCase();
                    }
                    // Trả về true nếu username, pname hoặc lname không có thông tin
                    return !usernameLower.contains(searchNameLower)
                            && !parentNameLower.contains(searchNameLower)
                            && !lecturerNameLower.contains(searchNameLower);
                });
            }
        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("Error/404.jsp");
            return;
        }

        session.setAttribute("accountList", accountList);
        request.getRequestDispatcher("/FE_Admin/AccountList.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account1)
            throws ServletException, IOException {
        processRequest(request, response, account1);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account1)
            throws ServletException, IOException {
        processRequest(request, response, account1);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
