/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Lecturers;

import Authentication.BaseRBACController;
import DAO.Class_SessionDBContext;
import DAO.CuriculumDBContext;
import DAO.SchedulesDBContext;
import DAO.SessionDetailDBContext;
import Entity.Account;
import Entity.Curiculum;
import Entity.Schedules;
import Entity.SessionDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddSchedules", urlPatterns = {"/lecturers/addSchedules"})
public class AddSchedules extends BaseRBACController {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddSchedules</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSchedules at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        String csid = request.getParameter("csid");
        String sdid = request.getParameter("sdid");
        String date = request.getParameter("date");
        String idToDelete = request.getParameter("idToDelete");
        Class_SessionDBContext classS = new Class_SessionDBContext();
        SchedulesDBContext sb = new SchedulesDBContext();
        SessionDetailDBContext sddb = new SessionDetailDBContext();
        CuriculumDBContext curdb = new CuriculumDBContext();
        String sm = request.getParameter("sm");
        try {

            if (idToDelete != null || !idToDelete.isEmpty()) {
                sb.delete(Integer.parseInt(idToDelete));
                request.setAttribute("Delete", "Delete success");
            }
        } catch (Exception e) {
        }

        Date today = new Date();
        HttpSession session = request.getSession();
        SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");

        // Get tomorrow's date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        if (sm != null) {
            if (sb.getSchedulesByCsIdAndDate(Integer.parseInt(csid), dateF.format(today)) != null && sm.equals("Update")) {
                sb.update(dateF.format(today), Integer.parseInt(sdid), Integer.parseInt(csid));
                request.setAttribute("mess", "Cập nhật buổi học thành công");
            } else {
                if ((!sdid.isEmpty() && !csid.isEmpty()) || (sdid != null && csid != null && sm.equals("Add"))) {
                    sb.insert(Integer.parseInt(sdid), dateF.format(today), Integer.parseInt(csid));
                    request.setAttribute("mess", "Thêm buổi học thành công.");
                }
            }
        }
        if (csid != null && !csid.isEmpty() && !csid.equals("0")) {
            
            // Lấy ra những buổi đã học và chưa học của 1 lớp trong năm học này
            List<Schedules> listSchedulesUnlearn = sb.getAllUnclassifiedSessionsDetail(Integer.parseInt(csid), classS.getSidByCsid(Integer.parseInt(csid)).getSid().getSid());
            List<Schedules> listSchedulesLearn = sb.getSchedulesByCsid(Integer.parseInt(csid));
            // Check null và gửi data sang jsp
            if (listSchedulesUnlearn != null) {
                List<Curiculum> listCuri = new ArrayList<>();

                if (sdid != null) {
                    listCuri = curdb.getCuriculumBySessionId(Integer.parseInt(sdid));
                } else if (!listSchedulesUnlearn.isEmpty()) {
                    listCuri = curdb.getCuriculumBySessionId(listSchedulesUnlearn.get(0).getSdid().getSdid());
                } else {
                    request.setAttribute("mess", "Chương trình dạy học của lớp trong năm học này đã hết!");
                }

                request.setAttribute("listCuri", listCuri);

                session.setAttribute("listSchedulesUnlearn", listSchedulesUnlearn);
                session.setAttribute("listSchedulesLearn", listSchedulesLearn);

                if (listSchedulesLearn != null && !listSchedulesLearn.isEmpty()) {
                    session.setAttribute("className", listSchedulesLearn.get(0).getCsid().getClassID().getClname());
                }
            }

            session.setAttribute("year", classS.getClassSessionById(Integer.parseInt(csid)).getYid());
            session.setAttribute("sche", sb.getSchedulesByCsIdAndDate(Integer.parseInt(csid), dateF.format(today)));
            session.setAttribute("date", dateF.format(today)); // Set tomorrow's date
        }

        session.setAttribute("csid", csid);
        session.setAttribute("sdid", sdid);
        request.getRequestDispatcher("/FE_Lecturers/AddSchedules.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response, account);
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
