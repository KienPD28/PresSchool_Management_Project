/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.ClassSession;
import Entity.Parent;
import Entity.Student;
import Entity.StudentClassSession;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class StudentDBContext extends DBContext {
    
    //Update student to new class
    public void updateStudentClass(int stuid, int newClassId) {
        try {
            String updateClassSQL
                    = "UPDATE Student_Class_Session SET csid = "
                    + "(SELECT cs.csid FROM Class_Session cs "
                    + "INNER JOIN SchoolYear sy ON cs.yid = sy.yid "
                    + "WHERE sy.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) AND cs.classID = ?) "
                    + "WHERE stuid = ?";
            PreparedStatement updateClassStmt = connection.prepareStatement(updateClassSQL);
            updateClassStmt.setInt(1, newClassId);
            updateClassStmt.setInt(2, stuid);
            updateClassStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStudentStatus(int studentId, boolean status) {
        try {
            String sql = "UPDATE [SchoolManagement].[dbo].[Student] "
                    + "SET status = ? "
                    + "WHERE stuid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setInt(2, studentId);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Get the parent ID . Used to update status
    public int getParentIdByStudentId(int studentId) {
        String sql = "SELECT pid FROM [SchoolManagement].[dbo].[Student] WHERE stuid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, studentId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("pid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    // Count the number of active students of the parent
    public int countActiveStudentsByParentId(int parentId) {
        String sql = "SELECT COUNT(*) AS total FROM [SchoolManagement].[dbo].[Student] WHERE pid = ? AND status = 1";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, parentId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Student> getAllStudent() {
        ParentDBContext parent = new ParentDBContext();
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT [stuid]\n"
                    + "      ,[sname]\n"
                    + "      ,[dob]\n"
                    + "      ,[gender]\n"
                    + "      ,[Address]\n"
                    + "      ,[pid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Student]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));

                student.setDob(rs.getString("dob"));
                student.setGender(rs.getBoolean("gender"));
                student.setAddress(rs.getString("Address"));
                student.setPid(parent.getParentByid(rs.getInt("pid")));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return students;
    }

    public Student getStudentById(int id) {
        ParentDBContext parent = new ParentDBContext();
        try {
            String sql = "SELECT [stuid]\n"
                    + "      ,[sname]\n"
                    + "      ,[dob]\n"
                    + "      ,[gender]\n"
                    + "      ,[Address]\n"
                    + "      ,[pid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Student] where stuid = ?";
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
                student.setPid(parent.getParentByid(rs.getInt("pid")));
                return student;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Student> getStudentByName(String sname) {
        List<Student> list = new ArrayList<>();
        ParentDBContext parent = new ParentDBContext();
        try {
            String sql = "SELECT [stuid], [sname], [dob], [gender], [Address], [pid] "
                    + "FROM [SchoolManagement].[dbo].[Student] WHERE sname LIKE ?";
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
                student.setPid(parent.getParentByid(rs.getInt("pid")));
                list.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Student> getStudentByIdUser(int id) {
        ParentDBContext parent = new ParentDBContext();
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT [stuid]\n"
                    + "      ,[sname]\n"
                    + "      ,[dob]\n"
                    + "      ,[gender]\n"
                    + "      ,[Address]\n"
                    + "      ,[pid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Student] where pid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {  // Sửa đổi từ if sang while
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));

                student.setDob(rs.getString("dob"));
                student.setGender(rs.getBoolean("gender"));
                student.setAddress(rs.getString("Address"));
                student.setPid(parent.getParentByid(rs.getInt("pid")));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return students;  // Trả về danh sách sinh viên
    }

    public List<Student> getStudentByPid(int id) {
        List<Student> list = new ArrayList<>();
        ParentDBContext parent = new ParentDBContext();
        try {
            String sql = "SELECT [stuid]\n"
                    + "      ,[sname]\n"
                    + "      ,[dob]\n"
                    + "      ,[gender]\n"
                    + "      ,[Address]\n"
                    + "      ,[pid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Student] where pid = ?";
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
                student.setPid(parent.getParentByid(rs.getInt("pid")));
                list.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Student> pagingStudent(int index) {
        List<Student> list = new ArrayList<>();
        ParentDBContext parent = new ParentDBContext();
        try {
            String sql = "SELECT S.stuid, S.sname, S.dob, S.gender, S.[Address], P.pid "
                    + "FROM Student S "
                    + "INNER JOIN Student_Class_Session SCS ON S.stuid = SCS.stuid "
                    + "INNER JOIN Class_Session CS ON SCS.csid = CS.csid "
                    + "INNER JOIN Parent P ON S.pid = P.pid "
                    + "ORDER BY S.stuid OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY;";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 10);  // Calculate the correct offset
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));
                student.setGender(rs.getBoolean("gender"));
                student.setDob(rs.getString("dob"));
                student.setAddress(rs.getString("Address"));

                student.setPid(parent.getParentByid(rs.getInt("pid")));
                list.add(student);

            }
        } catch (SQLException e) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public void deleteStudentAndParent(String stuid, String pid) {
        try {
            connection.setAutoCommit(false);

            // Xóa các bản ghi liên quan trong bảng Student_Class_Session
            String sql1 = "DELETE FROM Student_Class_Session WHERE stuid = ?";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setString(1, stuid);
            stm1.executeUpdate();

            // Xóa các bản ghi liên quan trong bảng Account
            String sql2 = "DELETE FROM Account WHERE pid = ?";
            PreparedStatement stm2 = connection.prepareStatement(sql2);
            stm2.setString(1, pid);
            stm2.executeUpdate();

            // Xóa bản ghi trong bảng Student
            String sql3 = "DELETE FROM Student WHERE stuid = ?";
            PreparedStatement stm3 = connection.prepareStatement(sql3);
            stm3.setString(1, stuid);
            stm3.executeUpdate();

            // Xóa bản ghi trong bảng Feedback
            String sql4 = "DELETE FROM Feedback WHERE stuid = ?";
            PreparedStatement stm4 = connection.prepareStatement(sql4);
            stm4.setString(1, stuid);
            stm4.executeUpdate();

            // Kiểm tra số lượng học sinh liên kết với phụ huynh
            String sqlCheck = "SELECT COUNT(*) FROM Student WHERE pid = ?";
            PreparedStatement stmCheck = connection.prepareStatement(sqlCheck);
            stmCheck.setString(1, pid);
            ResultSet rsCheck = stmCheck.executeQuery();
            rsCheck.next();
            int studentCount = rsCheck.getInt(1);

            // Xóa các bản ghi liên quan trong bảng parent
            if (studentCount == 0) {
                String sql5 = "DELETE FROM Parent WHERE pid = ?";
                PreparedStatement stm5 = connection.prepareStatement(sql5);
                stm5.setString(1, pid);
                stm5.executeUpdate();

            }

            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, rollbackEx);
            }
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateStudent(Student student) {
        try {
            String sql
                    = "UPDATE [dbo].[Student]\n"
                    + "   SET [sname] = ?,\n"
                    + "      [dob] = ?,\n"
                    + "      [gender] = ?,\n"
                    + "      [Address] = ?\n"
                    + "      WHERE stuid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, student.getSname());
            stm.setString(2, student.getDob());
            stm.setBoolean(3, student.isGender());
            stm.setString(4, student.getAddress());
            stm.setInt(5, student.getStuid());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
