/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.ClassSession;
import Entity.Parent;
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
 * @author Admin
 */
public class StudentClassSessionDBContext extends DBContext {

    public static void main(String[] args) {

    }

    //get actives student of the last school year and paging
    public List<StudentClassSession> getStudentClassSessionByLatestSchoolYearWithPaging(int index) {
        List<StudentClassSession> list = new ArrayList<>();
        try {
            String sql
                    = "SELECT S.stuid, S.sname, S.dob, S.gender, S.[Address], p.pid, p.pname, cl.classID, cl.clname "
                    + "FROM Student S "
                    + "INNER JOIN Student_Class_Session scs ON S.stuid = scs.stuid "
                    + "INNER JOIN Class_Session cs ON scs.csid = cs.csid "
                    + "INNER JOIN Parent p ON S.pid = p.pid "
                    + "INNER JOIN Class cl ON cs.classID = cl.classID "
                    + "INNER JOIN SchoolYear sy ON cs.yid = sy.yid "
                    + "WHERE sy.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) " //get lastest schoolyear
                    + "AND S.status = 1 "
                    + "ORDER BY S.stuid OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 10);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));
                student.setGender(rs.getBoolean("gender"));
                student.setDob(rs.getString("dob"));
                student.setAddress(rs.getString("Address"));

                Entity.Class cl = new Entity.Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(cl);

                Parent parent = new Parent();
                parent.setPid(rs.getInt("pid"));
                parent.setPname(rs.getString("pname"));
                student.setPid(parent);

                StudentClassSession stuClass = new StudentClassSession();
                stuClass.setCsid(cs);
                stuClass.setStuid(student);
                list.add(stuClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //get inactives student of the last school year and paging
    public List<StudentClassSession> getStudentInactiveClassSessionByLatestSchoolYearWithPaging(int index) {
        List<StudentClassSession> list = new ArrayList<>();
        try {
            String sql
                    = "SELECT S.stuid, S.sname, S.dob, S.gender, S.[Address], p.pid, p.pname, cl.classID, cl.clname "
                    + "FROM Student S "
                    + "INNER JOIN Student_Class_Session scs ON S.stuid = scs.stuid "
                    + "INNER JOIN Class_Session cs ON scs.csid = cs.csid "
                    + "INNER JOIN Parent p ON S.pid = p.pid "
                    + "INNER JOIN Class cl ON cs.classID = cl.classID "
                    + "INNER JOIN SchoolYear sy ON cs.yid = sy.yid "
                    + "WHERE sy.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) " //get lastest schoolyear
                    + "AND S.status = 0 "
                    + "ORDER BY S.stuid OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 10);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));
                student.setGender(rs.getBoolean("gender"));
                student.setDob(rs.getString("dob"));
                student.setAddress(rs.getString("Address"));

                Entity.Class cl = new Entity.Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(cl);

                Parent parent = new Parent();
                parent.setPid(rs.getInt("pid"));
                parent.setPname(rs.getString("pname"));
                student.setPid(parent);

                StudentClassSession stuClass = new StudentClassSession();
                stuClass.setCsid(cs);
                stuClass.setStuid(student);
                list.add(stuClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //Get list of students by class ID of the most recent school year and paginate
    public List<StudentClassSession> getStudentsByClassIdWithPaging(String classId, int index) {
        List<StudentClassSession> list = new ArrayList<>();
        try {
            String sql
                    = "SELECT S.stuid, S.sname, S.dob, S.gender, S.[Address], p.pid, p.pname, cl.classID, cl.clname "
                    + "FROM Student S "
                    + "INNER JOIN Student_Class_Session scs ON S.stuid = scs.stuid "
                    + "INNER JOIN Class_Session cs ON scs.csid = cs.csid "
                    + "INNER JOIN Parent p ON S.pid = p.pid "
                    + "INNER JOIN class cl ON cs.classID = cl.classID "
                    + "INNER JOIN SchoolYear sy ON cs.yid = sy.yid "
                    + "WHERE sy.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) "
                    + "AND cl.classID = ? "
                    + "AND S.status = 1 "
                    + "ORDER BY S.stuid OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, classId);
            stm.setInt(2, (index - 1) * 10);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));
                student.setGender(rs.getBoolean("gender"));
                student.setDob(rs.getString("dob"));
                student.setAddress(rs.getString("Address"));

                Entity.Class classObj = new Entity.Class();
                classObj.setClassid(rs.getInt("classID"));
                classObj.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(classObj);

                Parent parent = new Parent();
                parent.setPid(rs.getInt("pid"));
                parent.setPname(rs.getString("pname"));
                student.setPid(parent);

                StudentClassSession stuClass = new StudentClassSession();
                stuClass.setCsid(cs);
                stuClass.setStuid(student);
                list.add(stuClass);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //search student inactive by id
    public StudentClassSession getStudentInactiveById(int id) {
        StudentClassSession stuClass = null;

        try {
            String sql = "SELECT S.stuid, S.sname, S.dob, S.gender, S.[Address], P.pid, P.pname, CL.classID, CL.clname "
                    + "FROM Student S "
                    + "INNER JOIN Student_Class_Session SCS ON S.stuid = SCS.stuid "
                    + "INNER JOIN Class_Session CS ON SCS.csid = CS.csid "
                    + "INNER JOIN Parent P ON S.pid = P.pid "
                    + "INNER JOIN Class CL ON CS.classID = CL.classID "
                    + "INNER JOIN SchoolYear SY ON CS.yid = SY.yid "
                    + "WHERE S.stuid = ? "
                    + "AND SY.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) "
                    + "AND S.status = 0 ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));
                student.setDob(rs.getString("dob"));
                student.setGender(rs.getBoolean("gender"));
                student.setAddress(rs.getString("Address"));

                Parent parent = new Parent();
                parent.setPid(rs.getInt("pid"));
                parent.setPname(rs.getString("pname"));
                student.setPid(parent);

                Entity.Class cl = new Entity.Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(cl);

                stuClass = new StudentClassSession();
                stuClass.setCsid(cs);
                stuClass.setStuid(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stuClass;
    }

    //search student inactive by name
    public List<StudentClassSession> getStudentInactiveByName(String sname) {
        List<StudentClassSession> list = new ArrayList<>();

        try {
            String sql = "SELECT S.stuid, S.sname, S.dob, S.gender, S.[Address],P.pid, P.pname, CL.classID ,CL.clname "
                    + "FROM Student S "
                    + "INNER JOIN Student_Class_Session SCS ON S.stuid = SCS.stuid "
                    + "INNER JOIN Class_Session CS ON SCS.csid = CS.csid "
                    + "INNER JOIN Parent P ON S.pid = P.pid "
                    + "INNER JOIN Class CL ON CS.classID = CL.classID "
                    + "INNER JOIN SchoolYear SY ON CS.yid = SY.yid "
                    + "WHERE S.sname LIKE ? "
                    + "AND SY.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) "
                    + "AND S.status = 0 ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + sname + "%");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));
                student.setDob(rs.getString("dob"));
                student.setGender(rs.getBoolean("gender"));
                student.setAddress(rs.getString("Address"));

                Parent parent = new Parent();
                parent.setPid(rs.getInt("pid"));
                parent.setPname(rs.getString("pname"));
                student.setPid(parent);

                Entity.Class cl = new Entity.Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(cl);
                StudentClassSession stuClass = new StudentClassSession();
                stuClass.setCsid(cs);
                stuClass.setStuid(student);
                list.add(stuClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //search student active by id
    public StudentClassSession getStudentById(int id) {
        StudentClassSession stuClass = null;

        try {
            String sql = "SELECT S.stuid, S.sname, S.dob, S.gender, S.[Address], P.pid, P.pname, CL.classID, CL.clname "
                    + "FROM Student S "
                    + "INNER JOIN Student_Class_Session SCS ON S.stuid = SCS.stuid "
                    + "INNER JOIN Class_Session CS ON SCS.csid = CS.csid "
                    + "INNER JOIN Parent P ON S.pid = P.pid "
                    + "INNER JOIN Class CL ON CS.classID = CL.classID "
                    + "INNER JOIN SchoolYear SY ON CS.yid = SY.yid "
                    + "WHERE S.stuid = ? "
                    + "AND SY.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) "
                    + "AND S.status = 1 ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));
                student.setDob(rs.getString("dob"));
                student.setGender(rs.getBoolean("gender"));
                student.setAddress(rs.getString("Address"));

                Parent parent = new Parent();
                parent.setPid(rs.getInt("pid"));
                parent.setPname(rs.getString("pname"));
                student.setPid(parent);

                Entity.Class cl = new Entity.Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(cl);

                stuClass = new StudentClassSession();
                stuClass.setCsid(cs);
                stuClass.setStuid(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stuClass;
    }

    //search student active by name
    public List<StudentClassSession> getStudentByName(String sname) {
        List<StudentClassSession> list = new ArrayList<>();

        try {
            String sql = "SELECT S.stuid, S.sname, S.dob, S.gender, S.[Address],P.pid, P.pname, CL.classID ,CL.clname "
                    + "FROM Student S "
                    + "INNER JOIN Student_Class_Session SCS ON S.stuid = SCS.stuid "
                    + "INNER JOIN Class_Session CS ON SCS.csid = CS.csid "
                    + "INNER JOIN Parent P ON S.pid = P.pid "
                    + "INNER JOIN Class CL ON CS.classID = CL.classID "
                    + "INNER JOIN SchoolYear SY ON CS.yid = SY.yid "
                    + "WHERE S.sname LIKE ? "
                    + "AND SY.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) "
                    + "AND S.status = 1 ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + sname + "%");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));
                student.setDob(rs.getString("dob"));
                student.setGender(rs.getBoolean("gender"));
                student.setAddress(rs.getString("Address"));

                Parent parent = new Parent();
                parent.setPid(rs.getInt("pid"));
                parent.setPname(rs.getString("pname"));
                student.setPid(parent);

                Entity.Class cl = new Entity.Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(cl);
                StudentClassSession stuClass = new StudentClassSession();
                stuClass.setCsid(cs);
                stuClass.setStuid(student);
                list.add(stuClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //get total number of students active by class ID
    public int getTotalStudentsByClassId(String classId) {
        int totalStudents = 0;
        try {
            String sql = "SELECT COUNT(DISTINCT s.stuid) AS Total_Student "
                    + "FROM Student s "
                    + "INNER JOIN Student_Class_Session scs ON s.stuid = scs.stuid "
                    + "INNER JOIN Class_Session cs ON scs.csid = cs.csid "
                    + "INNER JOIN SchoolYear sy ON cs.yid = sy.yid "
                    + "WHERE sy.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) "
                    + "AND cs.classID = ? "
                    + "AND s.status = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, classId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                totalStudents = rs.getInt("Total_Student");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return totalStudents;
    }

    //add new student 
    public boolean addNewtudent(String sname, String dob, boolean gender, String address, int pid, int classID) {

        PreparedStatement insert = null;
        PreparedStatement insertStuToClass = null;
        boolean insertionSuccess = false; //Returns the result of adding students and adding to class.

        try {
            connection.setAutoCommit(false);

            String insertStudentSql = "INSERT INTO Student (sname, dob, gender, [Address], status, pid) VALUES (?, ?, ?, ?, 1, ?)";
            insert = connection.prepareStatement(insertStudentSql);

            insert.setString(1, sname);
            insert.setString(2, dob);
            insert.setBoolean(3, gender);
            insert.setString(4, address);
            insert.setInt(5, pid);

            int rowsInserted = insert.executeUpdate();

            //is to get the id of the newly added student
            int stuid = -1;
            String retrieveStudentIdSql = "SELECT MAX(stuid) AS stuid FROM Student";
            try (PreparedStatement retrieveIdStmt = connection.prepareStatement(retrieveStudentIdSql); ResultSet rs = retrieveIdStmt.executeQuery()) {

                if (rs.next()) {
                    stuid = rs.getInt("stuid");
                }
            }

            // SQL to insert student into current year class session
            String insertStudentToClass = "INSERT INTO Student_Class_Session (stuid, csid) "
                    + "SELECT ?, cs.csid "
                    + "FROM Class_Session cs "
                    + "INNER JOIN SchoolYear sy ON cs.yid = sy.yid "
                    + "WHERE sy.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) AND cs.classID = ?";
            insertStuToClass = connection.prepareStatement(insertStudentToClass);
            insertStuToClass.setInt(1, stuid);
            insertStuToClass.setInt(2, classID);

            // Execute insert 
            int rowsAssigned = insertStuToClass.executeUpdate();

            // Commit transaction if both inserts are successful
            if (rowsInserted > 0 && rowsAssigned > 0) {
                connection.commit();
                insertionSuccess = true;
            } else {
                connection.rollback();
            }

        } catch (SQLException ex) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace(); // Handle rollback exception
            }
            ex.printStackTrace(); // Handle SQL exception
        } finally {
            try {
                if (insert != null) {
                    insert.close();
                }
                if (insertStuToClass != null) {
                    insertStuToClass.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Handle closing exception
            }
        }

        return insertionSuccess;
    }

    //Get the total number of students active in the most recent school year 
    public int getTotalStudentByLatestSchoolYear() {
        String sql = "SELECT COUNT(*) as total FROM Student S "
                + "INNER JOIN Student_Class_Session scs ON S.stuid = scs.stuid "
                + "INNER JOIN Class_Session cs ON scs.csid = cs.csid "
                + "INNER JOIN SchoolYear sy ON cs.yid = sy.yid "
                + "WHERE sy.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) "
                + "AND S.status = 1";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentClassSessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //Get the total number of students inactive in the most recent school year 
    public int getTotalStudentInactiveByLatestSchoolYear() {
        String sql = "SELECT COUNT(*) as total FROM Student S "
                + "INNER JOIN Student_Class_Session scs ON S.stuid = scs.stuid "
                + "INNER JOIN Class_Session cs ON scs.csid = cs.csid "
                + "INNER JOIN SchoolYear sy ON cs.yid = sy.yid "
                + "WHERE sy.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) "
                + "AND S.status = 0";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentClassSessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<StudentClassSession> getStudentClassSessionById(int id) {
        List<StudentClassSession> stu = new ArrayList();
        try {
            String sql = "SELECT [scid]\n"
                    + "      ,[stuid]\n"
                    + "      ,[csid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Student_Class_Session] where stuid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
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

    public StudentClassSession getStudentClassSessionByStuid(int id, int yid) {
        StudentClassSession stu = new StudentClassSession();
        try {
            String sql = "  SELECT [scid] ,[stuid] , s.[csid] FROM [SchoolManagement].[dbo].[Student_Class_Session] s Inner Join Class_Session s1\n"
                    + "  ON s.csid = s1.csid Where s.stuid = ? and s1.yid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, yid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StudentClassSession student = new StudentClassSession();
                Class_SessionDBContext classSess = new Class_SessionDBContext();
                StudentDBContext stud = new StudentDBContext();
                student.setScid(rs.getInt("scid"));
                student.setCsid(classSess.getClassSessionById(rs.getInt("csid")));
                student.setStuid(stud.getStudentById(rs.getInt("stuid")));

                return student;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<StudentClassSession> getStudentClassSessionBySchoolYear(String timeStart, String timeEnd) {
        List<StudentClassSession> list = new ArrayList<>();
        try {
            String sql
                    = "SELECT S.stuid, S.sname, S.dob, S.gender, S.[Address], p.pid, cl.classID, cl.clname "
                    + "FROM Student S "
                    + "LEFT join Student_Class_Session scs ON S.stuid = scs.stuid "
                    + "LEFT join  Class_Session cs ON scs.csid = cs.csid "
                    + "LEFT join Parent p ON s.pid = p.pid "
                    + "LEFT join class cl ON cs.classID = cl.classID "
                    + "LEFT join SchoolYear sy ON cs.yid = sy.yid "
                    + "Where sy.dateStart LIKE ? AND sy.dateEnd LIKE ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, timeStart + "%");
            stm.setString(2, timeEnd + "%");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));
                student.setGender(rs.getBoolean("gender"));
                student.setDob(rs.getString("dob"));
                student.setAddress(rs.getString("Address"));

                Entity.Class cl = new Entity.Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(cl);

                Parent parent = new Parent();
                parent.setPid(rs.getInt("pid"));
                student.setPid(parent);

                StudentClassSession stuClass = new StudentClassSession();
                stuClass.setCsid(cs);
                stuClass.setStuid(student);
                list.add(stuClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<StudentClassSession> getStudentClassSessionBySchoolYearWithPaging(String timeStart, String timeEnd, int index) {
        List<StudentClassSession> list = new ArrayList<>();
        try {
            String sql
                    = "SELECT S.stuid, S.sname, S.dob, S.gender, S.[Address], p.pid, cl.classID, cl.clname "
                    + "FROM Student S "
                    + "Inner join Student_Class_Session scs ON S.stuid = scs.stuid "
                    + "Inner join  Class_Session cs ON scs.csid = cs.csid "
                    + "Inner join Parent p ON S.pid = p.pid "
                    + "Inner join class cl ON cs.classID = cl.classID "
                    + "Inner join SchoolYear sy ON cs.yid = sy.yid "
                    + "Where sy.dateStart LIKE ? AND sy.dateEnd LIKE ? "
                    + "ORDER BY S.stuid OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, timeStart + "%");
            stm.setString(2, timeEnd + "%");
            stm.setInt(3, (index - 1) * 10);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));
                student.setGender(rs.getBoolean("gender"));
                student.setDob(rs.getString("dob"));
                student.setAddress(rs.getString("Address"));

                Entity.Class cl = new Entity.Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));

                ClassSession cs = new ClassSession();
                cs.setClassID(cl);

                Parent parent = new Parent();
                parent.setPid(rs.getInt("pid"));
                student.setPid(parent);

                StudentClassSession stuClass = new StudentClassSession();
                stuClass.setCsid(cs);
                stuClass.setStuid(student);
                list.add(stuClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //number of students attending a class in a particular academic year
    public int getTotalStudentBySchoolYear(String timeStart, String timeEnd) {
        String sql = "SELECT COUNT(*) as total FROM Student S "
                + "INNER JOIN Student_Class_Session scs ON S.stuid = scs.stuid "
                + "INNER JOIN Class_Session cs ON scs.csid = cs.csid "
                + "INNER JOIN SchoolYear sy ON cs.yid = sy.yid "
                + "WHERE sy.dateStart LIKE ? AND sy.dateEnd LIKE ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + timeStart + "%");
            stm.setString(2, "%" + timeEnd + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentClassSessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //Get total number of students from database
    public int getTotalStudent() {
        try {
            String sql = "select count (*) from Student";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

}
