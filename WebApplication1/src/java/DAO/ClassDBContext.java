/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import Entity.Class;
import Entity.ClassSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class ClassDBContext extends DBContext {

    public Class getClassById(int id) {
        Class cla = new Class();
        try {
            String sql = "SELECT [classID]\n"
                    + "      ,[clname] FROM [SchoolManagement].[dbo].[Class] where classID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                cla.setClassid(rs.getInt("classID"));
                cla.setClname(rs.getString("clname"));

                return cla;
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

                Class cl = new Class();
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

    public ArrayList<Class> getClassById2(int id) {
        ArrayList<Class> list = new ArrayList<>();
        try {
            String sql = "SELECT [classID]\n"
                    + "      ,[clname] FROM [SchoolManagement].[dbo].[Class] where classID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Class cla = new Class();
                cla.setClassid(rs.getInt("classID"));
                cla.setClname(rs.getString("clname"));

                list.add(cla);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Class> getAllLecturersContain() {
        List<Class> list = new ArrayList<>();

        try {
            String sql = "SELECT\n"
                    + "    C.classID,\n"
                    + "    C.clname\n"
                    + "FROM\n"
                    + "    Class C\n"
                    + "LEFT JOIN (\n"
                    + "    SELECT DISTINCT\n"
                    + "        C.classID\n"
                    + "    FROM\n"
                    + "        Lecturers L\n"
                    + "    LEFT JOIN\n"
                    + "        Lecturers_Class_Session LCS ON L.lid = LCS.lid\n"
                    + "    LEFT JOIN\n"
                    + "        Class_Session CS ON LCS.csid = CS.csid\n"
                    + "    LEFT JOIN\n"
                    + "        SchoolYear sy ON CS.yid = sy.yid\n"
                    + "    LEFT JOIN\n"
                    + "        Class C ON CS.classID = C.classID\n"
                    + "    WHERE\n"
                    + "        sy.yid = (SELECT MAX(yid) FROM SchoolYear)\n"
                    + ") AS T ON C.classID = T.classID\n"
                    + "WHERE\n"
                    + "    T.classID IS NULL;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Class cl = new Class();
                cl.setClassid(rs.getInt("classID"));
                cl.setClname(rs.getString("clname"));
                list.add(cl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
