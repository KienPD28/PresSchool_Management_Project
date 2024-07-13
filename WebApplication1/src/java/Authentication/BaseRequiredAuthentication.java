/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Authentication;

//import DAO.AccountDB;
import Entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public abstract class BaseRequiredAuthentication extends HttpServlet {
   
    
    private Account getAuthenticatedAccount(HttpServletRequest req) {
        Account account = (Account) req.getSession().getAttribute("account");
        if (account == null) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                String username = null;
                String password = null;
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals("username")) {
                        username = cooky.getValue();
                    }

                    if (cooky.getName().equals("password")) {
                        password = cooky.getValue();
                    }

                    if (username != null && password != null) {
                        break;
                    }
                }

                if (username != null && password != null) {
                    //AccountDB db = new AccountDB();
                    //account = db.getByUsernamePassword(username, password);
                    req.getSession().setAttribute("account", account);
                }
            }
        }
        return account;
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, Account account)
            throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getAuthenticatedAccount(req);
        if (account != null) {
            doPost(req, resp, account);
        } else {
            resp.getWriter().println("access denied!");
        }

    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, Account account)
            throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getAuthenticatedAccount(req);
        if (account != null) {
            doGet(req, resp, account);
        } else {
            resp.getWriter().println("access denied!");
        }
    }

}
