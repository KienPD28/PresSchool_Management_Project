/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Lecturers;

import Authentication.BaseRBACController;
import DAO.Class_SessionDBContext;
import DAO.CuriculumDBContext;
import DAO.LecturersDBContext;
import DAO.SchedulesDBContext;
import DAO.SchoolYearDBContext;
import Entity.Account;
import Entity.ClassSession;
import Entity.Curiculum;
import Entity.Schedules;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "HistoryOfLecturer", urlPatterns = {"/lecturers/historyOfLecturer"})
public class HistoryOfLecturer extends BaseRBACController {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Curiculum> curi = new ArrayList<>();
        HttpSession sesion = request.getSession();
        String lid = request.getParameter("lid");
        String yidH = request.getParameter("yidHistoty");
        SchoolYearDBContext scy = new SchoolYearDBContext();
        String schedulesID = request.getParameter("schedulesID");
        CuriculumDBContext curiculum = new CuriculumDBContext();
        LecturersDBContext lectur = new LecturersDBContext();
        if (lid == null || lid.isEmpty()) {
            // Forward to an error page or display an error message on the same page
            request.setAttribute("error", "Missing or invalid 'lid' parameter.");
            request.getRequestDispatcher("/FE_Lecturers/HistoryOfLecturer.jsp").forward(request, response);
            return;
        }else{
             sesion.setAttribute("lect", lectur.getLecturerByid(Integer.parseInt(lid)));
        }

        Class_SessionDBContext ses = new Class_SessionDBContext();
        SchedulesDBContext sche = new SchedulesDBContext();

        List<ClassSession> listClass = ses.getClassSessionByLid(Integer.parseInt(lid));
        if (listClass == null || listClass.isEmpty()) {
            // Handle case where no class sessions are found
            request.setAttribute("error", "No class sessions found for the given 'lid'.");
            request.getRequestDispatcher("/FE_Lecturers/HistoryOfLecturer.jsp").forward(request, response);
            return;
        }

        List<Schedules> listSche = new ArrayList<>();

        try {
            if (schedulesID != null && !schedulesID.equals("0")) {
                Schedules sc = sche.getSchedulesBySchedulesID(Integer.parseInt(schedulesID));
                curi = curiculum.getCuriculumById(sc.getSdid().getSdid());
                request.setAttribute("schID", Integer.parseInt(schedulesID));
            }
            request.setAttribute("listYid", listClass);

            if (yidH != null && !yidH.isEmpty()) {
                request.setAttribute("yidChoose", scy.getSchoolYearById(Integer.parseInt(yidH)));
                listSche = sche.getAllSessionsDetailByYidAndLid(Integer.parseInt(yidH), Integer.parseInt(lid));
            } else {
                 request.setAttribute("yidChoose", listClass.get(0).getYid());
                listSche = sche.getAllSessionsDetailByYidAndLid(listClass.get(0).getYid().getYid(), Integer.parseInt(lid));
            }

            request.setAttribute("listSche", listSche);
        } catch (NumberFormatException e) {
            // Handle invalid number format
            request.setAttribute("error", "Invalid number format in parameters.");
            request.getRequestDispatcher("/FE_Lecturers/HistoryOfLecturer.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            // Log and handle other exceptions
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while processing the request.");
            request.getRequestDispatcher("/FE_Lecturers/HistoryOfLecturer.jsp").forward(request, response);
            return;
        }

        if (yidH != null) {
            request.setAttribute("yidH", yidH);
        }
         request.setAttribute("curiculum", curi);
        request.setAttribute("lid", lid);
       
        request.getRequestDispatcher("/FE_Lecturers/HistoryOfLecturer.jsp").forward(request, response);
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
