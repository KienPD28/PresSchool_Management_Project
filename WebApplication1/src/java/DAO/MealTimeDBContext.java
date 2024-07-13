/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.MealTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class MealTimeDBContext extends DBContext {

    public MealTime getMealTimeById(int id) {
        MealTime meal = new MealTime();
        try {
            String sql = "SELECT [mealID]\n"
                    + "      ,[mealName]\n"
                    + "  FROM [SchoolManagement].[dbo].[Meal_Time]"
                    + "  Where mealID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                meal.setMealID(rs.getInt("mealID"));
                meal.setMealName(rs.getString("mealName"));
                return meal;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<MealTime> getAllMealTime() {
        List<MealTime> meal = new ArrayList<>();
        try {
            String sql = "SELECT [mealID]\n"
                    + "      ,[mealName]\n"
                    + "  FROM [SchoolManagement].[dbo].[Meal_Time]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                MealTime m = new MealTime();
                m.setMealID(rs.getInt("mealID"));
                m.setMealName(rs.getString("mealName"));
                meal.add(m);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return meal;
    }
}
