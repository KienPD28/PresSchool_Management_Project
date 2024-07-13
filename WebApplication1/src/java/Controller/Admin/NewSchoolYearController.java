/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.SchoolYearDBContext;
import Entity.Account;
import Entity.SchoolYear;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "NewSchoolYearController", urlPatterns = {"/admin/newyear"})
public class NewSchoolYearController extends BaseRBACController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        //Lấy tham số dateStart, dateEnd từ request
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");

        try {
            if (dateStart != null && !dateStart.isEmpty() && dateEnd != null && !dateEnd.isEmpty()) {
                SchoolYearDBContext yearDB = new SchoolYearDBContext();

                // Kiểm tra năm học đã tồn tại
                if (yearDB.isSchoolYearExists(dateStart, dateEnd)) {
                    request.setAttribute("err", "Năm học này đã tồn tại! Vui lòng chọn năm học khác.");
                    request.getRequestDispatcher("/FE_Admin/NewSchoolYear.jsp").forward(request, response);
                } else {
                    // Kiểm tra năm học mới phải cách năm học cũ ít nhất 1 năm
                    SchoolYear latestYear = yearDB.getNewestSchoolYear();

                    if (latestYear != null) {
                        // Lấy năm bắt đầu và năm kết thúc của năm học mới nhất
                        int latestYearEnd = Integer.parseInt(latestYear.getDateEnd().substring(0, 4));
                        int newYearStart = Integer.parseInt(dateStart.substring(0, 4));
                        int newYearEnd = Integer.parseInt(dateEnd.substring(0, 4));

                        // Kiểm tra năm học mới phải kế tiếp năm học cũ
                        if (newYearStart == latestYearEnd && newYearEnd == newYearStart + 1) {
                            // Tạo năm học mới
                            yearDB.createNewSchoolYearForClassSession(dateStart, dateEnd);
                            response.sendRedirect("classController");
                        } else {
                            request.setAttribute("err", "Năm học mới phải kế tiếp năm học cũ (ví dụ: 2023-2024 tiếp đến 2024-2025).");
                            request.getRequestDispatcher("/FE_Admin/NewSchoolYear.jsp").forward(request, response);
                        }
                    } else {
                        // Nếu không có năm học nào trước đó, tạo năm học mới trực tiếp
                        yearDB.createNewSchoolYearForClassSession(dateStart, dateEnd);
                        response.sendRedirect("classController");
                    }
                }
            } else {
                request.setAttribute("err", "Ngày bắt đầu và kết thúc không được để trống!");
                request.getRequestDispatcher("/FE_Admin/NewSchoolYear.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Error/404.jsp");
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
        processRequest(request, response, account);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
