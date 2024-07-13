/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Schedules;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SchedulesDBContext extends DBContext {

    public static void main(String[] args) {
        SchedulesDBContext d = new SchedulesDBContext();
        System.out.println(d.getSchedulesByCsIdAndDate(1, "2024-07-11").getSdid().getSdid());
    }

    public List<Schedules> getAllSessionsDetailByYidAndLid(int yid, int lid) {
        List<Schedules> list = new ArrayList<>();
        try {
            String sql = "SELECT [scheID]\n"
                    + "      ,[sdid]\n"
                    + "      ,[Date]\n"
                    + "      ,cs.[csid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Schedules] s Inner Join Class_Session cs On s.csid = cs.csid\n"
                    + "  Inner Join Lecturers_Class_Session lc On lc.csid = cs.csid\n"
                    + "  Where cs.yid = ? and lc.lid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, yid);
            stm.setInt(2, lid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedules schel = new Schedules();
                SessionDetailDBContext ses = new SessionDetailDBContext();
                Class_SessionDBContext cla = new Class_SessionDBContext();
                schel.setScheID(rs.getInt("scheID"));
                schel.setDate(rs.getDate("Date"));
                schel.setSdid(ses.getSessionDetailById(rs.getInt("sdid")));
                schel.setCsid(cla.getClassSessionById(rs.getInt("csid")));
                list.add(schel);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
 public List<Schedules> getAllUnclassifiedSessionsDetail(int csid, int sid) {
        List<Schedules> list = new ArrayList<>();
        try {
            String sql = "SELECT sd.sdid, sd.sid, s.scheID, s.csid, s.Date\n"
                    + "FROM Session_Details sd\n"
                    + "LEFT JOIN Schedules s ON sd.sdid = s.sdid AND s.csid = ?\n"
                    + "LEFT JOIN Class_Session cs ON cs.sid = sd.sid AND cs.csid = ?\n"
                    + "WHERE s.scheID IS NULL AND cs.sid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, csid);
            stm.setInt(2, csid);
            stm.setInt(3, sid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedules schel = new Schedules();
                SessionDetailDBContext ses = new SessionDetailDBContext();
                Class_SessionDBContext cla = new Class_SessionDBContext();
                schel.setScheID(rs.getInt("scheID"));
                schel.setDate(rs.getDate("Date"));
                schel.setSdid(ses.getSessionDetailById(rs.getInt("sdid")));
                schel.setCsid(cla.getClassSessionById(rs.getInt("csid")));
                list.add(schel);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Schedules getSchedulesByCsIdAndDate(int id, String date) {
        Schedules schel = new Schedules();
        try {
            String sql = "SELECT [scheID]\n"
                    + "      ,[sdid]\n"
                    + "      ,[Date]\n"
                    + "      ,[csid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Schedules] Where csid = ? and Date = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, date);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                SessionDetailDBContext ses = new SessionDetailDBContext();
                Class_SessionDBContext cla = new Class_SessionDBContext();
                schel.setScheID(rs.getInt("scheID"));
                schel.setDate(rs.getDate("Date"));
                schel.setSdid(ses.getSessionDetailById(rs.getInt("sdid")));
                schel.setCsid(cla.getClassSessionById(rs.getInt("csid")));
                return schel;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Schedules getSchedulesBySchedulesID(int id) {
        Schedules schel = new Schedules();
        try {
            String sql = "SELECT [scheID]\n"
                    + "      ,[sdid]\n"
                    + "      ,[Date]\n"
                    + "      ,[csid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Schedules] Where scheID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                SessionDetailDBContext ses = new SessionDetailDBContext();
                Class_SessionDBContext cla = new Class_SessionDBContext();
                schel.setScheID(rs.getInt("scheID"));
                schel.setDate(rs.getDate("Date"));
                schel.setSdid(ses.getSessionDetailById(rs.getInt("sdid")));
                schel.setCsid(cla.getClassSessionById(rs.getInt("csid")));
                return schel;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Schedules> getSchedulesByCsid(int csid) {
        List<Schedules> list = new ArrayList<>();
        try {
            String sql = "SELECT [scheID]\n"
                    + "      ,[sdid]\n"
                    + "      ,[Date]\n"
                    + "      ,[csid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Schedules] Where csid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, csid);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedules schel = new Schedules();
                SessionDetailDBContext ses = new SessionDetailDBContext();
                Class_SessionDBContext cla = new Class_SessionDBContext();
                schel.setScheID(rs.getInt("scheID"));
                schel.setDate(rs.getDate("Date"));
                schel.setSdid(ses.getSessionDetailById(rs.getInt("sdid")));
                schel.setCsid(cla.getClassSessionById(rs.getInt("csid")));
                list.add(schel);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    // Insert a category
    public void insert(int sdid, String date, int csid) {
        String sql = "INSERT INTO [dbo].[Schedules]\n"
                + "           ([sdid]\n"
                + "           ,[Date]\n"
                + "           ,[csid])\n"
                + "     VALUES\n"
                + "           (? ,? ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, sdid);
            st.setString(2, date);
            st.setInt(3, csid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Update
    public void update(String date, int sdid, int csid) {
        String sql = "UPDATE [dbo].[Schedules]\n"
                + "   SET [Date] = ?\n"
                + " WHERE sdid = ? and csid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, date);
            st.setInt(3, csid);
            st.setInt(2, sdid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

        // Delete category by id
    public void delete(int id) {
        String sql = "DELETE FROM [dbo].[Schedules]\n"
                + "      WHERE scheID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
