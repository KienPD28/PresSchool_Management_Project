/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.AgeCategory;
import Entity.Food;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class AgeDBContext extends DBContext {

    public List<AgeCategory> getAge() {
        List<AgeCategory> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Age_Category";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                AgeCategory age = new AgeCategory();
                age.setAgeid(rs.getInt("ageid"));
                age.setAname(rs.getString("aname"));
                list.add(age);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public AgeCategory getAgeById(int id) {
        AgeCategory age = new AgeCategory();
        try {
            String sql = "SELECT [ageid]\n"
                    + "      ,[aname]\n"
                    + "  FROM [SchoolManagement].[dbo].[Age_Category]"
                    + "  Where ageid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                age.setAgeid(rs.getInt("ageid"));
                age.setAname(rs.getString("aname"));
                return age;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<AgeCategory> getAllAgeCategory() {
        List<AgeCategory> list = new ArrayList<>();
        try {
            String sql = "SELECT [ageid]\n"
                    + "      ,[aname]\n"
                    + "  FROM [SchoolManagement].[dbo].[Age_Category]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                AgeCategory age = new AgeCategory();
                age.setAgeid(rs.getInt("ageid"));
                age.setAname(rs.getString("aname"));
                list.add(age);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

}
