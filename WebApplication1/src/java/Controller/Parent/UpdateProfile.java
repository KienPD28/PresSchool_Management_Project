package Controller.Parent;

import Authentication.BaseRBACController;
import DAO.ParentDBContext;
import Entity.Account;
import Entity.Parent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "UpdateProfile", urlPatterns = {"/parent/update-profile"})
public class UpdateProfile extends BaseRBACController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc != null) {
            ParentDBContext parentDB = new ParentDBContext();
            Parent pa = parentDB.getParentByid(acc.getPid().getPid());
            request.setAttribute("pa", pa);

            // Forward to the JSP with the parent data
            request.getRequestDispatcher("/FE_Parent/UpdateProfileParent.jsp").forward(request, response);
        } else {
            // Redirect to login if the user is not authenticated
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response, account);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc != null) {
            // Retrieve parameters from the request
            int pid = acc.getPid().getPid();
            String pname = request.getParameter("pname");
            boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
            String dob = request.getParameter("dob");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String IDcard = request.getParameter("IDcard");
            String nickname = request.getParameter("nickname");

            // Create a new Parent object and set its properties
            Parent parent = new Parent();
            parent.setPid(pid);
            parent.setPname(pname);
            parent.setGender(gender);
            parent.setDob(dob);
            parent.setAddress(address);
            parent.setPhoneNumber(phoneNumber);
            parent.setEmail(email);
            parent.setIDcard(IDcard);
            parent.setNickname(nickname);

            // Update the parent information in the database
            ParentDBContext parentDB = new ParentDBContext();
            parentDB.updateParent(parent);

            // Redirect to the parent profile page after update
            response.sendRedirect("parent-profile");
        } else {
            // Redirect to the login page if the user is not authenticated
            response.sendRedirect("login");
        }
    }

    @Override
    public String getServletInfo() {
        return "UpdateProfile Servlet";
    }
}
