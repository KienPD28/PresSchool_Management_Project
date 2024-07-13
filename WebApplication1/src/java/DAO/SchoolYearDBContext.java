/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.AgeCategory;
import Entity.Class;
import Entity.ClassSession;
import Entity.Lecturers_Class_Session;
import Entity.Room;
import Entity.SchoolYear;
import Entity.Session;
import Entity.Student;
import Entity.StudentClassSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class SchoolYearDBContext extends DBContext {

    SchoolYear s = new SchoolYear();

    public static void main(String[] args) {
        SchoolYearDBContext ss = new SchoolYearDBContext();
        SchoolYear list = ss.getSchoolYearByDateNow("2024-07-11");
        System.out.println(list.getYid());

    }

    public SchoolYear getSchoolYearById(int id) {
        SchoolYear school = new SchoolYear();
        try {
            String sql = "SELECT [yid]\n"
                    + "      ,[dateStart]\n"
                    + "      ,[dateEnd]\n"
                    + "  FROM [SchoolManagement].[dbo].[SchoolYear] Where yid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                FoodDBContext food = new FoodDBContext();
                MealTimeDBContext meal = new MealTimeDBContext();
                AgeDBContext age = new AgeDBContext();
                school.setYid(rs.getInt("yid"));
                school.setDateStart(s.formatDate(rs.getString("dateStart")));
                school.setDateEnd(s.formatDate(rs.getString("dateEnd")));
                return school;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public SchoolYear getSchoolYearByDateNow(String date) {
        SchoolYear school = new SchoolYear();
        try {
            String sql = " SELECT yid, dateStart, dateEnd FROM [SchoolManagement].[dbo].[SchoolYear] Where dateStart <= ? and dateEnd >= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, date);
            stm.setString(2, date);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                FoodDBContext food = new FoodDBContext();
                MealTimeDBContext meal = new MealTimeDBContext();
                AgeDBContext age = new AgeDBContext();
                school.setYid(rs.getInt("yid"));
                school.setDateStart(s.formatDate(rs.getString("dateStart")));
                school.setDateEnd(s.formatDate(rs.getString("dateEnd")));
                return school;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //List  Function getAll Class in SchoolYear
    public ArrayList<SchoolYear> getAllSchoolYear() {
        ArrayList<SchoolYear> list = new ArrayList<>();
        try {
            String sql = "SELECT [yid]\n"
                    + "      ,[dateStart]\n"
                    + "      ,[dateEnd]\n"
                    + "  FROM [SchoolManagement].[dbo].[SchoolYear]";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SchoolYear year = new SchoolYear();
                year.setYid(rs.getInt("yid"));
                year.setDateStart(rs.getString("dateStart"));
                year.setDateEnd(rs.getString("dateEnd"));
                list.add(year);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    public ArrayList<Session> getAllSession() {
        ArrayList<Session> list = new ArrayList<>();
        try {
            String sql = "SELECT [sid], [sname], [totalSession], [ageid],[status] FROM [SchoolManagement].[dbo].[Session] where status is not null";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                AgeDBContext ageDB = new AgeDBContext();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setTotalSession(rs.getInt("totalSession"));
                s.setAge(ageDB.getAgeById(rs.getInt("ageid")));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Room> getAllRoom() {
        ArrayList<Room> list = new ArrayList<>();
        try {
            String sql = "SELECT [rid], [rname] FROM [SchoolManagement].[dbo].[Room]";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setRid(rs.getInt("rid"));
                room.setRname(rs.getString("rname"));
                list.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Class> getAllClass() {
        ArrayList<Class> list = new ArrayList<>();
        try {
            String sql = "SELECT [classID], [clname] FROM [SchoolManagement].[dbo].[Class]";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Class cls = new Class();
                cls.setClassid(rs.getInt("classID"));
                cls.setClname(rs.getString("clname"));
                list.add(cls);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public SchoolYear getSchoolYearById2(int id) {
        SchoolYear school = new SchoolYear();
        try {
            String sql = "SELECT [yid]\n"
                    + "      ,[dateStart]\n"
                    + "      ,[dateEnd]\n"
                    + "  FROM [SchoolManagement].[dbo].[SchoolYear] Where yid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                FoodDBContext food = new FoodDBContext();
                MealTimeDBContext meal = new MealTimeDBContext();
                AgeDBContext age = new AgeDBContext();
                school.setYid(rs.getInt("yid"));
                school.setDateStart(rs.getString("dateStart"));
                school.setDateEnd(rs.getString("dateEnd"));
                return school;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //List  Function getAll Class in SchoolYear
    public ArrayList<SchoolYear> getAllSchoolYear2() {
        ArrayList<SchoolYear> list = new ArrayList<>();
        try {
            String sql = "SELECT [yid]\n"
                    + "      ,[dateStart]\n"
                    + "      ,[dateEnd]\n"
                    + "  FROM [SchoolManagement].[dbo].[SchoolYear]";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SchoolYear year = new SchoolYear();
                year.setYid(rs.getInt("yid"));
                year.setDateStart(rs.getString("dateStart"));
                year.setDateEnd(rs.getString("dateEnd"));
                list.add(year);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    public ArrayList<SchoolYear> getSchoolYearById(String id) {
        ArrayList<SchoolYear> list = new ArrayList<>();
        try {
            String sql = "SELECT [yid]\n"
                    + "      ,[dateStart]\n"
                    + "      ,[dateEnd]\n"
                    + "  FROM [SchoolManagement].[dbo].[SchoolYear]\n"
                    + "  WHERE yid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SchoolYear year = new SchoolYear();
                year.setYid(rs.getInt("yid"));
                year.setDateStart(rs.getString("dateStart"));
                year.setDateEnd(rs.getString("dateEnd"));
                list.add(year);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public ArrayList<ClassSession> getClassSessionByYid(String id) {
        ArrayList<ClassSession> list = new ArrayList<>();
        try {
            String sql = "SELECT [csid]\n"
                    + "      ,[classID]\n"
                    + "      ,[yid]\n"
                    + "      ,[sid]\n"
                    + "      ,[rid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Class_Session]\n"
                    + "  WHERE yid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClassSession cls = new ClassSession();
                ClassDBContext cldb = new ClassDBContext();
                RoomDBContext rdb = new RoomDBContext();
                SessionDBContext sedb = new SessionDBContext();
                SchoolYearDBContext yearDB = new SchoolYearDBContext();

                cls.setCsid(rs.getInt("csid"));
                cls.setClassID(cldb.getClassById(rs.getInt("classID")));
                cls.setRid(rdb.getRoomByRid(rs.getInt("rid")));
                cls.setSid(sedb.getSessionById(rs.getInt("sid")));
                cls.setYid(yearDB.getSchoolYearById(rs.getInt("yid")));

                list.add(cls);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;

    }

    //Lấy ra tất cả danh sách lớp học ở những năm cũ
    public ArrayList<ClassSession> getAllClassesFromPreviousYears(String id) {
        ArrayList<ClassSession> list = new ArrayList<>();
        try {
            String sql = "SELECT [csid]\n"
                    + "      ,[classID]\n"
                    + "      ,[yid]\n"
                    + "      ,[sid]\n"
                    + "      ,[rid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Class_Session]\n"
                    + "  WHERE yid < ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClassSession cls = new ClassSession();
                ClassDBContext cldb = new ClassDBContext();
                RoomDBContext rdb = new RoomDBContext();
                SessionDBContext sedb = new SessionDBContext();
                SchoolYearDBContext yearDB = new SchoolYearDBContext();

                cls.setCsid(rs.getInt("csid"));
                cls.setClassID(cldb.getClassById(rs.getInt("classID")));
                cls.setRid(rdb.getRoomByRid(rs.getInt("rid")));
                cls.setSid(sedb.getSessionById(rs.getInt("sid")));
                cls.setYid(yearDB.getSchoolYearById(rs.getInt("yid")));

                list.add(cls);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;

    }

    public ArrayList<ClassSession> getClassSessionById(String id) {
        ArrayList<ClassSession> list = new ArrayList<>();
        try {
            String sql = "SELECT [csid]\n"
                    + "      ,[classID]\n"
                    + "      ,[yid]\n"
                    + "      ,[sid]\n"
                    + "      ,[rid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Class_Session]\n"
                    + "  WHERE csid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClassSession cls = new ClassSession();
                ClassDBContext cldb = new ClassDBContext();
                RoomDBContext rdb = new RoomDBContext();
                SessionDBContext sedb = new SessionDBContext();
                SchoolYearDBContext yearDB = new SchoolYearDBContext();

                cls.setCsid(rs.getInt("csid"));
                cls.setClassID(cldb.getClassById(rs.getInt("classID")));
                cls.setRid(rdb.getRoomByRid(rs.getInt("rid")));
                cls.setSid(sedb.getSessionById(rs.getInt("sid")));
                cls.setYid(yearDB.getSchoolYearById(rs.getInt("yid")));

                list.add(cls);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;

    }

    //Lấy ra danh sách học sinh ở năm học cũ khi chọn năm học mới
    public ArrayList<StudentClassSession> getStudentsFromPreviousYears(String yid) {
        ArrayList<StudentClassSession> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "scs.scid, \n"
                    + "scs.stuid, \n"
                    + "s.sname,\n"
                    + "s.dob,\n"
                    + "s.gender,\n"
                    + "cs.csid, \n"
                    + "cs.classID, \n"
                    + "cl.clname,\n"
                    + "cs.sid, \n"
                    + "cs.yid,\n"
                    + "se.ageid,\n"
                    + "y.dateStart,\n"
                    + "y.dateEnd\n"
                    + "FROM Student_Class_Session scs\n"
                    + "join Student s on scs.stuid = s.stuid\n"
                    + "join Class_Session cs on scs.csid = cs.csid\n"
                    + "join Session se on cs.sid = se.sid\n"
                    + "join SchoolYear y on cs.yid = y.yid\n"
                    + "join Class cl on cs.classID = cl.classID\n"
                    + "WHERE cs.yid = (SELECT MAX(yid) FROM SchoolYear WHERE yid < ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, yid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentClassSession studentClassSession = new StudentClassSession();
                // Set StudentClassSession ID
                studentClassSession.setScid(rs.getInt("scid"));

                // Set Student details
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));
                student.setDob(rs.getString("dob"));
                student.setGender(rs.getBoolean("gender"));
                studentClassSession.setStuid(student);

                // Set ClassSession details
                Class cl = new Class();
                ClassSession classSession = new ClassSession();
                classSession.setCsid(rs.getInt("csid"));
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));
                classSession.setClassID(cl);

                Session session = new Session();
                session.setSid(rs.getInt("sid"));
                classSession.setSid(session);
                
                AgeCategory age = new AgeCategory();
                age.setAgeid(rs.getInt("ageid"));
                session.setAge(age);
                classSession.setSid(session);

                SchoolYear schoolYear = new SchoolYear();
                schoolYear.setYid(rs.getInt("yid"));
                schoolYear.setDateStart(rs.getString("dateStart"));
                schoolYear.setDateEnd(rs.getString("dateEnd"));
                classSession.setYid(schoolYear);

                studentClassSession.setCsid(classSession);

                // Add the studentClassSession object to the list
                list.add(studentClassSession);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


    //Add học sinh vào lớp mới trong bảng Student_Class_Session
    public void addStudentToClass(StudentClassSession student) {
        try {
            String sql = "INSERT INTO [dbo].[Student_Class_Session]\n"
                    + "           ([stuid]\n"
                    + "           ,[csid])\n"
                    + "     VALUES\n"
                    + "           (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, student.getStuid().getStuid());
            ps.setInt(2, student.getCsid().getCsid());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Kiểm tra xem năm học này học sinh đã được add vào những lớp nào
    public ArrayList<StudentClassSession> getAssignedStudentId(String yid) {
        ArrayList<StudentClassSession> list = new ArrayList<>();
        try {
            String sql = "select scs.stuid, cs.yid from Student_Class_Session scs \n"
                    + "	join Class_Session cs on scs.csid = cs.csid\n"
                    + "	where cs.yid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, yid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentClassSession scs = new StudentClassSession();

                Student s = new Student();
                s.setStuid(rs.getInt("stuid"));
                scs.setStuid(s);

                ClassSession cls = new ClassSession();
                SchoolYear year = new SchoolYear();
                year.setYid(rs.getInt("yid"));
                cls.setYid(year);

                scs.setCsid(cls);
                list.add(scs);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //Lấy ra lịch sử của học sinh đã học những lớp nào trong năm học nào
    public ArrayList<StudentClassSession> getHistorySchoolYearbyStuid(String stuid) {
        ArrayList<StudentClassSession> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    s.stuid,\n"
                    + "    s.sname,\n"
                    + "    s.dob,\n"
                    + "    se.sid,\n"
                    + "    se.Sname,\n"
                    + "    se.ageid,\n"
                    + "    c.classID,\n"
                    + "    c.clname,\n"
                    + "    y.yid,\n"
                    + "    y.dateStart,\n"
                    + "    y.dateEnd\n"
                    + "FROM \n"
                    + "    Student_Class_Session scs\n"
                    + "JOIN \n"
                    + "    Student s ON s.stuid = scs.stuid\n"
                    + "JOIN \n"
                    + "    Class_Session cs ON scs.csid = cs.csid\n"
                    + "JOIN \n"
                    + "	Session se on cs.sid = se.sid\n"
                    + "JOIN \n"
                    + "	Class c on cs.classID = c.classID\n"
                    + "JOIN \n"
                    + "    SchoolYear y ON cs.yid = y.yid\n"
                    + "WHERE s.stuid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, stuid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentClassSession scs = new StudentClassSession();
                ClassSession cls = new ClassSession();

                Student stu = new Student();
                stu.setStuid(rs.getInt("stuid"));
                stu.setSname(rs.getString("sname"));
                stu.setDob(rs.getString("dob"));
                scs.setStuid(stu);
                
                
                Session ses = new Session();
                ses.setSid(rs.getInt("sid"));
                ses.setSname(rs.getString("Sname"));
                cls.setSid(ses);
                scs.setCsid(cls);
                
                AgeCategory age = new AgeCategory();
                age.setAgeid(rs.getInt("ageid"));
                ses.setAge(age);
                cls.setSid(ses);
                scs.setCsid(cls);
                

                Class cla = new Class();
                cla.setClassid(rs.getInt("classID"));
                cla.setClname(rs.getString("clname"));
                cls.setClassID(cla);
                scs.setCsid(cls);

                SchoolYear year = new SchoolYear();
                year.setYid(rs.getInt("yid"));
                year.setDateStart(rs.getString("dateStart"));
                year.setDateEnd(rs.getString("dateEnd"));
                cls.setYid(year);
                scs.setCsid(cls);

                list.add(scs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<StudentClassSession> getStudentClassSessionbyCsid(int csid) {
        ArrayList<StudentClassSession> list = new ArrayList<>();
        try {
            String sql = "SELECT [scid]\n"
                    + "      ,[stuid]\n"
                    + "      ,[csid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Student_Class_Session]\n"
                    + "  WHERE csid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, csid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentClassSession stuclass = new StudentClassSession();
                StudentDBContext stud = new StudentDBContext();
                Class_SessionDBContext ses = new Class_SessionDBContext();
                stuclass.setScid(rs.getInt("scid"));

                stuclass.setStuid(stud.getStudentById(rs.getInt("stuid")));

                //stuclass.setCsid(ses.getClassSessionById(rs.getInt("csid")));
                stuclass.setCsid(ses.getClassSessionById(csid));

                list.add(stuclass);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<StudentClassSession> getAllStudentClassSession() {
        ArrayList<StudentClassSession> stu = new ArrayList();
        try {
            String sql = "SELECT [scid]\n"
                    + "      ,[stuid]\n"
                    + "      ,[csid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Student_Class_Session]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StudentClassSession student = new StudentClassSession();
                Class_SessionDBContext classSess = new Class_SessionDBContext();
                StudentDBContext stud = new StudentDBContext();
                student.setScid(rs.getInt("scid"));
                student.setCsid(classSess.getClassSessionById(rs.getInt("csid")));
                student.setStuid(stud.getStudentById(rs.getInt("stuid")));

                stu.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return stu;
    }

    public Lecturers_Class_Session getLecturerByCsid(int id) {
        Lecturers_Class_Session lec = new Lecturers_Class_Session();
        try {
            String sql = "SELECT [lclassID]\n"
                    + "      ,[lid]\n"
                    + "      ,[csid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Lecturers_Class_Session] Where csid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Class_SessionDBContext classSess = new Class_SessionDBContext();
                LecturersDBContext lectu = new LecturersDBContext();
                lec.setLclassID(rs.getInt("lclassID"));
                lec.setCsid(classSess.getClassSessionById(rs.getInt("csid")));
                lec.setLid(lectu.getLecturerByid(rs.getInt("lid")));
                return lec;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Lecturers_Class_Session> getLecturersByCsid(int id) {
        ArrayList<Lecturers_Class_Session> lecturers = new ArrayList<>();
        try {
            String sql = "SELECT [lclassID], [lid], [csid] FROM [SchoolManagement].[dbo].[Lecturers_Class_Session] WHERE csid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lecturers_Class_Session lec = new Lecturers_Class_Session();
                Class_SessionDBContext classSess = new Class_SessionDBContext();
                LecturersDBContext lectu = new LecturersDBContext();
                lec.setLclassID(rs.getInt("lclassID"));
                lec.setCsid(classSess.getClassSessionById(rs.getInt("csid")));
                lec.setLid(lectu.getLecturerByid(rs.getInt("lid")));
                lecturers.add(lec);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lecturers;
    }

    //check năm học đó đã tồn tại chưa
    public boolean isSchoolYearExists(String dateStart, String dateEnd) {
        try {
            String sql = "  SELECT COUNT(*) FROM SchoolYear WHERE dateStart = ? OR dateEnd = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, dateStart);
            ps.setString(2, dateEnd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Lấy ra năm học mới nhất
    public SchoolYear getNewestSchoolYear() {
        String sql = "SELECT TOP 1 * FROM SchoolYear ORDER BY dateStart DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                SchoolYear year = new SchoolYear();
                year.setYid(resultSet.getInt("yid"));
                year.setDateStart(resultSet.getString("dateStart"));
                year.setDateEnd(resultSet.getString("dateEnd"));
                return year;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Tạo Năm học mới và lấy những lớp học có trạng thái status : active
    public int createNewSchoolYearForClassSession(String dateStart, String dateEnd) {
        int newYid = 0;  // tạo biến để lưu năm học mới khi được tạo
        int previousYid = 0; // tạo biến để lưu năm học gần nhất
        try {
            // hàm kết nối để xử lý
            connection.setAutoCommit(false);

            // Insert bản ghi năm học mới
            String insertNewYear = "INSERT INTO SchoolYear (dateStart, dateEnd) VALUES (?, ?);";
            PreparedStatement ps = connection.prepareStatement(insertNewYear, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, dateStart);
            ps.setString(2, dateEnd);
            ps.executeUpdate();

            // tạo năm học mới => lấy ra id của năm học vừa đc insert
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                newYid = rs.getInt(1);
            }
            rs.close();
            ps.close();

            // Lấy năm học gần nhất (ngoại trừ năm học mới vừa tạo)
            if (newYid != 0) {
                String getPreviousYear = "SELECT TOP 1 yid FROM SchoolYear WHERE yid <> ? ORDER BY dateEnd DESC;";
                PreparedStatement psGetPrev = connection.prepareStatement(getPreviousYear);
                psGetPrev.setInt(1, newYid);
                ResultSet rsPrev = psGetPrev.executeQuery();
                if (rsPrev.next()) {
                    previousYid = rsPrev.getInt(1);
                }
                rsPrev.close();
                psGetPrev.close();
            }

            // Nếu có năm học gần nhất, insert tiếp vào Class_Session
            if (newYid != 0 && previousYid != 0) {
                // Thêm các bản ghi vào Class_Session cho năm học mới
                String insertNewCsid = "INSERT INTO Class_Session (classID, yid, sid, rid, status) "
                        + "SELECT cs.classID, ?, cs.sid, cs.rid, cs.status "
                        + "FROM Class_Session cs "
                        + "WHERE cs.yid = ? AND cs.status = 1 OR cs.status = 0"
                        + "AND NOT EXISTS ( "
                        + "    SELECT 1 "
                        + "    FROM Class_Session cs2 "
                        + "    WHERE cs2.classID = cs.classID "
                        + "    AND cs2.yid = ? "
                        + ");";
                PreparedStatement ps2 = connection.prepareStatement(insertNewCsid);
                ps2.setInt(1, newYid);
                ps2.setInt(2, previousYid);
                ps2.setInt(3, newYid);
                ps2.executeUpdate();
                ps2.close();

                // Thêm các bản ghi vào Lecturers_Class_Session cho năm học mới
                String insertNewLcsid = "INSERT INTO Lecturers_Class_Session (lid, csid) "
                        + "SELECT lcs.lid, new_cs.csid "
                        + "FROM Lecturers_Class_Session lcs "
                        + "JOIN Class_Session old_cs ON lcs.csid = old_cs.csid "
                        + "JOIN Class_Session new_cs ON old_cs.classID = new_cs.classID AND new_cs.yid = ? "
                        + "WHERE old_cs.yid = ? "
                        + "AND NOT EXISTS ( "
                        + "    SELECT 1 "
                        + "    FROM Lecturers_Class_Session lcs2 "
                        + "    WHERE lcs2.lid = lcs.lid "
                        + "    AND lcs2.csid = new_cs.csid "
                        + ");";
                PreparedStatement ps3 = connection.prepareStatement(insertNewLcsid);
                ps3.setInt(1, newYid);
                ps3.setInt(2, previousYid);
                ps3.executeUpdate();
                ps3.close();
            }

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException rollbackEx) {
                Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, rollbackEx);
            }
            return 0;
        }
        return newYid; // Trả về ID của năm học mới được chèn
    }

    //Lấy ra số lượng học sinh trong lớp
    public int getStudentCountByClassSession(int csid) {
        int count = 0;
        String sql = "SELECT COUNT(*) AS total FROM [dbo].[Student_Class_Session] WHERE csid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, csid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    //Lấy Class Session theo yid và status
    public ArrayList<ClassSession> getClassSessionByYid(String id, boolean status) {
        ArrayList<ClassSession> list = new ArrayList<>();
        try {
            String sql = "SELECT [csid]\n"
                    + "      ,[classID]\n"
                    + "      ,[yid]\n"
                    + "      ,[sid]\n"
                    + "      ,[rid]\n"
                    + "      ,[status]\n"
                    + "  FROM [SchoolManagement].[dbo].[Class_Session]\n"
                    + "  WHERE yid = ? AND status = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.setBoolean(2, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClassSession cls = new ClassSession();
                ClassDBContext cldb = new ClassDBContext();
                RoomDBContext rdb = new RoomDBContext();
                SessionDBContext sedb = new SessionDBContext();
                SchoolYearDBContext yearDB = new SchoolYearDBContext();

                cls.setCsid(rs.getInt("csid"));
                cls.setClassID(cldb.getClassById(rs.getInt("classID")));
                cls.setRid(rdb.getRoomByRid(rs.getInt("rid")));
                cls.setSid(sedb.getSessionById(rs.getInt("sid")));
                cls.setYid(yearDB.getSchoolYearById(rs.getInt("yid")));
                cls.setStatus(rs.getBoolean("status"));

                list.add(cls);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;

    }

    public ArrayList<ClassSession> getClassSessionByYid2(int id) {
        ArrayList<ClassSession> list = new ArrayList<>();
        try {
            String sql = "SELECT [csid]\n"
                    + "      ,[classID]\n"
                    + "      ,[yid]\n"
                    + "      ,[sid]\n"
                    + "      ,[rid]\n"
                    + "      ,[status]\n"
                    + "  FROM [SchoolManagement].[dbo].[Class_Session]\n"
                    + "  WHERE yid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClassSession cls = new ClassSession();
                ClassDBContext cldb = new ClassDBContext();
                RoomDBContext rdb = new RoomDBContext();
                SessionDBContext sedb = new SessionDBContext();
                SchoolYearDBContext yearDB = new SchoolYearDBContext();

                cls.setCsid(rs.getInt("csid"));
                cls.setClassID(cldb.getClassById(rs.getInt("classID")));
                cls.setRid(rdb.getRoomByRid(rs.getInt("rid")));
                cls.setSid(sedb.getSessionById(rs.getInt("sid")));
                cls.setYid(yearDB.getSchoolYearById(rs.getInt("yid")));
                cls.setStatus(rs.getBoolean("status"));

                list.add(cls);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;

    }

    //Hàm kiểm tra xem lớp học đó có học sinh không
    public boolean getStudentCountByClassSession2(int classID) {

        try {
            String sql = "SELECT COUNT(*) AS student_count FROM [dbo].[Student_Class_Session] WHERE csid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, classID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("student_count") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Hàm Kiểm tra xem lớp học đó đã có giáo viên chưa
    public boolean getLecturersCountByClassSession2(int classID) {
        try {
            String sql = "SELECT COUNT(*) AS lecturers_count FROM [dbo].[Lecturers_Class_Session] WHERE csid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, classID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("lecturers_count") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Hàm insert new ClassSession 
    public void insertClassSession(ClassSession cls) {
        try {
            String sql = "INSERT INTO [dbo].[Class_Session] "
                    + "([classID], [yid], [sid], [rid], [status]) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, cls.getClassID().getClassid());

            ps.setInt(2, cls.getYid().getYid());

            if (cls.getSid() != null) {
                ps.setInt(3, cls.getSid().getSid());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            if (cls.getRid() != null) {
                ps.setInt(4, cls.getRid().getRid());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }

            ps.setBoolean(5, cls.getStatus());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Hàm update ClassSession
    public void updateClassSession(ClassSession cls) {
        try {
            String sql = "UPDATE [dbo].[Class_Session] "
                    + "SET [rid] = ?, [sid] = ?, [status] = ? "
                    + "WHERE [classID] = ? AND [yid] = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Set rid (room ID) or NULL
            if (cls.getRid() != null) {
                ps.setInt(1, cls.getRid().getRid());
            } else {
                ps.setNull(1, java.sql.Types.INTEGER);
            }

            // Set sid (session ID) or NULL
            if (cls.getSid() != null) {
                ps.setInt(2, cls.getSid().getSid());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            ps.setBoolean(3, cls.getStatus());
            ps.setInt(4, cls.getClassID().getClassid());
            ps.setInt(5, cls.getYid().getYid());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Hàm update ClassSession Only Room
    public void updateClassSessionRoom(ClassSession cls){
        try {
            String sql = "UPDATE [dbo].[Class_Session] SET rid = ? WHERE csid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cls.getRid().getRid());
            ps.setInt(2, cls.getCsid());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SchoolYearDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
