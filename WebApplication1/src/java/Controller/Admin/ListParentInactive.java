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
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author NGUYEN THI KHANH VI
 */
@WebServlet(name="ListParentInactive", urlPatterns={"/admin/parent-inactive"})
public class ListParentInactive extends BaseRBACController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
    throws ServletException, IOException {
       String indexPage = request.getParameter("index");
        
        ParentDBContext pdb = new ParentDBContext();
        
        
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        
        
            List<Parent> parentList = pdb.getAllParentInactive(index);
            int count = pdb.getTotalParentInactive();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            request.setAttribute("parentInactiveList", parentList); //Paginated List
            request.setAttribute("index", index);
            request.setAttribute("endPage", endPage);
        

       request.getRequestDispatcher("/FE_Admin/Inactive_Parent.jsp").forward(request, response);

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
    throws ServletException, IOException {
        processRequest(request, response, account);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
    throws ServletException, IOException {
        processRequest(request, response, account);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
