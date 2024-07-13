/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Lecturers;
import Entity.Class;
import Entity.ClassSession;
import Entity.Lecturers_Class_Session;
import Entity.Parent;
import Entity.SchoolYear;
import Entity.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class LecturerClassSession extends DBContext {

    public boolean isEmailExits(String email){
        String sql="select count(*) as count from lecturers where email= ? and status is not null";
         try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1,email);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Student> getStudentsForLecturers(int lid) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT S.stuid, S.sname, S.dob, S.gender, P.phoneNumber, P.pname "
                + "FROM Student S "
                + "INNER JOIN Student_Class_Session SCS ON S.stuid = SCS.stuid "
                + "INNER JOIN Class_Session CS ON SCS.csid = CS.csid "
                + "INNER JOIN Lecturers_Class_Session LCS ON CS.csid = LCS.csid "
                + "INNER JOIN Parent P ON S.pid = P.pid "
                + "INNER JOIN Class CL ON CS.classID = CL.classID "
                + "INNER JOIN SchoolYear SY ON CS.yid = SY.yid "
                + "WHERE SY.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) "
                + "AND LCS.lid = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, lid); // Set lid parameter
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));
                student.setDob(rs.getString("dob"));
                student.setGender(rs.getBoolean("gender"));

                Parent parent = new Parent();
                parent.setPname(rs.getString("pname"));
                parent.setPhoneNumber(rs.getString("phoneNumber"));

                // Set parent object to student
                student.setPid(parent);

                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception properly
        }
        return list;
    }

    public Lecturers_Class_Session getLecturerByid(String lid) {

        try {
            String sql = "SELECT\n"
                    + "L.lid,\n"
                    + "L.lname,\n"
                    + "L.dob,\n"
                    + "L.gender,\n"
                    + "L.phoneNumber,\n"
                    + "L.IDcard,\n"
                    + "L.[Address],\n"
                    + "L.NickName,\n"
                    + "L.Email,\n"
                    + "C.classID,\n"
                    + "C.clname\n"
                    + "FROM\n"
                    + "Lecturers L\n"
                    + "LEFT JOIN\n"
                    + "Lecturers_Class_Session LCS ON L.lid = LCS.lid\n"
                    + "LEFT JOIN\n"
                    + "Class_Session CS ON LCS.csid = CS.csid\n"
                    + "LEFT JOIN\n"
                    + "Class C ON CS.classID = C.classID\n"
                    + "where L.lid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lecturers lecturer = new Lecturers();
                lecturer.setLid(rs.getInt("lid"));
                lecturer.setLname(rs.getString("lname"));
                lecturer.setGender(rs.getBoolean("gender"));
                lecturer.setDob(rs.getString("dob")); // assuming dob is of type Date
                lecturer.setPhoneNumber(rs.getString("phoneNumber"));
                lecturer.setIDcard(rs.getString("IDcard"));
                lecturer.setEmail(rs.getString("Email"));
                lecturer.setAddress(rs.getString("Address"));
                lecturer.setNickname(rs.getString("NickName"));

                Class cl = new Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(cl);

                Lecturers_Class_Session lecClass = new Lecturers_Class_Session();
                lecClass.setLid(lecturer);
                lecClass.setCsid(cs);
                return lecClass;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Lecturers_Class_Session getLecturerByidClass(String lid) {

        try {
            String sql = "SELECT *\n"
                    + "FROM\n"
                    + "Lecturers L\n"
                    + "LEFT JOIN\n"
                    + "Lecturers_Class_Session LCS ON L.lid = LCS.lid\n"
                    + "LEFT JOIN\n"
                    + "Class_Session CS ON LCS.csid = CS.csid\n"
                    + "LEFT JOIN\n"
                    + "Class C ON CS.classID = C.classID\n"
                    + "left join SchoolYear sy on cs.yid = sy.yid\n"
                    + "where L.lid = ? and sy.yid = (select Max(yid) from SchoolYear) and lcs.status is not null;";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lecturers l = new Lecturers();
                l.setLid(rs.getInt("lid"));
                l.setLname(rs.getString("lname"));
                l.setGender(rs.getBoolean("gender"));
                l.setAddress(rs.getString("address"));
                l.setDob(rs.getString("dob"));
                l.setEmail(rs.getString("email"));
                l.setPhoneNumber(rs.getString("phoneNumber"));
                l.setIDcard(rs.getString("IDcard"));
                l.setEmail(rs.getString("email"));

                Class cl = new Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                SchoolYear sy = new SchoolYear();
                sy.setYid(rs.getInt("yid"));
                sy.setDateStart(rs.getString("dateStart"));
                sy.setDateEnd(rs.getString("dateEnd"));

                ClassSession cs = new ClassSession();
                cs.setCsid(rs.getInt("csid"));
                cs.setYid(sy);
                cs.setClassID(cl);

                Lecturers_Class_Session lcs = new Lecturers_Class_Session();
                lcs.setLid(l);
                lcs.setCsid(cs);
                return lcs;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateClass(String lid, String classID) {
        String sql = "	UPDATE [dbo].[Lecturers_Class_Session]\n"
                + "SET [status] = NULL\n"
                + "WHERE [lid] = ?;\n"
                + "\n"
                + "INSERT INTO [dbo].[Lecturers_Class_Session]\n"
                + "           ([lid]\n"
                + "           ,[csid]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,(SELECT csid FROM Class_Session WHERE classID = ? AND yid = (SELECT MAX(yid) FROM Class_Session))\n"
                + "           ,'active');";

        try {
            connection.setAutoCommit(false);

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);
            stm.setString(2, lid);
            stm.setString(3, classID);
            stm.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Lecturers_Class_Session> getAllLecturerInNewSchoolYear() {
        List<Lecturers_Class_Session> list = new ArrayList<>();
        try {
            String sql = "WITH RankedClasses AS (SELECT l.lid,l.lname,l.Address,l.dob,l.gender,l.IDcard,l.phoneNumber,l.Email,lcs.csid,\n"
                    + "ROW_NUMBER() OVER (PARTITION BY l.lid ORDER BY lcs.lclassID DESC) AS rn\n"
                    + "                        FROM lecturers l\n"
                    + "                      LEFT JOIN lecturers_class_session lcs ON l.lid = lcs.lid\n"
                    + "                      LEFT JOIN Class_Session cs ON cs.csid = lcs.csid\n"
                    + "                        LEFT JOIN SchoolYear sy ON sy.yid = cs.yid\n"
                    + "                       WHERE l.status IS NOT NULL and cs.status = '1'\n"
                    + "                        AND sy.yid = (SELECT MAX(yid) FROM SchoolYear) and lcs.status is not null )\n"
                    + "                    \n"
                    + "                    SELECT *\n"
                    + "                    FROM lecturers l\n"
                    + "                    LEFT JOIN RankedClasses rc ON l.lid = rc.lid AND rc.rn = 1\n"
                    + "                    LEFT JOIN Class_Session cs ON rc.csid = cs.csid\n"
                    + "                    LEFT JOIN Class cl ON cs.classID = cl.classID\n"
                    + "                    LEFT JOIN SchoolYear sy ON cs.yid = sy.yid\n"
                    + "                    where l.status is not null \n"
                    + "                    ORDER BY l.lid ASC;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lecturers l = new Lecturers();
                l.setLid(rs.getInt("lid"));
                l.setLname(rs.getString("lname"));
                l.setGender(rs.getBoolean("gender"));
                l.setAddress(rs.getString("address"));
                l.setDob(rs.getString("dob"));
                l.setEmail(rs.getString("email"));
                l.setPhoneNumber(rs.getString("phoneNumber"));
                l.setIDcard(rs.getString("IDcard"));
                l.setEmail(rs.getString("email"));

                Class cl = new Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                SchoolYear sy = new SchoolYear();
                sy.setYid(rs.getInt("yid"));
                sy.setDateStart(rs.getString("dateStart"));
                sy.setDateEnd(rs.getString("dateEnd"));

                ClassSession cs = new ClassSession();
                cs.setCsid(rs.getInt("csid"));
                cs.setYid(sy);
                cs.setClassID(cl);

                Lecturers_Class_Session lcs = new Lecturers_Class_Session();
                lcs.setLid(l);
                lcs.setCsid(cs);

                list.add(lcs);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public SchoolYear getNewSchoolYear() {
        SchoolYear sc = new SchoolYear();
        try {
            String sql = "select * from SchoolYear where yid = (select max (yid) from SchoolYear)";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                sc.setYid(rs.getInt("yid"));
                sc.setDateStart(rs.getString("dateStart"));
                sc.setDateEnd(rs.getString("dateEnd"));
                return sc;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Lecturers_Class_Session> getHistoryAllLecturerUpdate(String lid, String yid) {
        List<Lecturers_Class_Session> list = new ArrayList<>();
        try {
            String sql = "select l.lname,lcs.Status,cl.clname from Lecturers l \n"
                    + "left join Lecturers_Class_Session lcs on l.lid = lcs.lid\n"
                    + "left join Class_Session cs on cs.csid = lcs.csid \n"
                    + "left join Class cl on cl.classID = cs.classID\n"
                    + "left join SchoolYear sy on sy.yid = cs.yid \n"
                    + "where l.lid = ? and sy.yid = ? and lcs.Status is null;\n"
                    + " ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);
            stm.setString(2, yid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lecturers l = new Lecturers();
                l.setLname(rs.getString("lname"));
                Class cl = new Class();
                cl.setClname(rs.getString("clname"));
                SchoolYear sy = new SchoolYear();
                ClassSession cs = new ClassSession();
                cs.setYid(sy);
                cs.setClassID(cl);

                Lecturers_Class_Session lcs = new Lecturers_Class_Session();
                lcs.setStatus(rs.getString("Status"));
                lcs.setLid(l);
                lcs.setCsid(cs);

                list.add(lcs);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Lecturers_Class_Session> getHistoryAllLecturer(String yid) {
        List<Lecturers_Class_Session> list = new ArrayList<>();
        try {
            String sql = "WITH RankedClasses AS (SELECT l.lid,l.lname,l.Address,l.dob,l.gender,l.IDcard,l.phoneNumber,l.Email,lcs.csid,\n"
                    + "    ROW_NUMBER() OVER (PARTITION BY l.lid ORDER BY lcs.lclassID DESC) AS rn\n"
                    + "    FROM lecturers l\n"
                    + "    LEFT JOIN lecturers_class_session lcs ON l.lid = lcs.lid\n"
                    + "    LEFT JOIN Class_Session cs ON cs.csid = lcs.csid\n"
                    + "    LEFT JOIN SchoolYear sy ON sy.yid = cs.yid\n"
                    + "    WHERE sy.yid = ? and lcs.status is not null\n"
                    + ")\n"
                    + "SELECT *\n"
                    + "FROM lecturers l\n"
                    + "LEFT JOIN RankedClasses rc ON l.lid = rc.lid AND rc.rn = 1\n"
                    + "LEFT JOIN Class_Session cs ON rc.csid = cs.csid\n"
                    + "LEFT JOIN Class cl ON cs.classID = cl.classID\n"
                    + "LEFT JOIN SchoolYear sy ON cs.yid = sy.yid\n"
                    + "ORDER BY l.lid ASC;\n"
                    + "";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, yid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lecturers l = new Lecturers();
                l.setLid(rs.getInt("lid"));
                l.setLname(rs.getString("lname"));
                l.setGender(rs.getBoolean("gender"));
                l.setAddress(rs.getString("address"));
                l.setDob(rs.getString("dob"));
                l.setEmail(rs.getString("email"));
                l.setPhoneNumber(rs.getString("phoneNumber"));
                l.setIDcard(rs.getString("IDcard"));
                l.setEmail(rs.getString("email"));
                l.setStatus(rs.getString("status"));

                Class cl = new Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                SchoolYear sy = new SchoolYear();
                sy.setYid(rs.getInt("yid"));
                sy.setDateStart(rs.getString("dateStart"));
                sy.setDateEnd(rs.getString("dateEnd"));

                ClassSession cs = new ClassSession();
                cs.setCsid(rs.getInt("csid"));
                cs.setYid(sy);
                cs.setClassID(cl);

                Lecturers_Class_Session lcs = new Lecturers_Class_Session();

                lcs.setLid(l);
                lcs.setCsid(cs);

                list.add(lcs);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Lecturers_Class_Session getLecturerByCsidById(int id) {
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
                lec.setLclassID(rs.getInt("scid"));
                lec.setCsid(classSess.getClassSessionById(rs.getInt("csid")));
                lec.setLid(lectu.getLecturerByid(rs.getInt("csid")));
                return lec;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        String sql = "SELECT COUNT(*) AS count FROM Lecturers WHERE phoneNumber = ?  and status is not null";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, phoneNumber);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isIDCardExists(String IDCard) {
        String sql = "SELECT COUNT(*) AS count FROM Lecturers WHERE IDcard = ? and status is not null";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, IDCard);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getTotalLecturerInClass(String classID) {
        try {
            String sql = "SELECT COUNT(*)\n"
                    + "FROM Lecturers l\n"
                    + "LEFT JOIN lecturers_class_session lcs ON l.lid = lcs.lid\n"
                    + "LEFT JOIN Class_Session cs ON cs.csid = lcs.csid\n"
                    + "LEFT JOIN Class cl ON cl.classID = cs.classID\n"
                    + "LEFT JOIN SchoolYear sy ON sy.yid = cs.yid\n"
                    + "WHERE cl.classID = ? and lcs.status is not null AND sy.yid = (SELECT MAX(yid) FROM SchoolYear) AND l.status IS NOT NULL;";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, classID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;

    }

    public void addLecturer(String lname, String gender, String dob, String phoneNumber, String IDcard, String address, String email, String classID) {
        String sql = "INSERT INTO [dbo].[Lecturers] \n"
                + "                ([lname],[gender],[dob],[phoneNumber],[IDcard],[Address],[Email],[status])\n"
                + "                VALUES (?,?,?,?,?,?,?,'active');\n"
                + "                \n"
                + "                INSERT INTO [dbo].[Account]([username],[password],[role],[lid],[status])\n"
                + "                SELECT phoneNumber, FLOOR(RAND()*100000),'2',lid,'1'\n"
                + "                FROM Lecturers WHERE lid = (SELECT MAX(lid) FROM Lecturers);\n"
                + "                \n"
                + "                INSERT INTO [dbo].[Lecturers_Class_Session]([lid],[csid],[status])\n"
                + "                VALUES ((select max(lid) from Lecturers)\n"
                + "                ,(SELECT csid FROM Class_Session WHERE classID = ? AND yid = (SELECT MAX(yid) FROM Class_Session)),'active');";

        try {
            connection.setAutoCommit(false);

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lname);
            stm.setString(2, gender);
            stm.setString(3, dob);
            stm.setString(4, phoneNumber);
            stm.setString(5, IDcard);
            stm.setString(6, address);
            stm.setString(7, email);
            stm.setString(8, classID);

            stm.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public List<Lecturers_Class_Session> getAllLecturerClassSessions() {
        List<Lecturers_Class_Session> list = new ArrayList<>();

        try {
            String sql = "SELECT\n"
                    + "               L.lid,\n"
                    + "                  L.lname,\n"
                    + "              L.dob,\n"
                    + "              L.gender,\n"
                    + "                L.phoneNumber,\n"
                    + "                 L.IDcard,\n"
                    + "                    L.[Address],\n"
                    + "              L.NickName,\n"
                    + "                L.Email,\n"
                    + "                C.classID,\n"
                    + "                  C.clname\n"
                    + "              FROM\n"
                    + "                   Lecturers L\n"
                    + "               LEFT JOIN\n"
                    + "                   Lecturers_Class_Session LCS ON L.lid = LCS.lid\n"
                    + "              LEFT JOIN\n"
                    + "                 Class_Session CS ON LCS.csid = CS.csid\n"
                    + "                LEFT JOIN\n"
                    + "           Class C ON CS.classID = C.classID;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                LecturerClassSession lecturerClassSession = new LecturerClassSession();

                Lecturers lecturer = new Lecturers();
                lecturer.setLid(rs.getInt("lid"));
                lecturer.setLname(rs.getString("lname"));
                lecturer.setGender(rs.getBoolean("gender"));
                lecturer.setDob(rs.getString("dob"));
                lecturer.setPhoneNumber(rs.getString("phoneNumber"));
                lecturer.setIDcard(rs.getString("IDcard"));
                lecturer.setEmail(rs.getString("Email"));
                lecturer.setAddress(rs.getString("Address"));
                lecturer.setNickname(rs.getString("NickName"));

                Class cl = new Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(cl);

                Lecturers_Class_Session lecClass = new Lecturers_Class_Session();
                lecClass.setLid(lecturer);
                lecClass.setCsid(cs);
                list.add(lecClass);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Lecturers_Class_Session> getAllLecturerBySchoolYear(String timeStart, String timeEnd) {
        List<Lecturers_Class_Session> list = new ArrayList<>();

        try {
            String sql = "SELECT\n"
                    + "    L.lid,\n"
                    + "    L.lname,\n"
                    + "    L.dob,\n"
                    + "    L.gender,\n"
                    + "    L.phoneNumber,\n"
                    + "    L.IDcard,\n"
                    + "    L.[Address],\n"
                    + "    L.NickName,\n"
                    + "    L.Email,\n"
                    + "    C.classID,\n"
                    + "    C.clname\n"
                    + "FROM\n"
                    + "    Lecturers L\n"
                    + "LEFT JOIN\n"
                    + "    Lecturers_Class_Session LCS ON L.lid = LCS.lid\n"
                    + "LEFT JOIN\n"
                    + "    Class_Session CS ON LCS.csid = CS.csid\n"
                    + "LEFT JOIN\n"
                    + "    Class C ON C.classID = CS.classID\n"
                    + "LEFT JOIN\n"
                    + "    SchoolYear sy ON CS.yid = sy.yid\n"
                    + "WHERE\n"
                    + "    sy.dateStart LIKE ? AND sy.dateEnd LIKE ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, timeStart + "%");
            stm.setString(2, timeEnd + "%");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                LecturerClassSession lecturerClassSession = new LecturerClassSession();

                Lecturers lecturer = new Lecturers();
                lecturer.setLid(rs.getInt("lid"));
                lecturer.setLname(rs.getString("lname"));
                lecturer.setGender(rs.getBoolean("gender"));
                lecturer.setDob(rs.getString("dob"));
                lecturer.setPhoneNumber(rs.getString("phoneNumber"));
                lecturer.setIDcard(rs.getString("IDcard"));
                lecturer.setEmail(rs.getString("Email"));
                lecturer.setAddress(rs.getString("Address"));
                lecturer.setNickname(rs.getString("NickName"));

                Class cl = new Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(cl);

                Lecturers_Class_Session lecClass = new Lecturers_Class_Session();
                lecClass.setLid(lecturer);
                lecClass.setCsid(cs);
                list.add(lecClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Lecturers_Class_Session> getLecturersBySchoolYearWithPaging(String timeStart, String timeEnd, int index) {
        List<Lecturers_Class_Session> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "l.lid,\n"
                + "l.lname,\n"
                + "l.gender,\n"
                + "l.dob,\n"
                + "l.phoneNumber,\n"
                + "l.IDcard,\n"
                + "l.Email,\n"
                + "l.Address,\n"
                + "l.NickName,\n"
                + "Cl.classID,\n"
                + "cl.clname\n"
                + "FROM Lecturers_Class_Session lcs\n"
                + "JOIN Lecturers l ON lcs.lid = l.lid\n"
                + "JOIN Class_Session cs ON lcs.csid = cs.csid\n"
                + "JOIN Class cl ON cl.classID = cs.classID\n"
                + "JOIN SchoolYear sy ON cs.yid = sy.yid\n"
                + "WHERE sy.dateStart LIKE ? AND sy.dateEnd LIKE ?\n"
                + "ORDER BY l.lid OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, timeStart + "%");
            stm.setString(2, timeEnd + "%");
            stm.setInt(3, (index - 1) * 10);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lecturers lecturer = new Lecturers();
                lecturer.setLid(rs.getInt("lid"));
                lecturer.setLname(rs.getString("lname"));
                lecturer.setGender(rs.getBoolean("gender"));
                lecturer.setDob(rs.getString("dob"));
                lecturer.setPhoneNumber(rs.getString("phoneNumber"));
                lecturer.setIDcard(rs.getString("IDcard"));
                lecturer.setEmail(rs.getString("Email"));
                lecturer.setAddress(rs.getString("Address"));
                lecturer.setNickname(rs.getString("NickName"));

                Class cl = new Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(cl);

                Lecturers_Class_Session lecClass = new Lecturers_Class_Session();
                lecClass.setLid(lecturer);
                lecClass.setCsid(cs);
                list.add(lecClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerClassSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Lecturers_Class_Session> getLecturerByName(String lname) {
        List<Lecturers_Class_Session> list = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "    L.lid,\n"
                    + "    L.lname,\n"
                    + "    L.dob,\n"
                    + "    L.gender,\n"
                    + "    L.phoneNumber,\n"
                    + "    L.IDcard,\n"
                    + "    L.[Address],\n"
                    + "    L.NickName,\n"
                    + "    L.Email,\n"
                    + "    C.classID,\n"
                    + "    C.clname\n"
                    + "FROM\n"
                    + "    Lecturers L\n"
                    + "LEFT JOIN\n"
                    + "    Lecturers_Class_Session LCS ON L.lid = LCS.lid\n"
                    + "LEFT JOIN\n"
                    + "    Class_Session CS ON LCS.csid = CS.csid\n"
                    + "LEFT JOIN\n"
                    + "    Class C ON CS.classID = C.classID\n"
                    + "WHERE\n"
                    + "    L.lname LIKE ?;";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + lname + "%"); // Adding wildcard characters here

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lecturers lecturer = new Lecturers();
                lecturer.setLid(rs.getInt("lid"));
                lecturer.setLname(rs.getString("lname"));
                lecturer.setGender(rs.getBoolean("gender"));
                lecturer.setDob(rs.getDate("dob").toString()); // assuming dob is of type Date
                lecturer.setPhoneNumber(rs.getString("phoneNumber"));
                lecturer.setIDcard(rs.getString("IDcard"));
                lecturer.setEmail(rs.getString("Email"));
                lecturer.setAddress(rs.getString("Address"));
                lecturer.setNickname(rs.getString("NickName"));

                Class cl = new Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(cl);

                Lecturers_Class_Session lecClass = new Lecturers_Class_Session();
                lecClass.setLid(lecturer);
                lecClass.setCsid(cs);
                list.add(lecClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerClassSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void insertLecturers(String lname, String gender, String dob, String phoneNumber, String IDcard, String address, String email, String nickName, String classID) {
        String sql = "INSERT INTO Lecturers (lname, gender, dob, phoneNumber, IDcard, [Address], Email, NickName)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);\n"
                + "INSERT INTO Lecturers_Class_Session (lid, csid)\n"
                + "VALUES (\n"
                + "    (SELECT MAX(lid) FROM Lecturers),\n"
                + "    (SELECT csid FROM Class_Session WHERE classID = ? AND yid = (SELECT MAX(yid) FROM Class_Session))\n"
                + ");";
        try {
            connection.setAutoCommit(false); // Begin transaction

            // Prepare statement for inserting lecturer
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lname);
            stm.setString(2, gender);
            stm.setString(3, dob);
            stm.setString(4, phoneNumber);
            stm.setString(5, IDcard);
            stm.setString(6, address);
            stm.setString(7, email);
            stm.setString(8, nickName);
            stm.setString(9, classID);

            // Execute the insert statements
            stm.executeUpdate();

            connection.commit(); // Commit transaction
        } catch (SQLException ex) {
            try {
                connection.rollback(); // Rollback transaction on error
            } catch (SQLException e) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
            }
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true); // Restore default commit behavior
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void promoteLecturer(String lid, String classID) {
        try {
            String sql = "INSERT INTO Lecturers_Class_Session (lid, csid)\n"
                    + "                VALUES (\n"
                    + "                   ?,\n"
                    + "                (SELECT csid FROM Class_Session WHERE classID = ? AND yid = (SELECT MAX(yid) FROM Class_Session)));";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);
            stm.setString(2, classID);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LecturerClassSession.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteLecturer(String lid) {
        try {
            String sql = "delete from Lecturers_Class_Session where lid= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LecturerClassSession.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateLecturerinClass(int classID, int lid) {
        try {
            // Assuming 'connection' is initialized somewhere in your class
            String sql = "UPDATE [dbo].[Lecturers_Class_Session]\n"
                    + "   SET \n"
                    + "      [csid] = (SELECT csid FROM Class_Session WHERE classID = ? AND yid = (SELECT MAX(yid) FROM Class_Session))\n"
                    + " WHERE lid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, classID);
            stm.setInt(2, lid);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LecturersDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        LecturerClassSession lc = new LecturerClassSession();
        List<Lecturers_Class_Session> list = lc.getLecturersBySchoolYearWithPaging("2023", "2024", 1);
        System.out.println(list);

    }

}
