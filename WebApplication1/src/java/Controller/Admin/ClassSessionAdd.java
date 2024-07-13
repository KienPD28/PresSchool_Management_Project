package Controller.Admin;

import DAO.ClassDBContext;
import DAO.RoomDBContext;
import DAO.SchoolYearDBContext;
import DAO.SessionDBContext;
import Entity.Room;
import Entity.SchoolYear;
import Entity.Class;
import Entity.ClassSession;
import Entity.Session;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "ClassSessionAdd", urlPatterns = {"/admin/classSession-add"})
public class ClassSessionAdd extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SchoolYearDBContext db = new SchoolYearDBContext();
        try {
            int selectedYid = -1;
            // check "selectedYid" có tồn tại và không rỗng
            if (request.getParameter("selectedYid") != null && !request.getParameter("selectedYid").isEmpty()) {
                // Nếu có, gán giá trị cho biến selectedYid
                selectedYid = Integer.parseInt(request.getParameter("selectedYid"));
            } else {
                //Không => mặc định lấy ra năm học mới nhất
                SchoolYear newestYear = db.getNewestSchoolYear();
                if (newestYear != null) {
                    selectedYid = newestYear.getYid();
                }
            }

            // Lấy danh sách các năm học
            ArrayList<SchoolYear> years = db.getAllSchoolYear();
            ArrayList<Class> classes = new ArrayList<>();
            ArrayList<Room> rooms = new ArrayList<>();
            ArrayList<Session> sessions = new ArrayList<>();
            ArrayList<ClassSession> classSessions = new ArrayList<>();

            //Nếu có yid
            if (selectedYid != -1) {
                //lấy thông tin của ClassSession
                classes = db.getAllClass();
                rooms = db.getAllRoom();
                sessions = db.getAllSession();
                classSessions = db.getClassSessionByYid2(selectedYid);
            }

            request.setAttribute("years", years);
            request.setAttribute("classes", classes);
            request.setAttribute("rooms", rooms);
            request.setAttribute("sessions", sessions);
            request.setAttribute("selectedYid", selectedYid);
            request.setAttribute("classSessions", classSessions);

            request.getRequestDispatcher("/FE_Admin/AddClassSession.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println(e);
            request.getRequestDispatcher("/Error/404.jsp").forward(request, response);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SchoolYearDBContext db = new SchoolYearDBContext();
        ClassDBContext classDB = new ClassDBContext();
        SessionDBContext sessionDB = new SessionDBContext();
        RoomDBContext roomDB = new RoomDBContext();

        try {
            int selectedYid = Integer.parseInt(request.getParameter("selectedYid"));
            String action = request.getParameter("action");
            String[] classIDsStr = request.getParameterValues("classID");
            String[] ridsStr = request.getParameterValues("rid");
            String[] sidsStr = request.getParameterValues("sid");
            String[] statusStr = request.getParameterValues("status");

            // Lấy ra năm học mới nhất
            SchoolYear newestYear = db.getNewestSchoolYear();

            // nếu là năm học cũ => chặn update
            if (newestYear == null || newestYear.getYid() != selectedYid) {
                // thông báo lỗi
                request.setAttribute("oldYearUpdateAttempt", true);
                processRequest(request, response);
                return;
            }
            if (classIDsStr != null && ridsStr != null && sidsStr != null && statusStr != null) {
                // Duyệt qua các giá trị của các tham số
                for (int i = 0; i < classIDsStr.length; i++) {
                    try {
                        int classID = Integer.parseInt(classIDsStr[i]);
                        Integer rid = (ridsStr[i] != null && !ridsStr[i].isEmpty()) ? Integer.parseInt(ridsStr[i]) : null;
                        Integer sid = (sidsStr[i] != null && !sidsStr[i].isEmpty()) ? Integer.parseInt(sidsStr[i]) : null;
                        boolean status = Boolean.parseBoolean(statusStr[i]);

                        //Tạo ClassSession và SetValue
                        ClassSession cls = new ClassSession();
                        cls.setClassID(classDB.getClassById(classID));
                        cls.setYid(db.getSchoolYearById(selectedYid));
                        cls.setSid((sid != null) ? sessionDB.getSessionById(sid) : null);
                        cls.setRid((rid != null) ? roomDB.getRoomByRid(rid) : null);
                        cls.setStatus(status);

                        //lớp đó đã có học sinh không thể thay đổi status
                        if (db.getStudentCountByClassSession2(classID)) {
                            //continue;
                            cls.setStatus(true);
                        }

                        //Action == Update
                        if ("update".equals(action)) {

                            //update khi chưa có học sinh or có thể update khi có học sinh (only room and session)
                            if (!db.getStudentCountByClassSession2(classID) || rid != null || sid != null) {
                                db.updateClassSession(cls);
                            }

                            //Action == Save
                        } else if ("save".equals(action)) {
                            //Thêm lớp vào năm học mới
                            db.insertClassSession(cls);
                        }

                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                }
            }

            response.sendRedirect("classSession-add?selectedYid=" + selectedYid);

        } catch (Exception e) {
            System.out.println(e);
            request.getRequestDispatcher("/Error/404.jsp").forward(request, response);
            return;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
