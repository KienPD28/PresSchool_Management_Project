/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Account;
import Entity.Lecturers;
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
public class AccountDBContext extends DBContext {

    public static void main(String[] args) {
        AccountDBContext ac = new AccountDBContext();
        ac.createNewAccount("kien28102003", "123");
//        ArrayList<Account> list = ac.getOnlyNewAccount();
//        System.out.println(list);

    }
    
    //change password lecturer
    public void changePassLecurers(int id, String newPass) {
        try {
            String sql = "UPDATE Account SET password = ? WHERE lid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newPass);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Hàm Lấy Tất cả Account
    public ArrayList<Account> getAllAccount() {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT acc.[aid]\n"
                    + "      ,acc.[username]\n"
                    + "      ,acc.[password]\n"
                    + "      ,acc.[role]\n"
                    + "      ,acc.[pid]\n"
                    + "      ,pa.pname\n"
                    + "      ,acc.[lid]\n"
                    + "      ,le.lname\n"
                    + "  FROM Account acc\n"
                    + "  LEFT JOIN Parent pa on acc.pid = pa.pid\n"
                    + "  LEFT JOIN Lecturers le on acc.lid = le.lid";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAid(rs.getInt("aid"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setRole(rs.getInt("role"));

                Parent pa = new Parent();
                pa.setPid(rs.getInt("pid"));
                pa.setPname(rs.getString("pname"));
                acc.setPid(pa);

                Lecturers le = new Lecturers();
                le.setLid(rs.getInt("lid"));
                le.setLname(rs.getString("lname"));
                acc.setLid(le);

                list.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //Chỉ lấy ra những account nào mới được tạo
    public ArrayList<Account> getOnlyNewAccount() {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Account WHERE role IS NULL AND pid IS NULL AND lid IS NULL";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAid(rs.getInt("aid"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                list.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //Lấy Các Account theo Role
    public ArrayList<Account> getAllAccountByRole(String role) {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT acc.[aid]\n"
                    + "      ,acc.[username]\n"
                    + "      ,acc.[password]\n"
                    + "      ,acc.[role]\n"
                    + "      ,acc.[pid]\n"
                    + "      ,pa.pname\n"
                    + "      ,acc.[lid]\n"
                    + "      ,le.lname\n"
                    + "  FROM Account acc\n"
                    + "  LEFT JOIN Parent pa on acc.pid = pa.pid\n"
                    + "  LEFT JOIN Lecturers le on acc.lid = le.lid"
                    + "  WHERE acc.[role] = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAid(rs.getInt("aid"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setRole(rs.getInt("role"));

                Parent pa = new Parent();
                pa.setPid(rs.getInt("pid"));
                pa.setPname(rs.getString("pname"));
                acc.setPid(pa);

                Lecturers le = new Lecturers();
                le.setLid(rs.getInt("lid"));
                le.setLname(rs.getString("lname"));
                acc.setLid(le);

                list.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Account> getAllAccountByAid(String aid) {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT [aid]\n"
                    + "      ,[username]\n"
                    + "      ,[password]\n"
                    + "      ,[role]\n"
                    + "      ,[pid]\n"
                    + "      ,[lid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Account]\n"
                    + "  WHERE aid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, aid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();

                acc.setAid(rs.getInt("aid"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                

                Parent pa = new Parent();
                pa.setPid(rs.getInt("pid"));
                acc.setPid(pa);

                Lecturers lec = new Lecturers();
                lec.setLid(rs.getInt("lid"));
                acc.setLid(lec);

                list.add(acc);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Account getAllAccountByAid2(String aid) {
        
        try {
            String sql = "SELECT [aid]\n"
                    + "      ,[username]\n"
                    + "      ,[password]\n"
                    + "      ,[role]\n"
                    + "      ,[pid]\n"
                    + "      ,[lid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Account]\n"
                    + "  WHERE aid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, aid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();

                acc.setAid(rs.getInt("aid"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));

                Parent pa = new Parent();
                pa.setPid(rs.getInt("pid"));
                acc.setPid(pa);

                Lecturers lec = new Lecturers();
                lec.setLid(rs.getInt("lid"));
                acc.setLid(lec);

                return acc;

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Parent> getAllParent() {
        ArrayList<Parent> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [SchoolManagement].[dbo].[Parent]";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
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
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Parent> getParentByPid(String pid) {
        ArrayList<Parent> list = new ArrayList<>();
        try {
            String sql = "SELECT [pid]\n"
                    + "      ,[pname]\n"
                    + "  FROM [SchoolManagement].[dbo].[Parent]\n"
                    + "  WHERE pid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Parent pa = new Parent();
                pa.setPid(rs.getInt("pid"));
                pa.setPname(rs.getString("pname"));
                list.add(pa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //Lấy PID chưa tồn tại trong Account
    public ArrayList<Parent> getPidNotExitsAccount() {
        ArrayList<Parent> list = new ArrayList<>();
        try {
            String sql = " Select pa.pid, pa.pname from Parent pa \n"
                    + " LEFT JOIN Account acc on pa.pid = acc.pid\n"
                    + " WHERE acc.pid is NULL";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Parent pa = new Parent();
                pa.setPid(rs.getInt("pid"));
                pa.setPname(rs.getString("pname"));

                Account acc = new Account();
                acc.setPid(pa);

                list.add(pa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //Lấy Lid chưa tồn tại trong Account
    public ArrayList<Lecturers> getLidNotExitsAccount() {
        ArrayList<Lecturers> list = new ArrayList<>();
        try {
            String sql = " Select lec.lid, lec.lname from Lecturers lec\n"
                    + " LEFT JOIN Account acc on lec.lid = acc.lid\n"
                    + " WHERE acc.lid is NULL";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lecturers lec = new Lecturers();
                lec.setLid(rs.getInt("lid"));
                lec.setLname(rs.getString("lname"));

                Account acc = new Account();
                acc.setLid(lec);

                list.add(lec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Lecturers> getAllLecturers() {
        ArrayList<Lecturers> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [SchoolManagement].[dbo].[Lecturers]";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
                list.add(lec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Lecturers> getLecturersByLid(String lid) {
        ArrayList<Lecturers> list = new ArrayList<>();
        try {
            String sql = "SELECT [lid]\n"
                    + "      ,[lname]\n"
                    + "  FROM [SchoolManagement].[dbo].[Lecturers]\n"
                    + "  WHERE lid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, lid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lecturers lec = new Lecturers();
                lec.setLid(rs.getInt("lid"));
                lec.setLname(rs.getString("lname"));
                list.add(lec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

     //Hàm Update => Phân Quyền Role, Pid, Lid cho Account
    public void updateAuthenticationAccount3(Account acc, boolean status) {
        try {
            String sql = "UPDATE Account SET role = ?, pid = ?, lid = ?, status = ? WHERE aid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, acc.getRole());

            if (acc.getPid() != null) {
                ps.setInt(2, acc.getPid().getPid());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            if (acc.getLid() != null) {
                ps.setInt(3, acc.getLid().getLid());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.setBoolean(4, status); // Cập nhật trạng thái của tài khoản
            ps.setInt(5, acc.getAid());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAccount(String aid) {
        try {
            String sql = "DELETE FROM [dbo].[Account]\n"
                    + "      WHERE aid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, aid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    //Hàm Tạo Account mới
    public void createNewAccount(String username, String password) {
        try {
            String sql = " INSERT INTO Account (username, password, pid, lid) VALUES (?, ?, NULL, NULL)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Hàm kiểm tra xem username tồn tại 
    public boolean isUsernameTaken(String username) {
        try {
            String sql = "SELECT COUNT(*) FROM Account WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //change password
    public void changePass(int id, String newPass) {
        try {
            String sql = "UPDATE Account SET password = ? WHERE pid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newPass);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account getByUsernamePassword(String username, String password) {
        try {
            String sql = "SELECT [aid]\n"
                    + "      ,[username]\n"
                    + "      ,[password]\n"
                    + "      ,[role]\n"
                    + "      ,[pid]\n"
                    + "      ,[lid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Account]\n"
                    + "  Where [username] = ? and [password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getInt("role"));
                ParentDBContext padb = new ParentDBContext();
                LecturersDBContext ledb = new LecturersDBContext();
                account.setLid(ledb.getLecturerByid(rs.getInt("lid")));
                account.setPid(padb.getParentByid(rs.getInt("pid")));
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updatePasswordByParentEmail(String email, String newPassword) {
        String sql = "UPDATE [dbo].[Account] SET [password] = ? WHERE pid = (SELECT pid FROM Parent WHERE email = ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePasswordByLecturerEmail(String email, String newPassword) {
        String sql = "UPDATE [dbo].[Account] SET [password] = ? WHERE lid = (SELECT lid FROM Lecturers WHERE email = ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int countAccounts() {
        String sql = "SELECT COUNT(*)  From Account where role = 1 or role = 2";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countStudents() {
        String sql = "SELECT COUNT(*) AS total FROM [SchoolManagement].[dbo].[Student] ";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countTeachers() {
        String sql = "SELECT COUNT(*) AS total FROM [SchoolManagement].[dbo].[Lecturers]";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    //Cookie
    public ArrayList<Account> getAccountByUsernameandPassword(String username, String password) {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT [aid]\n"
                    + "      ,[username]\n"
                    + "      ,[password]\n"
                    + "      ,[role]\n"
                    + "      ,[pid]\n"
                    + "      ,[lid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Account]\n"
                    + "  WHERE [username] = ? and [password] = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Account a = new Account();
                a.setAid(rs.getInt("aid"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setRole(rs.getInt("role"));
                
                Parent pa = new Parent();
                pa.setPid(rs.getInt("pid"));
                a.setPid(pa);
                
                Lecturers lec = new Lecturers();
                lec.setLid(rs.getInt("lid"));
                a.setLid(lec);
                
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
