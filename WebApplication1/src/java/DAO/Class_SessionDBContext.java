/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.ClassSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Class_SessionDBContext extends DBContext {
    public static void main(String[] args) {
        Class_SessionDBContext c = new Class_SessionDBContext();
        System.out.println(c.getClassSessionByLidAndDateNow(1, 1));
    }
    
     
    public ClassSession getSidByCsid(int csid) {
        ClassSession claSes = new ClassSession();
        try {
            String sql = "SELECT * FROM Class_Session where csid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, csid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                SchoolYearDBContext scho = new SchoolYearDBContext();
                ClassDBContext cla = new ClassDBContext();
                SessionDBContext ses = new SessionDBContext();
                RoomDBContext r = new RoomDBContext();
                AgeDBContext age = new AgeDBContext();
                claSes.setCsid(rs.getInt("csid"));
                claSes.setClassID(cla.getClassById(rs.getInt("classID")));
                claSes.setYid(scho.getSchoolYearById(rs.getInt("yid")));
                claSes.setSid(ses.getSessionById(rs.getInt("sid")));
                claSes.setRid(r.getRoomByRid(rs.getInt("rid")));
                return claSes;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    
    public List<ClassSession> getAllClass() {
        List<ClassSession> list = new ArrayList<>();
        try {
            String sql = "select * from Class_Session cs\n"
                    + "inner join Class cl on cs.classID = cl.classID\n"
                    + "where cs.status = '1'";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Entity.Class cl = new Entity.Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));
                ClassSession cs = new ClassSession();
                cs.setClassID(cl);
                list.add(cs);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public List<ClassSession> getClassSessionByLid(int lid) {
        List<ClassSession> list = new ArrayList<>();
        try {
            String sql = "SELECT *  FROM [SchoolManagement].[dbo].[Lecturers_Class_Session] lc "
                    + "Inner Join Class_Session cs ON lc.csid = cs.csid \n"
                    + "  where lid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ClassSession claSes = new ClassSession();
                SchoolYearDBContext scho = new SchoolYearDBContext();
                ClassDBContext cla = new ClassDBContext();
                SessionDBContext ses = new SessionDBContext();
                RoomDBContext r = new RoomDBContext();
                AgeDBContext age = new AgeDBContext();
                claSes.setCsid(rs.getInt("csid"));
                claSes.setClassID(cla.getClassById(rs.getInt("classID")));
                claSes.setYid(scho.getSchoolYearById(rs.getInt("yid")));
                claSes.setSid(ses.getSessionById(rs.getInt("sid")));
                claSes.setRid(r.getRoomByRid(rs.getInt("rid")));
                list.add(claSes);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public ClassSession getClassSessionByLidAndDateNow(int lid, int yid) {
        ClassSession claSes = new ClassSession();
        try {
            String sql = "SELECT l.[csid]\n"
                    + "      ,[classID]\n"
                    + "      ,l.[yid]\n"
                    + "      ,[sid]\n"
                    + "      ,[rid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Class_Session] l\n"
                    + "  Inner Join Lecturers_Class_Session lc\n"
                    + "  ON lc.csid = l.csid\n"
                    + "  Inner join SchoolYear s\n"
                    + "  On s.yid = l.yid\n"
                    + "  WHere lc.lid = ? and l.yid =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            stm.setInt(2, yid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                SchoolYearDBContext scho = new SchoolYearDBContext();
                ClassDBContext cla = new ClassDBContext();
                SessionDBContext ses = new SessionDBContext();
                RoomDBContext r = new RoomDBContext();
                AgeDBContext age = new AgeDBContext();
                claSes.setCsid(rs.getInt("csid"));
                claSes.setClassID(cla.getClassById(rs.getInt("classID")));
                claSes.setYid(scho.getSchoolYearById(rs.getInt("yid")));
                claSes.setSid(ses.getSessionById(rs.getInt("sid")));
                claSes.setRid(r.getRoomByRid(rs.getInt("rid")));
                return claSes;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ClassSession getClassSessionById(int id) {
        ClassSession claSes = new ClassSession();
        try {
            String sql = "SELECT [csid]\n"
                    + "      ,[classID]\n"
                    + "      ,[yid]\n"
                    + "      ,[sid]\n"
                    + "      ,[rid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Class_Session] Where csid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                SchoolYearDBContext scho = new SchoolYearDBContext();
                ClassDBContext cla = new ClassDBContext();
                SessionDBContext ses = new SessionDBContext();
                RoomDBContext r = new RoomDBContext();
                AgeDBContext age = new AgeDBContext();
                claSes.setCsid(rs.getInt("csid"));
                claSes.setClassID(cla.getClassById(rs.getInt("classID")));
                claSes.setYid(scho.getSchoolYearById(rs.getInt("yid")));
                claSes.setSid(ses.getSessionById(rs.getInt("sid")));
                claSes.setRid(r.getRoomByRid(rs.getInt("rid")));
                return claSes;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<ClassSession> getClassSessionByYid(int id) {
        List<ClassSession> list = new ArrayList<>();
        try {
            String sql = "SELECT [csid]\n"
                    + "      ,[classID]\n"
                    + "      ,[yid]\n"
                    + "      ,[sid]\n"
                    + "      ,[rid]\n"
                    + "  FROM [SchoolManagement].[dbo].[Class_Session] Where yid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ClassSession claSes = new ClassSession();
                SchoolYearDBContext scho = new SchoolYearDBContext();
                ClassDBContext cla = new ClassDBContext();
                SessionDBContext ses = new SessionDBContext();
                RoomDBContext r = new RoomDBContext();
                AgeDBContext age = new AgeDBContext();
                claSes.setCsid(rs.getInt("csid"));
                claSes.setClassID(cla.getClassById(rs.getInt("classID")));
                claSes.setYid(scho.getSchoolYearById(rs.getInt("yid")));
                claSes.setSid(ses.getSessionById(rs.getInt("sid")));
                claSes.setRid(r.getRoomByRid(rs.getInt("rid")));
                list.add(claSes);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

}
