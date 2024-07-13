/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Menu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class MenuDBContext extends DBContext {

    public Menu getMenuByAgeidAndate(int id, String date) {
        Menu menu = new Menu();
        try {
            String sql = "SELECT [mid]\n"
                    + "      ,[date]\n"
                    + "      ,[menu]\n"
                    + "      ,[ageid]\n"
                    + "      ,[mealID]\n"
                    + "  FROM [SchoolManagement].[dbo].[Menu] Where ageid = ? and date = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, date);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                MealTimeDBContext meal = new MealTimeDBContext();
                AgeDBContext age = new AgeDBContext();
                menu.setMid(rs.getInt("mid"));
                menu.setDate(rs.getDate("date"));
                menu.setAgeid(age.getAgeById(rs.getInt("ageid")));
                menu.setMenu(rs.getString("menu"));
                menu.setMealID(meal.getMealTimeById(rs.getInt("mealID")));
                return menu;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Menu> getMenuByAgeAndDate(int id, String date) {
        List<Menu> me = new ArrayList<>();
        try {
            String sql = "SELECT [mid]\n"
                    + "      ,[date]\n"
                    + "      ,[menu]\n"
                    + "      ,[ageid]\n"
                    + "      ,[mealID]\n"
                    + "  FROM [SchoolManagement].[dbo].[Menu] Where ageid = ? and date = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, date);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                MealTimeDBContext meal = new MealTimeDBContext();
                AgeDBContext age = new AgeDBContext();
                Menu menu = new Menu();
                menu.setMid(rs.getInt("mid"));
                menu.setDate(rs.getDate("date"));
                menu.setAgeid(age.getAgeById(rs.getInt("ageid")));
                menu.setMenu(rs.getString("menu"));
                menu.setMealID(meal.getMealTimeById(rs.getInt("mealID")));
                me.add(menu);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return me;
    }

    public List<Menu> getMenuByDate(String date) {
        List<Menu> me = new ArrayList<>();
        try {
            String sql = "SELECT [mid]\n"
                    + "      ,[date]\n"
                    + "      ,[menu]\n"
                    + "      ,[ageid]\n"
                    + "      ,[mealID]\n"
                    + "  FROM [SchoolManagement].[dbo].[Menu] Where date = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, date);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                MealTimeDBContext meal = new MealTimeDBContext();
                AgeDBContext age = new AgeDBContext();
                Menu menu = new Menu();
                menu.setMid(rs.getInt("mid"));
                menu.setDate(rs.getDate("date"));
                menu.setAgeid(age.getAgeById(rs.getInt("ageid")));
                menu.setMenu(rs.getString("menu"));
                menu.setMealID(meal.getMealTimeById(rs.getInt("mealID")));
                me.add(menu);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return me;
    }

    // Insert a category
    public boolean insertMenu(int ageid, String date, String menu, int mealID) {
        String sql = "INSERT INTO [dbo].[Menu]\n"
                + "           ([date]\n"
                + "           ,[menu]\n"
                + "           ,[ageid]\n"
                + "           ,[mealID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, date);
            st.setString(2, menu);
            st.setInt(3, ageid);
            st.setInt(4, mealID);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    // Update
    public boolean update(String date, String menu, int ageid, int meid) {
        String sql = "UPDATE [dbo].[Menu]\n"
                + "   SET [menu] = ?\n"
                + " WHERE date = ? and mealID = ? and [ageid] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, menu);
            st.setString(2, date);
            st.setInt(3, meid);
            st.setInt(4, ageid);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

}
