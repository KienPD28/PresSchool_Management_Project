/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.LecturerClassSession;
import DAO.LecturersDBContext;
import Entity.Account;
import Entity.Lecturers;
import Entity.Lecturers_Class_Session;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name="SearchLecturersController", urlPatterns={"/admin/search-lecturers"})
public class SearchLecturersController extends BaseRBACController {
   
    
   protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    
    // Lấy giá trị từ trường tìm kiếm
    String searchInput = request.getParameter("searchInput");
    
    // Gọi phương thức tìm kiếm theo id
    LecturersDBContext lcs = new LecturersDBContext();
    List<Lecturers> searchResultsById = lcs.getLecturerByID(searchInput);
    
    // Gọi phương thức tìm kiếm theo tên
    List<Lecturers> searchResultsByName = lcs.getLecturerByName(searchInput);
    
    // Kết hợp kết quả từ cả hai phương thức tìm kiếm
    List<Lecturers> combinedResults = new ArrayList<>();
    combinedResults.addAll(searchResultsById);
    combinedResults.addAll(searchResultsByName);
    
    // Chuyển kết quả đến trang JSP
    request.setAttribute("searchResults", combinedResults);
    request.getRequestDispatcher("/FE_Admin/CRUD_Lecturers.jsp").forward(request, response);
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
