/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Parent;
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
public class ParentDBContext extends DBContext {
    
    public boolean isEmailExists(String email) {
    try {
        String sql = "SELECT COUNT(*) AS count FROM [SchoolManagement].[dbo].[Parent] WHERE [Email] = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, email);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getInt("count") > 0;
        }
    } catch (SQLException ex) {
        Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}
    
    //update parent status
    public void updateParentStatus(int pid, boolean status) {
        try {
            String sql = "UPDATE Parent SET status = ? WHERE pid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setBoolean(1, status);
            stm.setInt(2, pid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //search parentInactive by id
    public Parent getParentInactiveByid(int rid) {
        try {
            String sql = "SELECT [pid]\n"
                    + "      ,[pname]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[phoneNumber]\n"
                    + "      ,[IDcard]\n"
                    + "      ,[Address]\n"
                    + "      ,[Email]\n"
                    + "      ,[NickName]\n"
                    + "  FROM [SchoolManagement].[dbo].[Parent] Where pid = ? "
                    + " AND [status] = 0";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, rid);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Parent p = new Parent();
                p.setPid(rs.getInt("pid"));
                p.setPname(rs.getString("pname"));
                p.setGender(rs.getBoolean("gender"));
                p.setDob(rs.getString("dob"));
                p.setPhoneNumber(rs.getString("phoneNumber"));
                p.setIDcard(rs.getString("IDcard"));
                p.setEmail(rs.getString("Email"));
                p.setAddress(rs.getString("Address"));
                p.setNickname(rs.getString("NickName"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     //search parent inactive by name
    public List<Parent> getParentInactiveByName(String pname) {
        List<Parent> parentList = new ArrayList<>();
        try {
            String sql = "SELECT [pid], [pname], [gender], [dob], [phoneNumber], [IDcard], [Address], [Email], [NickName] "
                    + "FROM [SchoolManagement].[dbo].[Parent] "
                    + "WHERE [pname] LIKE ? "
                    + "AND [status] = 0";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + pname + "%");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Parent p = new Parent();
                p.setPid(rs.getInt("pid"));
                p.setPname(rs.getString("pname"));
                p.setGender(rs.getBoolean("gender"));
                p.setDob(rs.getString("dob"));
                p.setPhoneNumber(rs.getString("phoneNumber"));
                p.setIDcard(rs.getString("IDcard"));
                p.setEmail(rs.getString("Email"));
                p.setAddress(rs.getString("Address"));
                p.setNickname(rs.getString("NickName"));
                parentList.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parentList;
    }
    
    //get parent active by name
    public List<Parent> getParentByName(String pname) {
        List<Parent> parentList = new ArrayList<>();
        try {
            String sql = "SELECT [pid], [pname], [gender], [dob], [phoneNumber], [IDcard], [Address], [Email], [NickName] "
                    + "FROM [SchoolManagement].[dbo].[Parent] "
                    + "WHERE [pname] LIKE ? "
                    + "AND [status] = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + pname + "%");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Parent p = new Parent();
                p.setPid(rs.getInt("pid"));
                p.setPname(rs.getString("pname"));
                p.setGender(rs.getBoolean("gender"));
                p.setDob(rs.getString("dob"));
                p.setPhoneNumber(rs.getString("phoneNumber"));
                p.setIDcard(rs.getString("IDcard"));
                p.setEmail(rs.getString("Email"));
                p.setAddress(rs.getString("Address"));
                p.setNickname(rs.getString("NickName"));
                parentList.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parentList;
    }
    
    //get list parent inactive and paging
    public List<Parent> getAllParentInactive(int index) {
        List<Parent> parentList = new ArrayList<>();
        try {
            String sql = "SELECT [pid], [pname], [gender], [dob], [phoneNumber], [IDcard], [Address], [Email], [NickName] "
                    + "FROM [SchoolManagement].[dbo].[Parent] "
                    + "WHERE [status] = 0 "
                    + "ORDER BY [pid] "
                    + "OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 10);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Parent p = new Parent();
                p.setPid(rs.getInt("pid"));
                p.setPname(rs.getString("pname"));
                p.setGender(rs.getBoolean("gender"));
                p.setDob(rs.getString("dob"));
                p.setPhoneNumber(rs.getString("phoneNumber"));
                p.setIDcard(rs.getString("IDcard"));
                p.setEmail(rs.getString("Email"));
                p.setAddress(rs.getString("Address"));
                p.setNickname(rs.getString("NickName"));
                parentList.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parentList;
    }
    
    //get all parent active
    public List<Parent> getAllParents(int index) {
        List<Parent> parentList = new ArrayList<>();
        try {
            String sql = "SELECT [pid], [pname], [gender], [dob], [phoneNumber], [IDcard], [Address], [Email], [NickName] "
                    + "FROM [SchoolManagement].[dbo].[Parent] "
                    + "WHERE [status] = 1 "
                    + "ORDER BY [pid] "
                    + "OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 10);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Parent p = new Parent();
                p.setPid(rs.getInt("pid"));
                p.setPname(rs.getString("pname"));
                p.setGender(rs.getBoolean("gender"));
                p.setDob(rs.getString("dob"));
                p.setPhoneNumber(rs.getString("phoneNumber"));
                p.setIDcard(rs.getString("IDcard"));
                p.setEmail(rs.getString("Email"));
                p.setAddress(rs.getString("Address"));
                p.setNickname(rs.getString("NickName"));
                parentList.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parentList;
    }
    
    //Count the number of parents for pagination
    public int getTotalParent() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS count FROM [SchoolManagement].[dbo].[Parent] "
                    + " Where [status] = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    
     //Count the number of parents inactive for pagination
    public int getTotalParentInactive() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS count FROM [SchoolManagement].[dbo].[Parent] "
                    + " Where [status] = 0";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    
    //Check if parent exists
    public boolean checkParentIdExists(int pid) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM Parent WHERE pid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean isPhoneNumberExists(String phoneNumber) {
        try {
            String sql = "SELECT COUNT(*) FROM Parent WHERE phoneNumber = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, phoneNumber);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //Check if IDCard exists
    public boolean isIDCardExists(String idCard) {
        boolean exists = false;
        try {
            String sql = "SELECT COUNT(*) AS count FROM [SchoolManagement].[dbo].[Parent] WHERE [IDcard] = ?";
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
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }
    
    public void addParent(Parent parent) {
        try {
            connection.setAutoCommit(false);

            // add Parent
            String sqlParent = "INSERT INTO Parent (pname, gender, dob, phoneNumber, IDcard, [Address], Email, NickName, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";
            PreparedStatement stmInsertParent = connection.prepareStatement(sqlParent);

            stmInsertParent.setString(1, parent.getPname());
            stmInsertParent.setBoolean(2, parent.isGender());
            stmInsertParent.setString(3, parent.getDob());
            stmInsertParent.setString(4, parent.getPhoneNumber());
            stmInsertParent.setString(5, parent.getIDcard());
            stmInsertParent.setString(6, parent.getAddress());
            stmInsertParent.setString(7, parent.getEmail());
            stmInsertParent.setString(8, parent.getNickname());

            stmInsertParent.executeUpdate();

            //add account
            String sqlAccount = "INSERT INTO [dbo].[Account]([username],[password],[role],[pid],[status]) "
                    + "SELECT phoneNumber, FLOOR(RAND()*100000),'1',pid,'1' "
                    + "FROM Parent WHERE pid = (SELECT MAX(pid) FROM Parent)";
            PreparedStatement stmInsertAccount = connection.prepareStatement(sqlAccount);

            stmInsertAccount.executeUpdate();

            connection.commit();

        } catch (SQLException ex) {
            try {
                // If there is an error, rollback the transaction.
                connection.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, rollbackEx);
            }
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //auto-commit
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Parent getParentByid(int rid) {
        try {
            String sql = "SELECT [pid]\n"
                    + "      ,[pname]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[phoneNumber]\n"
                    + "      ,[IDcard]\n"
                    + "      ,[Address]\n"
                    + "      ,[Email]\n"
                    + "      ,[NickName]\n"
                    + "  FROM [SchoolManagement].[dbo].[Parent] Where pid = ?";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, rid);
            
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Parent p = new Parent();
                p.setPid(rs.getInt("pid"));
                p.setPname(rs.getString("pname"));
                p.setGender(rs.getBoolean("gender"));
                p.setDob(rs.getString("dob"));
                p.setPhoneNumber(rs.getString("phoneNumber"));
                p.setIDcard(rs.getString("IDcard"));
                p.setEmail(rs.getString("Email"));
                p.setAddress(rs.getString("Address"));
                p.setNickname(rs.getString("NickName"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Parent getParentNickNameByEmail(String email){
        try {
            String sql = "SELECT NickName FROM Parent WHERE Email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Parent pa = new Parent();
                pa.setNickname(rs.getString("NickName"));
                return pa;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Parent getParentByGmail(String gmail) {
        try {
            String sql = "SELECT [pid]\n"
                    + "      ,[pname]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[phoneNumber]\n"
                    + "      ,[IDcard]\n"
                    + "      ,[Address]\n"
                    + "      ,[Email]\n"
                    + "      ,[NickName]\n"
                    + "  FROM [SchoolManagement].[dbo].[Parent] Where Email = ?";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, gmail);
            
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Parent p = new Parent();
                p.setPid(rs.getInt("pid"));
                p.setPname(rs.getString("pname"));
                p.setGender(rs.getBoolean("gender"));
                p.setDob(rs.getString("dob"));
                p.setPhoneNumber(rs.getString("phoneNumber"));
                p.setIDcard(rs.getString("IDcard"));
                p.setEmail(rs.getString("Email"));
                p.setAddress(rs.getString("Address"));
                p.setNickname(rs.getString("NickName"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturersDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       public void updateParent(Parent parent) {
    try {
        String sql = "UPDATE [Parent] SET pname = ?, gender = ?, dob = ?, phoneNumber = ?, IDcard = ?, Address = ?, Email = ?, NickName = ? WHERE pid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setString(1, parent.getPname());
        stm.setBoolean(2, parent.isGender());
        stm.setString(3, parent.getDob());
        stm.setString(4, parent.getPhoneNumber());
        stm.setString(5, parent.getIDcard());
        stm.setString(6, parent.getAddress());
        stm.setString(7, parent.getEmail());
        stm.setString(8, parent.getNickname());
        stm.setInt(9, parent.getPid());

        stm.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}
