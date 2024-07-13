/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.ClassSession;
import Entity.Lecturers;
import Entity.Lecturers_Class_Session;

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
public class LecturersDBContext extends DBContext {

    public static void main(String[] args) {
        LecturersDBContext l = new LecturersDBContext();
        System.out.println(l.getLecturersNickNameByEmail("bhan6589@gmail.com"));
    }

    //check id card
    public boolean isIDCardLecExists(String idCard) {
        boolean exists = false;
        try {
            String sql = "SELECT COUNT(*) AS count FROM [SchoolManagement].[dbo].[Lecturers] WHERE [IDcard] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, idCard);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerClassSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }

    //update lecturers profile
    public void updateLecturer(Lecturers lecturer) {
        try {
            String sql = "UPDATE [Lecturers] SET lname = ?, gender = ?, dob = ?, phoneNumber = ?, IDcard = ?, Email = ?, Address = ?, NickName = ? WHERE lid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, lecturer.getLname());
            stm.setBoolean(2, lecturer.isGender());
            stm.setString(3, lecturer.getDob());
            stm.setString(4, lecturer.getPhoneNumber());
            stm.setString(5, lecturer.getIDcard());
            stm.setString(6, lecturer.getAddress());
            stm.setString(7, lecturer.getNickname());
            stm.setString(8, lecturer.getEmail());
            stm.setInt(9, lecturer.getLid());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LecturerClassSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Lecturers getLecturerByid(int rid) {
        try {
            String sql = "SELECT [lid]\n"
                    + "      ,[lname]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[phoneNumber]\n"
                    + "      ,[IDcard]\n"
                    + "      ,[Address]\n"
                    + "      ,[Email]\n"
                    + "      ,[NickName]\n"
                    + "  FROM [SchoolManagement].[dbo].[Lecturers] Where Lid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, rid);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Lecturers lec = new Lecturers();
                lec.setLid(rs.getInt("lid"));
                lec.setLname(rs.getString("lname"));
                lec.setGender(rs.getBoolean("gender"));
                lec.setDob(rs.getString("dob"));
                lec.setPhoneNumber(rs.getString("phoneNumber"));
                lec.setIDcard(rs.getString("IDcard"));
                lec.setEmail(rs.getString("Email"));
                lec.setAddress(rs.getString("Address"));
                lec.setNickname(rs.getString("NickName"));
                return lec;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Lecturers getLecturersNickNameByEmail(String email) {
        try {
            String sql = "SELECT NickName FROM Lecturers WHERE Email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lecturers lec = new Lecturers();
                lec.setNickname(rs.getString("NickName"));
                return lec;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturersDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Lecturers getLecturerByEmail(String email) {
        try {
            String sql = "SELECT [lid]\n"
                    + "      ,[lname]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[phoneNumber]\n"
                    + "      ,[IDcard]\n"
                    + "      ,[Address]\n"
                    + "      ,[Email]\n"
                    + "      ,[NickName]\n"
                    + "  FROM [SchoolManagement].[dbo].[Lecturers] Where Email = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Lecturers lec = new Lecturers();
                lec.setLid(rs.getInt("lid"));
                lec.setLname(rs.getString("lname"));
                lec.setGender(rs.getBoolean("gender"));
                lec.setDob(rs.getString("dob"));
                lec.setPhoneNumber(rs.getString("phoneNumber"));
                lec.setIDcard(rs.getString("IDcard"));
                lec.setEmail(rs.getString("Email"));
                lec.setAddress(rs.getString("Address"));
                lec.setNickname(rs.getString("NickName"));
                return lec;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteLecturers(String lid) {
        try {
            connection.setAutoCommit(false);

            // Xóa các bản ghi liên quan trong bảng Lecturers_Class_Session
            String sql1 = "DELETE FROM Lecturers_Class_Session WHERE lid = ?";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setString(1, lid);
            stm1.executeUpdate();

            // Xóa các bản ghi liên quan trong bảng Account
            String sql2 = "DELETE FROM Account WHERE lid = ?";
            PreparedStatement stm2 = connection.prepareStatement(sql2);
            stm2.setString(1, lid);
            stm2.executeUpdate();

            // Xóa bản ghi trong bảng Lecturers
            String sql3 = "DELETE FROM lecturers WHERE lid = ?";
            PreparedStatement stm3 = connection.prepareStatement(sql3);
            stm3.setString(1, lid);
            stm3.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(LecturersDBContext.class.getName()).log(Level.SEVERE, null, rollbackEx);
            }
            Logger.getLogger(LecturersDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(LecturersDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // dem so luonng giao vien trong database 
    public int getTotalLecturers() {
        try {
            String sql = "select count (*) from Lecturers";

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

    public List<Lecturers> pagingLecturers(int index) {
        List<Lecturers> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "Lecturers.lid,\n"
                    + "Lecturers.lname,\n"
                    + "Lecturers.gender,\n"
                    + "Lecturers.dob,\n"
                    + "Lecturers.phoneNumber,\n"
                    + "Lecturers.IDcard,\n"
                    + "Lecturers.Email,\n"
                    + "Lecturers.Address\n"
                    + "FROM Lecturers\n"
                    + "\n"
                    + "ORDER BY Lecturers.lid \n"
                    + "OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY;";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 10);  // Calculate the correct offset
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
                list.add(lecturer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public int getTotalLecturersBySchoolYear(String timeStart, String timeEnd) {
        String sql = "SELECT COUNT(*) FROM Lecturers "
                + "INNER JOIN Lecturers_Class_Session ON Lecturers.lid = Lecturers_Class_Session.lid "
                + "INNER JOIN Class_Session ON Lecturers_Class_Session.csid = Class_Session.csid "
                + "INNER JOIN SchoolYear ON Class_Session.yid = SchoolYear.yid "
                + "WHERE SchoolYear.dateStart LIKE ? AND SchoolYear.dateEnd LIKE ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, timeStart + "%");
            stm.setString(2, timeEnd + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturersDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Lecturers> getLecturerByID(String lid) {
        List<Lecturers> list = new ArrayList<>();
        try {
            String sql = "select * from Lecturers where lid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);

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

                list.add(lecturer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Lecturers> getLecturerByName(String lname) {
        List<Lecturers> list = new ArrayList<>();
        try {
            String sql = "select * from Lecturers where lname like ?;";

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

                list.add(lecturer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerClassSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Lecturers getLecturerById(String lid) {
        try {
            String sql = "SELECT [lid], [lname], [gender], [dob], [phoneNumber], [IDcard], [Address], [Email], [NickName] "
                    + "FROM [SchoolManagement].[dbo].[Lecturers] WHERE [lid] = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Lecturers lec = new Lecturers();
                lec.setLid(rs.getInt("lid"));
                lec.setLname(rs.getString("lname"));
                lec.setGender(rs.getBoolean("gender"));
                lec.setDob(rs.getString("dob"));
                lec.setPhoneNumber(rs.getString("phoneNumber"));
                lec.setIDcard(rs.getString("IDcard"));
                lec.setEmail(rs.getString("Email"));
                lec.setAddress(rs.getString("Address"));
                lec.setNickname(rs.getString("NickName"));
                return lec;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturersDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateLecturers(Lecturers lecturers) {
        try {
            // Assuming 'connection' is initialized somewhere in your class
            String sql = "UPDATE [dbo].[Lecturers]\n"
                    + "   SET [lname] = ?,\n"
                    + "      [gender] = ?,\n"
                    + "      [dob] = ?,\n"
                    + "      [phoneNumber] = ?,\n"
                    + "      [IDcard] = ?,\n"
                    + "      [Address] = ?,\n"
                    + "      [Email] = ?\n" // Remove the comma here
                    + "      WHERE lid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, lecturers.getLname());
            stm.setBoolean(2, lecturers.isGender());
            stm.setString(3, lecturers.getDob());
            stm.setString(4, lecturers.getPhoneNumber());
            stm.setString(5, lecturers.getIDcard());
            stm.setString(6, lecturers.getAddress());
            stm.setString(7, lecturers.getEmail());
            stm.setInt(8, lecturers.getLid());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LecturersDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Lecturers> getAllLecturers() {
        List<Lecturers> list = new ArrayList<>();
        try {
            String sql = "select * from Lecturers";

            PreparedStatement stm = connection.prepareStatement(sql);
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

                list.add(lecturer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerClassSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Lecturers> getAllLecturerContain() {
        List<Lecturers> list = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "    L.lid,\n"
                    + "    L.lname\n"
                    + "FROM\n"
                    + "    Lecturers L\n"
                    + "LEFT JOIN\n"
                    + "    Lecturers_Class_Session LCS ON L.lid = LCS.lid\n"
                    + "LEFT JOIN\n"
                    + "    Class_Session CS ON LCS.csid = CS.csid\n"
                    + "LEFT JOIN\n"
                    + "    SchoolYear SY ON CS.yid = SY.yid\n"
                    + "LEFT JOIN\n"
                    + "    Class C ON CS.classID = C.classID\n"
                    + "WHERE\n"
                    + "    C.classID IS NULL ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Lecturers lec = new Lecturers();
                lec.setLid(rs.getInt("lid"));
                lec.setLname(rs.getString("lname"));
                list.add(lec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
