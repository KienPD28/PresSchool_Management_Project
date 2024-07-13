/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Session;
import Entity.SessionDetails;
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
public class SessionDetailDBContext extends DBContext {

    public List<SessionDetails> getAllSessionDetails(String sid) {
        List<SessionDetails> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "                FROM Session s\n"
                + "                LEFT JOIN Session_Details sd ON s.sid = sd.sid \n"
                + "                WHERE s.sid = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, sid);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    SessionDetails sesDetail = new SessionDetails();
                    sesDetail.setSdid(rs.getInt("sdid"));
                    sesDetail.setDetail(rs.getString("details"));
                    sesDetail.setSessionNumber(rs.getInt("sessionNumber"));

                    Session session = new Session();
                    session.setSid(rs.getInt("sid"));
                    session.setTotalSession(rs.getInt("totalSession"));
                    sesDetail.setSid(session);
                    list.add(sesDetail);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
        return list;
    }

    public void deleteActivityOnSession(String curID) {
        try {

            String sql = "UPDATE [dbo].[Curiculum]\n"
                    + "                      SET statusSes = null\n"
                    + "                     WHERE curID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, curID);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LecturersDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertSession(String sid) {
        String sql = "INSERT INTO [dbo].[Session_Details] ([sid])\n"
                + "                VALUES (?);\n"
                + "              \n"
                + "                INSERT INTO [dbo].[Curiculum] ([nameAct], [sdid], [isFix], [TimeStart], [TimeEnd], [statusSes])\n"
                + "                SELECT DISTINCT\n"
                + "                    c.nameact,\n"
                + "                    (SELECT MAX(sdid) FROM Session_Details),\n"
                + "                    c.isFix,\n"
                + "                    c.TimeStart,\n"
                + "                    c.TimeEnd,\n"
                + "                    c.statusSes\n"
                + "                FROM Curiculum c\n"
                + "                JOIN Session_Details sd ON c.sdid = sd.sdid\n"
                + "                JOIN Session s ON s.sid = sd.sid\n"
                + "                WHERE c.isFix = 1 AND s.sid = ? AND c.statusSes IS NOT NULL;\n"
                + "                \n"
                + "                WITH NumberedSessions AS (\n"
                + "                    SELECT sdid, ROW_NUMBER() OVER(PARTITION BY sid ORDER BY sdid) AS row_number\n"
                + "                    FROM Session_Details\n"
                + "                )\n"
                + "                UPDATE sd\n"
                + "                SET sd.sessionNumber = ns.row_number\n"
                + "                FROM Session_Details sd\n"
                + "                JOIN NumberedSessions ns ON sd.sdid = ns.sdid;";

        try {
            connection.setAutoCommit(false);

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, sid);
            stm.setString(2, sid);
            stm.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    public int getTotalSession(String sid) {
        try {
            String sql = "select count(*) from Session_Details where sid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, sid);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public SessionDetails getSessionDetailById(int id) {
        SessionDetails sesDetail = new SessionDetails();
        try {
            String sql = "SELECT [sdid]\n"
                    + "      ,[details]\n"
                    + "      ,[sessionNumber]\n"
                    + "      ,[sid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Session_Details]\n"
                    + "  Where sdid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                SessionDBContext session = new SessionDBContext();
                sesDetail.setSdid(rs.getInt("sdid"));
                sesDetail.setDetail(rs.getString("details"));
                sesDetail.setSessionNumber(rs.getInt("sessionNumber"));
                sesDetail.setSid(session.getSessionById(rs.getInt("sid")));
                return sesDetail;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<SessionDetails> getAllSessionDetailBySessionID(int id) {
        List<SessionDetails> sesionDe = new ArrayList<>();
        try {
            String sql = "SELECT [sdid]\n"
                    + "      ,[details]\n"
                    + "      ,[sessionNumber]\n"
                    + "      ,[sid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Session_Details]\n"
                    + "  Where sid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SessionDetails sesDetail = new SessionDetails();
                SessionDBContext session = new SessionDBContext();
                sesDetail.setSdid(rs.getInt("sdid"));
                sesDetail.setDetail(rs.getString("details"));
                sesDetail.setSessionNumber(rs.getInt("sessionNumber"));
                sesDetail.setSid(session.getSessionById(rs.getInt("sid")));
                sesionDe.add(sesDetail);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return sesionDe;
    }
}
