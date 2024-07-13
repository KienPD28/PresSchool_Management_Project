/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.AccountDBContext;
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
@WebServlet(name = "AddNewParent", urlPatterns = {"/admin/add-parent"})
public class AddNewParent extends BaseRBACController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/FE_Admin/AddNewParent.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response, account);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        String pname = request.getParameter("pname");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String dob = request.getParameter("dob");
        String phoneNumber = request.getParameter("phoneNumber");
        String IDcard = request.getParameter("IDcard");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");

        ParentDBContext parentDB = new ParentDBContext();

        if (pname == null || pname.trim().isEmpty()) {
            request.setAttribute("Error", "Please enter your name.");
            processRequest(request, response, account);
            return;
        }
        // Validate Phone Number
        if (!phoneNumber.matches("\\d{10}")) {
            request.setAttribute("Error", "Phone number must be 10 digits.");
            processRequest(request, response, account);
            return;
        }
        if (parentDB.isPhoneNumberExists(phoneNumber)) {
            request.setAttribute("Error", "Phone number already exists.");
            processRequest(request, response, account);
            return;
        }
        if (address == null || address.trim().isEmpty()) {
            request.setAttribute("Error", "Please enter your address.");
            processRequest(request, response, account);
            return;
        }
        if (nickname == null || nickname.trim().isEmpty()) {
            request.setAttribute("Error", "Please enter your nickname.");
            return;
        }
        if (!IDcard.matches("\\d{12}")) {
            request.setAttribute("Error", "ID Card must be 12 digits.");
            processRequest(request, response, account);
            return;
        }

        if (parentDB.isIDCardExists(IDcard)) {
            request.setAttribute("Error", "ID Card already exists.");
            processRequest(request, response, account);
            return;
        }
        if (parentDB.isEmailExists(email)) {
            request.setAttribute("Error", "Email already exists.");
            processRequest(request, response, account);
            return;
        }

        Parent parent = new Parent();
        parent.setPname(pname);
        parent.setGender(gender);
        parent.setDob(dob);
        parent.setPhoneNumber(phoneNumber);
        parent.setIDcard(IDcard);
        parent.setAddress(address);
        parent.setEmail(email);
        parent.setNickname(nickname);

        parentDB.addParent(parent);

        response.sendRedirect("parent");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
