/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Authentication;

import DAO.AccountDBContext;
import Entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author DELL
 */
public abstract class BaseRBACController extends HttpServlet {

    private static final int SESSION_TIMEOUT = 3 * 60 * 60;

    //xác thực tài khoản dựa theo phiên và cookie
    protected Account getAuthenticatedAccount(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(SESSION_TIMEOUT);
        Account account = (Account) session.getAttribute("account");
        
        // Nếu tài khoản không có trong phiên, kiểm tra cookie
        if (account == null) {
            
            Cookie[] cookies = req.getCookies();
            
            // Nếu có cookie, duyệt qua các cookie để tìm username và password
            if (cookies != null) {
                String username = null;
                String password = null;
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals("username")) {// Lấy giá trị của cookie
                        username = cooky.getValue();
                    }

                    if (cooky.getName().equals("password")) {
                        password = cooky.getValue();
                    }

                    if (username != null && password != null) {
                        break;
                    }//tìm thấy username và password, break
                }

                // Xác thực tài khoản kiểm tra username và password từ cookie
                if (username != null && password != null) {
                    AccountDBContext db = new AccountDBContext();
                    //account = db.getByUsernamePassword(username, password);
                    session.setAttribute("account", account);
                }
            }
        }
        // return về tài khoản xác thực
        return account;
    }

    // Kiểm tra quyền truy cập dựa trên vai trò của tài khoản và URL
    private boolean checkAccess(HttpServletRequest request, Account account) {
        String url = request.getServletPath();
        int role = account.getRole();

        // URL bắt đầu bằng "/admin"
        if (url.startsWith("/admin") && role != 3) {
            return false;
        } else if (url.startsWith("/lecturers") && role != 2) {// URL bắt đầu bằng "/lecturers"
            return false;
        } else if (url.startsWith("/parent") && role != 1) {// URL bắt đầu bằng "/parent"
            return false; // return false => không có quyền truy cập
        }

        // return true => có quyền truy cập
        return true;

    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, Account account)
            throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getAuthenticatedAccount(req);
        if (account != null) {// tài khoản đã được xác thực
            if (checkAccess(req, account)) {// check quyền truy cập
                doPost(req, resp, account);
            } else {
                req.getRequestDispatcher("/Error/accessDenied.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/Error/accessDenied.jsp").forward(req, resp);

        }
    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, Account account)
            throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getAuthenticatedAccount(req);
        if (account != null) {
            if (checkAccess(req, account)) {
                doGet(req, resp, account);
            } else {
                req.getRequestDispatcher("/Error/accessDenied.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/Error/accessDenied.jsp").forward(req, resp);
        }
    }
    
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Lấy phiên hiện tại nếu tồn tại
        if (session != null) {
            session.invalidate(); // Kết thúc phiên
        }
        resp.sendRedirect(req.getContextPath() + "/login"); // Chuyển hướng đến trang đăng nhập
    }

}
