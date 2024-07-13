package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.ClassDBContext;
import DAO.LecturerClassSession;
import Entity.Account;
import Entity.ClassSession;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AddLecturers", urlPatterns = {"/admin/add-lecturer"})
public class AddLecturers extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ClassDBContext cl = new ClassDBContext();
        List<ClassSession> list = cl.getAllClass();
        request.setAttribute("listA", list);
        request.getRequestDispatcher("/FE_Admin/AddLecturer.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lname = request.getParameter("lname");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String IDcard = request.getParameter("IDcard");
        String classID = request.getParameter("classID");
//        
//        String lname = "Bùi Trung Hiếu";
//        String gender = "true";
//        String dob = "2003-01-12";
//        String address = "Hà Nội";
//        String phoneNumber = "0913394483";
//        String email = "soi@gmail.com";
//        String IDcard = "32894723";
//        String classID = "3";

        LecturerClassSession lcs = new LecturerClassSession();
        boolean isNumberPhone = lcs.isPhoneNumberExists(phoneNumber);
        boolean isIDCard = lcs.isIDCardExists(IDcard);
        boolean isEmail = lcs.isEmailExits(email);
        if (isNumberPhone) {
            request.setAttribute("errorMessage", "Số điện thoại đã tồn tại. Vui lòng nhập lại.");
              processRequest(request, response);
        }

        if (isIDCard) {
            request.setAttribute("errorMessage", "Số căn cước công dân đã tồn tại. Vui lòng nhập lại.");
              processRequest(request, response);
        }
        if(isEmail){
           request.setAttribute("errorMessage", "Email đã tồn tại. Vui lòng nhập lại.");
            processRequest(request, response);
        }
          if (phoneNumber == null || !phoneNumber.matches("0\\d{9}")) {
            request.setAttribute("errorMessage", "Khai Báo Không Hợp Lệ,Vui Lòng Thử Lại.");
            processRequest(request, response);
            return;
        }

        if (lname == null || !lname.matches("[\\p{L} ]+")) {
            request.setAttribute("errorMessage", "Khai Báo Không Hợp Lệ,Vui Lòng Thử Lại.");
            processRequest(request, response);
            return;
        }

        int total = lcs.getTotalLecturerInClass(classID);
        if (total < 5) {
            //   public void addLecturer(String lname, String gender, String dob, String phoneNumber, String IDcard, String address, String email, String classID)
//            lcs.addLecturer("Bùi Trung Hiếu", "true", "2003-01-12", "0913394483", "32894723", "Hà Nội", "soi@gmail.com","3");
            lcs.addLecturer(lname, gender, dob, phoneNumber, IDcard, address, email, classID);
            response.sendRedirect("lecturers");
        } else {
            request.setAttribute("errorMessage", "This class already has 4 lecturers.");
              processRequest(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

