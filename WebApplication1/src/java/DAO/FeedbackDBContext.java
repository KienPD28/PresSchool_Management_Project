/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Feedback;
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
 * @author admin
 */
public class FeedbackDBContext extends DBContext {

    public List<Feedback> getFeedbacksForLecturerAndDate(String lid, String date) {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = """
                     SELECT S.stuid, S.sname, F.fid ,F.ftitle, F.fcontent, F.dateFeedback 
                     FROM Student S 
                     INNER JOIN Student_Class_Session SCS ON S.stuid = SCS.stuid 
                     INNER JOIN Class_Session CS ON SCS.csid = CS.csid 
                     INNER JOIN Lecturers_Class_Session LCS ON CS.csid = LCS.csid 
                     INNER JOIN Parent P ON S.pid = P.pid 
                     INNER JOIN Class CL ON CS.classID = CL.classID 
                     INNER JOIN SchoolYear SY ON CS.yid = SY.yid 
                     LEFT JOIN Feedback F ON S.stuid = F.stuid 
                     WHERE SY.dateEnd = (SELECT MAX(dateEnd) FROM SchoolYear) 
                     AND LCS.lid = ? AND F.dateFeedback = ? """;

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, lid);
            stm.setString(2, date);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFid(rs.getInt("fid"));
                feedback.setFtitle(rs.getString("ftitle"));
                feedback.setFcontent(rs.getString("fcontent"));
                feedback.setDateFeedback(rs.getDate("dateFeedback"));

                Student student = new Student();
                student.setStuid(rs.getInt("stuid"));
                student.setSname(rs.getString("sname"));

                // Assuming Parent class has a field named pid and necessary getters/setters
                feedback.setStuid(student);
                feedbackList.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedbackList;
    }

    public boolean updateFeedback(int fid, int stuid, String ftitle, String fcontent, String dateFeedback) throws SQLException {

        String sql = "UPDATE Feedback SET stuid = ?, ftitle = ?, fcontent = ?, dateFeedback = ? WHERE fid = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, stuid);
            statement.setString(2, ftitle);
            statement.setString(3, fcontent);
            statement.setString(4, dateFeedback);
            statement.setInt(5, fid);

            int rowsAffected = statement.executeUpdate();
            statement.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting feedback: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteFeedback(int fid) {
        try {
            String sql = "DELETE FROM feedback WHERE fid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, fid);
            int rowsAffected = stm.executeUpdate();
            stm.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting feedback: " + e.getMessage());
            return false;
        }
    }

    public void addFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedback (ftitle, fcontent, dateFeedback, stuid) VALUES (?, ?, ?, ?)";
        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, feedback.getFtitle());
            ps.setString(2, feedback.getFcontent());
            ps.setDate(3, feedback.getDateFeedback());
            ps.setInt(4, feedback.getStuid().getStuid());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Feedback getFeedbackByIdAndate(String date, int id) {
        Feedback feed = new Feedback();
        try {
            String sql = "SELECT [fid]\n"
                    + "      ,[ftitle]\n"
                    + "      ,[fcontent]\n"
                    + "      ,[dateFeedBack]\n"
                    + "      ,[stuid]\n"
                    + "  FROM [SchoolManagement].[dbo].[FeedBack]\n"
                    + "  where dateFeedBack = ? and stuid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, date);
            stm.setInt(2, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                StudentDBContext student = new StudentDBContext();
                feed.setFid(rs.getInt("fid"));
                feed.setFtitle(rs.getString("ftitle"));
                feed.setFcontent(rs.getString("fcontent"));
                feed.setStuid(student.getStudentById(rs.getInt("stuid")));
                feed.setDateFeedback(rs.getDate("dateFeedBack"));
                return feed;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
