/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.CuriculumDBContext;
import Entity.Account;
import Entity.Curiculum;
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
 * @author admin
 */
@WebServlet(name = "AddCuriculum", urlPatterns = {"/admin/add-curiculum"})
public class AddCuriculum extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/FE_Admin/AddCuriculum.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
                    String nameAct = request.getParameter("nameAct");
        String sdid = request.getParameter("sdid");
        String isFix = request.getParameter("isFix");
        String timeSlot = request.getParameter("timeSlot");

       
        String[] times = timeSlot.split("-");
        String timeStart = times[0].trim();
        String timeEnd = times[1].trim();
        
        int sdid_raw = Integer.parseInt(sdid);

        CuriculumDBContext cur = new CuriculumDBContext();
        
        boolean conflict = cur.checkTimeSlotConflict(sdid, timeStart, timeEnd);

        if (conflict) {
            request.setAttribute("errorMessage", "Khung giờ học đã có.");
            request.getRequestDispatcher("/FE_Admin/AddCuriculum.jsp").forward(request, response);
        } else {
            cur.addCuriculum(nameAct, sdid, isFix, timeStart, timeEnd);
            request.setAttribute("success","Add succesfully");
            request.getRequestDispatcher("/FE_Admin/AddCuriculum.jsp").forward(request, response);
                    
            String referer = request.getHeader("referer");
            response.sendRedirect(referer);
        }
        }catch(Exception e){
            System.out.println(e);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
